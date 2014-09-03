package com.agloco.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.ToolsDAO;
import com.agloco.model.VBTools;
import com.agloco.model.VBToolsDTrack;
import com.agloco.model.VBToolsPathes;
/**
 * 
 * @author terry_zhao
 * @date Apr 23, 2007
 */
public class ToolsDAOHibernate extends HibernateDaoSupport implements ToolsDAO {

	//tools
	public void createTool(VBTools tool) {
		tool.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(tool);
	}

	public void deleteTool(VBTools tool) {
		getHibernateTemplate().delete(tool);
	}
	
	public VBTools getTool(Long id) {
		return (VBTools)getHibernateTemplate().get(VBTools.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<VBTools> listTools() {
		return getHibernateTemplate().find("from VBTools vt order by vt.id");
	}

	@SuppressWarnings("unchecked")
	public List<VBTools> listToolsByOS(String os) {
		return getHibernateTemplate().find("from VBTools vt where vt=? order by vt.id", os);
	}
	
	public void updateTool(VBTools tool) {
		getHibernateTemplate().saveOrUpdate(tool);
	}
	
	//tools download track
	public void createToolDownloadTrack(VBToolsDTrack track) {
		track.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(track);
	}

	public void deleteToolDownloadTrack(VBToolsDTrack track) {
		getHibernateTemplate().delete(track);
	}

	public VBToolsDTrack getToolDownloadTrack(Long trackId) {
		return (VBToolsDTrack)getHibernateTemplate().get(VBToolsDTrack.class, trackId);
	}

	@SuppressWarnings("unchecked")
	public List<VBToolsDTrack> listToolsDownloadTrack(Long memberId) {
		return getHibernateTemplate().find("from VBToolsDTrack tdt where tdt.memberId=? order by tdt.id", memberId);
	}
	public void updateToolDownloadTrack(VBToolsDTrack track) {
		getHibernateTemplate().saveOrUpdate(track);
	}
	
	//tools pathes
	public void createToolsPath(VBToolsPathes tPath) {
		getHibernateTemplate().save(tPath);
	}
	public void deleteToolsPath(VBToolsPathes tPath) {
		getHibernateTemplate().delete(tPath);
	}
	@SuppressWarnings("unchecked")
	public List<VBToolsPathes> listToolsPathes(Long toolId) {
		return getHibernateTemplate().find("from VBToolsPathes tp where tp.toolId=?", toolId);
	}
	public void updateToolsPath(VBToolsPathes tPath) {
		getHibernateTemplate().saveOrUpdate(tPath);
	}

}
