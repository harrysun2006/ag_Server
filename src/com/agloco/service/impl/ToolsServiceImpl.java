package com.agloco.service.impl;

import java.util.List;

import com.agloco.dao.util.ToolsDAOUtil;
import com.agloco.model.VBTools;
import com.agloco.model.VBToolsDTrack;
import com.agloco.model.VBToolsPathes;
import com.agloco.service.ToolsService;
/**
 * 
 * @author terry_zhao
 * @date Apr 23, 2007
 */
public class ToolsServiceImpl implements ToolsService {

	//tools
	public void createTool(VBTools tool){
		ToolsDAOUtil.createTool(tool);
	}
	public void deleteTool(VBTools tool){
		ToolsDAOUtil.deleteTool(tool);
	}
	public void updateTool(VBTools tool){
		ToolsDAOUtil.updateTool(tool);
	}
	public VBTools getTool(Long id){
		return ToolsDAOUtil.getTool(id);
	}
	public List<VBTools> listTools(){
		return ToolsDAOUtil.listTools();
	}
	public List<VBTools> listToolsByOS(String os){
		return ToolsDAOUtil.listToolsByOS(os);
	}
	
	//tools pathes
	public void createToolsPath(VBToolsPathes tPath){
		ToolsDAOUtil.createToolsPath(tPath);
	}
	public void deleteToolsPath(VBToolsPathes tPath){
		ToolsDAOUtil.deleteToolsPath(tPath);
	}
	public void updateToolsPath(VBToolsPathes tPath){
		ToolsDAOUtil.updateToolsPath(tPath);
	}
	public List<VBToolsPathes> listToolsPathes(Long toolId){
		return ToolsDAOUtil.listToolsPathes(toolId);
	}
	
	//tools download track
	public void createToolDownloadTrack(VBToolsDTrack track){
		ToolsDAOUtil.createToolDownloadTrack(track);
	}
	public void deleteToolDownloadTrack(VBToolsDTrack track){
		ToolsDAOUtil.deleteToolDownloadTrack(track);
	}
	public void updateToolDownloadTrack(VBToolsDTrack track){
		ToolsDAOUtil.updateToolDownloadTrack(track);
	}
	public VBToolsDTrack getToolDownloadTrack(Long trackId){
		return ToolsDAOUtil.getToolDownloadTrack(trackId);
	}
	public List<VBToolsDTrack> listToolsDownloadTrack(Long memberId){
		return ToolsDAOUtil.listToolsDownloadTrack(memberId);
	}
}
