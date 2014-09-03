package com.agloco.service.impl;

import java.util.List;

import com.agloco.dao.util.FilesDAOUtil;
import com.agloco.model.VBFiles;
import com.agloco.service.FilesService;

public class FilesServiceImpl extends BaseServiceImpl implements FilesService {

	
	public List listFiles(String viewbarId) {
		return FilesDAOUtil.listFiles(viewbarId);
	}

	public List listFiles(String viewbarId, String osVersion) {
		return FilesDAOUtil.listFiles(viewbarId, osVersion);
	}

	public void createFile(VBFiles files) {
		FilesDAOUtil.createFile(files);
	}

	public void deleteFile(String viewbarId) {
		FilesDAOUtil.deleteFile(viewbarId);
	}

	public void updateFile(VBFiles files) {
		FilesDAOUtil.updateFile(files);
	}
}
