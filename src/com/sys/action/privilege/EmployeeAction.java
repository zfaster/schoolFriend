package com.sys.action.privilege;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.sys.enums.AdminType;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.bean.privilege.Employee;
import com.sys.service.base.DAO;
import com.sys.service.employee.EmployeeService;
import com.sys.web.action.BaseAction;
@Controller
@Scope("prototype")
public class EmployeeAction extends BaseAction<Employee> {

	private String realname;
	private String actionPath = "control/privilege/employee";
	private String username;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private boolean state;
	private String password;
	private int employeeId;
	private int[] groupId;
	private String error;
	private boolean select;
	@Override
	public String execute() throws Exception {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("realname", "asc");
		orderBy.put("id", "asc");
		StringBuffer whereSql = new StringBuffer(" 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(realname != null && !"".equals(realname)){
			whereSql.append("and o.realname like ? ");
			params.add("%"+realname+"%");
		}
		if(select){
			whereSql.append("and o.type = ? ");
			params.add(AdminType.CLASS_ADMIN);
			whereSql.append("and not exists (select cr from ClassRoom cr where cr.employee.id = o.id)");
		}
		pm = baseService.findScrollData(
				orderBy,whereSql.toString(),params.toArray());
		setPageInfo();
		if(select){
			return "select";
		}
		return SUCCESS;
	}
	
	public String checkUsername() throws Exception {
		boolean isExists = ((EmployeeService)baseService).checkUsernameExists(username,employeeId);
		sendMessage(ServletActionContext.getResponse(), isExists+"");
		return null;
	}
	public String modifyPassword() throws Exception {
		String oldPassword = ServletActionContext.getRequest().getParameter("oldPassword");
		((EmployeeService)baseService).modifyPassword(employeeId, oldPassword, object.getPassword());
		return "update_success";
	}
	
	public String update() throws Exception {
		if(id == null){
			baseService.save(object);
			if(image != null){
				if(!checkFileType(imageContentType,imageFileName)) throw new RuntimeException("请上传图片文件");
				String path = ServletActionContext.getServletContext().getRealPath(object.getSavePath());
				imageFileName = UUID.randomUUID().toString()+imageFileName.substring(imageFileName.lastIndexOf("."));//生成UUID文件名
				File imageDir = new File(path);
				if(!imageDir.exists()) imageDir.mkdirs();//创建保存图片的文件夹
				FileUtils.copyFile(image, new File(path+"/"+imageFileName));
				object.setImage(imageFileName);
				baseService.update(object);
			}
		}else{
			Employee oldEmployee = baseService.find(id);
			if(oldEmployee.getImagePath() != null && image!=null){
				String oldPath = ServletActionContext.getServletContext().getRealPath(oldEmployee.getImagePath());
				File oldFile = new File(oldPath);
				if(oldFile.exists()){
					oldFile.delete();
				}
			}
			injectProperties(oldEmployee, object);
			if(image != null){
				if(!checkFileType(imageContentType,imageFileName)) throw new RuntimeException("请上传图片文件");
				String path = ServletActionContext.getServletContext().getRealPath(oldEmployee.getSavePath());
				imageFileName = UUID.randomUUID().toString()+imageFileName.substring(imageFileName.lastIndexOf("."));//生成UUID文件名
				File imageDir = new File(path);
				if(!imageDir.exists()) imageDir.mkdirs();//创建保存图片的文件夹
				FileUtils.copyFile(image, new File(path+"/"+imageFileName));
				oldEmployee.setImage(imageFileName);
			}
			baseService.update(oldEmployee);
		}
		return "update_success";
	}
	/**
	 * 用户退出
	 * @return
	 * @throws Exception
	 */
	public String quit() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	public String login() throws Exception {
		Employee e = ((EmployeeService)baseService).login(username, password);
		if(e == null){
			error = "用户名或密码错误";
			return "login_fail";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("employee",e);
			return "login_success";
		}
	}
	@Resource(name="employeeService")
	public void setBaseService(DAO baseService) {
		this.baseService = baseService;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		try {
			this.realname = new String(realname.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public File getImageName() {
		return image;
	}
	public void setImageName(File imageName) {
		this.image = imageName;
	}
	public String getImageNameFileName() {
		return imageFileName;
	}
	public void setImageNameFileName(String imageNameFileName) {
		this.imageFileName = imageNameFileName;
	}
	public String getImageNameContentType() {
		return imageContentType;
	}
	public void setImageNameContentType(String imageNameContentType) {
		this.imageContentType = imageNameContentType;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int[] getGroupId() {
		return groupId;
	}
	public void setGroupId(int[] groupId) {
		this.groupId = groupId;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}
}
