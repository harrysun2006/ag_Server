package com.agloco.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import com.agloco.web.session.SessionContext;

public class PerformanceUtil {

	public static long VB_DOWNLOAD_COUNT;

	static {
		VB_DOWNLOAD_COUNT = 0;
		Thread cpuPerformanceThread = new Thread(new CpuPerformanceThread());
		cpuPerformanceThread.start();
		Thread memPerformanceThread = new Thread(new MemPerformanceThread());
		memPerformanceThread.start();
	}

	public static void main(String[] arg) throws Exception {
		System.out.println(getCpuPerformance());
		System.out.println(getMemPerformance());
		Thread.sleep(200000);
	}

	public static long getCpuPerformance() {
		return CpuPerformanceThread.CPU_PERFORMANCE;
	}

	public static long getMemPerformance() {
		return MemPerformanceThread.MEM_PERFORMANCE;
	}

	public static long getDownloadCount() {
		return VB_DOWNLOAD_COUNT;
	}

	public static long getCurrentOnlineCount() {
		return SessionContext.size();
	}

	private static final class CpuPerformanceThread implements Runnable {

		public static long CPU_PERFORMANCE = 0;

		private static Date cpuLastModifyDate = null;

		private static Date cpuCurrentModifyDate = null;

		private static String cpuFileName = "/proc/stat";

		private static long REFRESH_INTERVAL = 5000;

		private static boolean force = true;

		public void run() {
			while (true) {
				try {
					File cpuFile = new File(cpuFileName);
					if (!cpuFile.exists()) {
						return;
					}
					if (cpuLastModifyDate == null) {
						cpuLastModifyDate = new Date(cpuFile.lastModified());
						cpuCurrentModifyDate = new Date(cpuFile.lastModified());
						getCPUPerformance(cpuFile);
					} else {
						cpuCurrentModifyDate = new Date(cpuFile.lastModified());
						if (force
								|| !cpuCurrentModifyDate
										.equals(cpuLastModifyDate)) {
							cpuLastModifyDate = cpuCurrentModifyDate;
							getCPUPerformance(cpuFile);
						}
					}
				Thread.sleep(REFRESH_INTERVAL);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Cpu Performance error happend");
				}
			}
		}

		private void getCPUPerformance(File cpuFile) throws Exception {
			long cpuInfo = 0;
			long tot_frme = 0;
			float scale;
			int arrayNo = 0;
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(cpuFile));
				String str = br.readLine();
				if (str != null) {
					StringTokenizer token = new StringTokenizer(str);
					token.nextToken();
					while (token.hasMoreTokens()) {
						cpuParameterStruct.currentParameters[arrayNo++] = Long
								.parseLong(token.nextToken());
					}

					if (cpuParameterStruct.lastParameters[0] == 0
							&& cpuParameterStruct.lastParameters[1] == 0
							&& cpuParameterStruct.lastParameters[2] == 0
							&& cpuParameterStruct.lastParameters[3] == 0
							&& cpuParameterStruct.lastParameters[4] == 0
							&& cpuParameterStruct.lastParameters[5] == 0
							&& cpuParameterStruct.lastParameters[6] == 0
							&& cpuParameterStruct.lastParameters[7] == 0) {
						for (int j = 0; j < cpuParameterStruct.lastParameters.length; j++) {
							cpuParameterStruct.lastParameters[j] = cpuParameterStruct.currentParameters[j];
						}
						CPU_PERFORMANCE = cpuInfo;
						return;
					}
					for (int i = 0; i < cpuParameterStruct.lastParameters.length; i++) {
						cpuParameterStruct.frme[i] = cpuParameterStruct.currentParameters[i]
								- cpuParameterStruct.lastParameters[i];
					}

					for (int i = 0; i < cpuParameterStruct.lastParameters.length; i++) {
						tot_frme = tot_frme + cpuParameterStruct.frme[i];
					}
					if (tot_frme < 1) {
						tot_frme = 1;
					}
					scale = (float) (100.0 / (float) tot_frme);
					cpuInfo = (long) (cpuParameterStruct.frme[3] * scale);
					for (int j = 0; j < cpuParameterStruct.lastParameters.length; j++) {
						cpuParameterStruct.lastParameters[j] = cpuParameterStruct.currentParameters[j];
					}
				}
			} catch (Exception ex) {
				throw ex;
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			CPU_PERFORMANCE = cpuInfo;
		}
	}

	private static final class MemPerformanceThread implements Runnable {

		public static long MEM_PERFORMANCE = 0;

		private static Date memLastModifyDate = null;

		private static Date memCurrentModifyDate = null;

		private static String memFileName = "/proc/meminfo";

		private static long REFRESH_INTERVAL = 5000;

		private static boolean force = true;

		public void run() {
			while (true) {
				try {
					File memFile = new File(memFileName);
					if (!memFile.exists()) {
						return;
					}
					if (memLastModifyDate == null) {
						memLastModifyDate = new Date(memFile.lastModified());
						memCurrentModifyDate = new Date(memFile.lastModified());
						getMemPerformance(memFile);
					} else {
						memCurrentModifyDate = new Date(memFile.lastModified());
						if (force
								|| !memCurrentModifyDate
										.equals(memLastModifyDate)) {
							memLastModifyDate = new Date(memFile.lastModified());
							getMemPerformance(memFile);
						}
					}
					Thread.sleep(REFRESH_INTERVAL);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Mem Performance error happend");
				}
			}
		}

		private void getMemPerformance(File memFile) throws Exception {
			long memInfo = 0;
			FileReader fr = null;
			BufferedReader br = null;
			long memTotal = 0;
			long memUsed = 0;
			int count = 0;
			try {
				fr = new FileReader(memFile);
				br = new BufferedReader(fr);
				String str = null;
				StringTokenizer token = null;
				
				while ((str = br.readLine()) != null){
					token = new StringTokenizer(str);
					String temp = token.nextToken();
					if (temp.equals("MemTotal:")){
						count = count + 1;
						memTotal = Long.parseLong(token.nextToken());
						memUsed = memTotal;
					}else if (temp.equals("MemFree:")){
						memUsed = memUsed - Long.parseLong(token.nextToken());
						count = count + 1;
					}else if (temp.equals("Buffers:")){
						memUsed = memUsed - Long.parseLong(token.nextToken());
						count = count + 1;
					}else if (temp.equals("Cached:")){
						memUsed = memUsed - Long.parseLong(token.nextToken());
						count = count + 1;
					}
					if (count == 4){ 
						break;
					}
				}
				
				memInfo = (memUsed * 100) / memTotal;
			} catch (Exception ex) {
				throw ex;
			} finally {
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			MEM_PERFORMANCE = memInfo;
		}
	}

	private static final class cpuParameterStruct {
		public static long[] lastParameters = new long[] { 0, 0, 0, 0, 0, 0, 0 , 0};

		public static long[] currentParameters = new long[] { 0, 0, 0, 0, 0, 0,
				0 , 0};

		public static long[] frme = new long[] { 0, 0, 0, 0, 0, 0, 0 , 0};
	}
}
