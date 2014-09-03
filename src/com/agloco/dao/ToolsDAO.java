package com.agloco.dao;

import java.util.List;

import com.agloco.dao.hibernate.ToolsDAOHibernate;
import com.agloco.model.VBTools;
import com.agloco.model.VBToolsDTrack;
import com.agloco.model.VBToolsPathes;

/**
 * 
 * @author terry_zhao
 * @date Apr 23, 2007
 * @see ToolsDAOHibernate
 */
public interface ToolsDAO {

	//tools
	public void createTool(VBTools tool);
	public void deleteTool(VBTools tool);
	public void updateTool(VBTools tool);
	public VBTools getTool(Long id);
	public List<VBTools> listTools();
	public List<VBTools> listToolsByOS(String os);
	
	//tools pathes
	public void createToolsPath(VBToolsPathes tPath);
	public void deleteToolsPath(VBToolsPathes tPath);
	public void updateToolsPath(VBToolsPathes tPath);
	public List<VBToolsPathes> listToolsPathes(Long toolId);
	
	//tools download track
	public void createToolDownloadTrack(VBToolsDTrack track);
	public void deleteToolDownloadTrack(VBToolsDTrack track);
	public void updateToolDownloadTrack(VBToolsDTrack track);
	public VBToolsDTrack getToolDownloadTrack(Long trackId);
	public List<VBToolsDTrack> listToolsDownloadTrack(Long memberId);
	
	
}
