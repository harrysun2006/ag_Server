package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.FilesDAO;
import com.agloco.model.VBFiles;
import com.agloco.spring.SpringUtil;

public class FilesDAOUtil {

	private FilesDAO filesDAO;

	public static void createFile(VBFiles files){
		getFilesDAO().createFile(files);
	}
	public static void updateFile(VBFiles files){
		getFilesDAO().updateFile(files);
	}
	public static void deleteFile(String viewbarId){
		getFilesDAO().deleteFile(viewbarId);
	}
	
	public static List listFiles(String viewbarId) {
		return getFilesDAO().listFiles(viewbarId);
	}

	public static List listFiles(String viewbarId, String osVersion) {
		return getFilesDAO().listFiles(viewbarId, osVersion);
	}

	public static FilesDAO getFilesDAO() {
		ApplicationContext ctx = SpringUtil.getContext();
		FilesDAOUtil util = (FilesDAOUtil) ctx.getBean(FilesDAOUtil.class
				.getName());
		return util.filesDAO;
	}

	public void setFilesDAO(FilesDAO filesDAO) {
		this.filesDAO = filesDAO;
	}

}
