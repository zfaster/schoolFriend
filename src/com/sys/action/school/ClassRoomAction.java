package com.sys.action.school;

import com.sys.bean.school.ClassRoom;
import com.sys.bean.school.Student;
import com.sys.service.base.DAO;
import com.sys.web.action.BaseAction;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("prototype")
public class ClassRoomAction extends BaseAction<ClassRoom> {

	private String actionPath = "control/school/classRoom";
	private String roomName;

	@Override
	public String execute() throws Exception {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "desc");
		StringBuffer whereSql = new StringBuffer(" 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(roomName != null && !"".equals(roomName)){
			whereSql.append("and o.name like ? ");
			params.add("%"+roomName+"%");
		}
		pm = baseService.findScrollData(
				orderBy,whereSql.toString(),params.toArray());
		setPageInfo();
		return SUCCESS;
	}


	@Resource(name="classRoomService")
	public void setBaseService(DAO baseService) {
		this.baseService = baseService;
	}
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		try {
			this.roomName = new String(roomName.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
