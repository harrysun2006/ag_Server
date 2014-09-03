package com.agloco.dao;

import java.util.List;

import com.agloco.model.VBFiles;

public interface FilesDAO {
	
	public void createFile(VBFiles files);
	public void updateFile(VBFiles files);
	public void deleteFile(String viewbarId);
	public List listFiles(String viewbarId);
	public List listFiles(String viewbarId, String osVersion);
	
}
