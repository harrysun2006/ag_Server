package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.ToolsDAO;
import com.agloco.model.VBTools;
import com.agloco.model.VBToolsDTrack;
import com.agloco.model.VBToolsPathes;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author terry_zhao
 * @date Apr 23, 2007
 */
public class ToolsDAOUtil {

	//tools
	public static void createTool(VBTools tool) {
		getToolsDAO().createTool(tool);
	}

	public static void deleteTool(VBTools tool) {
		getToolsDAO().deleteTool(tool);
	}
	
	public static VBTools getTool(Long id) {
		return getToolsDAO().getTool(id);
	}
	
	public static List<VBTools> listTools() {
		return getToolsDAO().listTools();
	}

	public static List<VBTools> listToolsByOS(String os) {
		return getToolsDAO().listToolsByOS(os);
	}
	
	public static void updateTool(VBTools tool) {
		getToolsDAO().updateTool(tool);
	}
	
	//tools download track
	public static void createToolDownloadTrack(VBToolsDTrack track) {
		getToolsDAO().createToolDownloadTrack(track);
	}

	public static void deleteToolDownloadTrack(VBToolsDTrack track) {
		getToolsDAO().deleteToolDownloadTrack(track);
	}

	public static VBToolsDTrack getToolDownloadTrack(Long trackId) {
		return getToolsDAO().getToolDownloadTrack(trackId);
	}

	public static List<VBToolsDTrack> listToolsDownloadTrack(Long memberId) {
		return getToolsDAO().listToolsDownloadTrack(memberId);
	}
	public static void updateToolDownloadTrack(VBToolsDTrack track) {
		getToolsDAO().updateToolDownloadTrack(track);
	}
	
	//tools pathes
	public static void createToolsPath(VBToolsPathes tPath) {
		getToolsDAO().createToolsPath(tPath);
	}
	public static void deleteToolsPath(VBToolsPathes tPath) {
		getToolsDAO().deleteToolsPath(tPath);
	}
	public static List<VBToolsPathes> listToolsPathes(Long toolId) {
		return getToolsDAO().listToolsPathes(toolId);
	}
	public static void updateToolsPath(VBToolsPathes tPath) {
		getToolsDAO().updateToolsPath(tPath);
	}
	
	private static ToolsDAO getToolsDAO(){
		ApplicationContext ctx = SpringUtil.getContext();
		ToolsDAOUtil util = (ToolsDAOUtil)ctx.getBean(ToolsDAOUtil.class.getName());
		return util.toolsDAO;
	}
	public void setToolsDAO(ToolsDAO toolsDAO) {
		this.toolsDAO = toolsDAO;
	}
	private ToolsDAO toolsDAO;
}
