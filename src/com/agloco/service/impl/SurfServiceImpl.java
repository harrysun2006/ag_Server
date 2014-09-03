package com.agloco.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.agloco.dao.util.SurfDAOUtil;
import com.agloco.model.VBTimeSubTotal;
import com.agloco.model.VBTimeTotal;
import com.agloco.model.VBTimeTrack;
import com.agloco.model.VBURLTrack;
import com.agloco.service.SurfService;
import com.agloco.web.exception.BadRequestException;
import com.agloco.web.exception.DuplicateRecordException;
import com.agloco.web.service.model.TimeTrack;
import com.agloco.web.service.model.TimeTrackListReq;

public class SurfServiceImpl implements SurfService {

	// surf total
	public void createTimeTotal(VBTimeTotal sTotal) {
		SurfDAOUtil.createTimeTotal(sTotal);
	}

	public void deleteTimeTotal(VBTimeTotal sTotal) {
		SurfDAOUtil.deleteTimeTotal(sTotal);
	}

	public void updateTimeTotal(VBTimeTotal sTotal) {
		SurfDAOUtil.updateTimeTotal(sTotal);
	}

	public VBTimeTotal getTimeTotal(Long memberId) {
		return SurfDAOUtil.getTimeTotal(memberId);
	}

	// surf subtotal
	public void createTimeSubTotal(VBTimeSubTotal subtotal) {
		SurfDAOUtil.createTimeSubTotal(subtotal);
	}

	public void deleteTimeSubTotal(VBTimeSubTotal sSubtotal) {
		SurfDAOUtil.deleteTimeSubTotal(sSubtotal);
	}

	public VBTimeSubTotal getTimeSubTotal(Long memberId, Calendar surfDate) {
		return SurfDAOUtil.getTimeSubTotal(memberId, surfDate);
	}

	public void updateTimeSubTotal(VBTimeSubTotal sSubtotal) {
		SurfDAOUtil.updateTimeSubTotal(sSubtotal);
	}

	public List listTimeSubTotal(Long memberId) {
		return SurfDAOUtil.listTimeSubTotal(memberId);
	}

	// time track
	public void createTimeTrack(VBTimeTrack timeTrack) {
		SurfDAOUtil.createTimeTrack(timeTrack);
	}

	private void initVBTimeSubTotal(VBTimeSubTotal vbtst, Long memberId,
			Calendar surfDate, Long interval) {
		vbtst.setMemberId(memberId);
		vbtst.setYear(new Integer(surfDate.get(Calendar.YEAR)));
		vbtst.setMonth(new Integer(surfDate.get(Calendar.MONTH) + 1));
		vbtst.setDay(new Integer(surfDate.get(Calendar.DATE)));
		vbtst.setSurfDate(surfDate);
		vbtst.setSecond(interval);
	}

	public void addBatchTimeTrack(TimeTrackListReq ttList, String timeTrackTable,
			String timeTrackPattern, String subTotalTable, String subTotalPattern)
			throws Exception {
		Long memberId = ttList.getMemberId();

		long totalTime = 0;

		// SubTime map record the total time of each day
		HashMap<String, Long> subTime = new HashMap<String, Long>();

		// Add time track
		totalTime = addTimeTrack(ttList, timeTrackTable, timeTrackPattern, subTime);

		if (totalTime > 0) {
			// Update TimeTotal
			updateTimeTotal(memberId, totalTime);

			// Update SubTimeTotal
			updateTimeSubTotal(memberId, subTotalTable, subTotalPattern, subTime);
		}
	}

	/**
	 * 
	 * @param ttList:
	 *          the surf data from the client
	 * @param timeTrackTable:
	 *          timeTrackTrack name
	 * @param timeTrackPattern
	 * @param subTime:
	 *          the total time of each day
	 * @return total surf time
	 * @throws Exception
	 */
	private long addTimeTrack(TimeTrackListReq ttList, String timeTrackTable,
			String timeTrackPattern, Map<String, Long> subTime) throws Exception {
		long totalTime = 0;
		Calendar now = Calendar.getInstance();
		Calendar beginTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		SimpleDateFormat ttdf = new SimpleDateFormat(timeTrackPattern);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Long memberId = ttList.getMemberId();
		Long submitId = ttList.getSubmitId();
		for (int i = 0; i < ttList.size(); i++) {
			TimeTrack timeTrack = (TimeTrack) ttList.get(i);
			VBTimeTrack vbTimeTrack = new VBTimeTrack();
			vbTimeTrack.setMemberId(ttList.getMemberId());
			vbTimeTrack.setSubmitId(ttList.getSubmitId());

			Long beginPoint = timeTrack.getBeginPoint();
			Long endPoint = timeTrack.getEndPoint();

			if (beginPoint > ttList.getSubmitId()) {
				throw new BadRequestException("Bad request!(memberId = " + memberId + ", beginPoint = " + beginPoint + ", submitId = " + ttList.getSubmitId() + ")");
			}
			beginTime.setTimeInMillis(beginPoint.longValue() * 1000);
			endTime.setTimeInMillis(endPoint.longValue() * 1000);
			vbTimeTrack.setBeginTime(beginTime);
			vbTimeTrack.setEndTime(endTime);

			vbTimeTrack.setPoint(timeTrack.getPoint());
			vbTimeTrack.setStatus(timeTrack.getStatus());

			String date = df.format(beginTime.getTime());
			long interval = 0;
			if (subTime.containsKey(date))
				interval = ((Long) subTime.get(date)).longValue();

			// if(now.getTimeInMillis()+60000 > beginTime.getTimeInMillis())
			// Modify by Erick at 2007-05-15, by Harry at 2007-09-04 (add 60 seconds buffer)
			if (now.getTimeInMillis() > endTime.getTimeInMillis() - 60000 ) {
				long tmpIntervar = Math.abs(endTime.getTimeInMillis()
						- beginTime.getTimeInMillis()) / 1000;
				interval += tmpIntervar;
				totalTime += tmpIntervar;

				String scheduledTable = timeTrackTable
						+ ttdf.format(beginTime.getTime());
				String dateTable = timeTrackTable + ttdf.format(now.getTime());

				if (i == 0) {
					// Check if the record is exists
					if (scheduledTable.equals(dateTable)) {
						if (SurfDAOUtil.checkTimeTrack(memberId, submitId))
							throw new DuplicateRecordException("Duplicate time track!(memberId = " + memberId + ", submitId = " + submitId + ")");
					} else {
						if (SurfDAOUtil.checkTimeTrack(memberId, submitId, scheduledTable))
							throw new DuplicateRecordException("Duplicate time track!(memberId = " + memberId + ", submitId = " + submitId + ", table = " + scheduledTable + ")");
					}
				}

				// At midnight, old table will be renamed and new table will be created,
				// so if data is occured at the before day or more earlier,
				// it will be inserted into the true table
				if (scheduledTable.equals(dateTable)) {
					SurfDAOUtil.createTimeTrack(vbTimeTrack);
				} else {
					SurfDAOUtil.createTimeTrack(vbTimeTrack, scheduledTable);
				}
			} else {
				throw new BadRequestException("Bad request!(memberId = " + memberId + ", now = " + now.getTimeInMillis() + ", end = " + endTime.getTimeInMillis() + ")");
			}

			subTime.put(date, new Long(interval));
		}
		return totalTime;
	}

	private void updateTimeTotal(Long memberId, long totalTime) {
		VBTimeTotal vbtt = SurfDAOUtil.getTimeTotal(memberId);
		if (vbtt == null) {
			vbtt = new VBTimeTotal();
			vbtt.setMemberId(memberId);
			vbtt.setTotalSecond(new Long(totalTime));
			SurfDAOUtil.createTimeTotal(vbtt);
		} else {
			vbtt.setMemberId(vbtt.getMemberId());
			vbtt.setTotalSecond(new Long(totalTime
					+ vbtt.getTotalSecond().longValue()));
			SurfDAOUtil.updateTimeTotal(vbtt);
		}
	}

	private void updateTimeSubTotal(Long memberId, String subTotalTable,
			String subTotalPattern, Map subTime) throws Exception {
		SimpleDateFormat stdf = new SimpleDateFormat(subTotalPattern);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar now = Calendar.getInstance();

		for (Iterator iter = subTime.keySet().iterator(); iter.hasNext();) {
			String dateString = (String) iter.next();
			Long interval = (Long) subTime.get(dateString);
			Calendar surfDate = Calendar.getInstance();
			try {
				surfDate.setTime(df.parse(dateString));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				surfDate = now;
			}

			String scheduledTable = subTotalTable + stdf.format(surfDate.getTime());
			String dateTable = subTotalTable + stdf.format(now.getTime());

			// At midnight, old table will be renamed and new table will be created,
			// so if data is occured at the before month or more earlier,
			// it will be inserted into the true table
			if (scheduledTable.equals(dateTable)) {
				VBTimeSubTotal vbtst = SurfDAOUtil.getTimeSubTotal(memberId, surfDate);
				if (vbtst == null) {
					vbtst = new VBTimeSubTotal();
					initVBTimeSubTotal(vbtst, memberId, surfDate, interval);
					SurfDAOUtil.createTimeSubTotal(vbtst);
				} else {
					vbtst.setSecond(new Long(interval.longValue()
							+ vbtst.getSecond().longValue()));
					SurfDAOUtil.updateTimeSubTotal(vbtst);
				}
			} else {
				VBTimeSubTotal vbtst = new VBTimeSubTotal();
				long tmpInterval = SurfDAOUtil.getTimeSubTotal(scheduledTable,
						memberId, surfDate);

				if (tmpInterval < 0) {
					initVBTimeSubTotal(vbtst, memberId, surfDate, interval);
					SurfDAOUtil.createTimeSubTotal(vbtst, scheduledTable);
				} else {
					initVBTimeSubTotal(vbtst, memberId, surfDate, new Long(interval
							.longValue()
							+ tmpInterval));
					SurfDAOUtil.updateTimeSubTotal(vbtst, scheduledTable);
				}
			}
		}
	}

	public void deleteTimeTrack(VBTimeTrack timeTrack) {
		SurfDAOUtil.deleteTimeTrack(timeTrack);
	}

	public VBTimeTrack getTimeTrack(Long trackId) {
		return SurfDAOUtil.getTimeTrack(trackId);
	}

	public List listTimeTrack(Long memberId) {
		return SurfDAOUtil.listTimeSubTotal(memberId);
	}

	public void updateTimeTrack(VBTimeTrack timeTrack) {
		SurfDAOUtil.updateTimeTrack(timeTrack);
	}

	// url track
	public void createURLTrack(VBURLTrack urlTrack) {
		SurfDAOUtil.createURLTrack(urlTrack);
	}

	public void deleteURLTrack(VBURLTrack urlTrack) {
		SurfDAOUtil.deleteURLTrack(urlTrack);
	}

	public VBURLTrack getURLTrack(Long trackId) {
		return SurfDAOUtil.getURLTrack(trackId);
	}

	public List listURLTrack(Long memberId) {
		return SurfDAOUtil.listTimeSubTotal(memberId);
	}

	public void updateURLTrack(VBURLTrack urlTrack) {
		SurfDAOUtil.updateURLTrack(urlTrack);
	}

}
