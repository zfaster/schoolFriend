package com.sys.action.school;

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
import java.util.*;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	private String actionPath = "control/school/student";
	private String username;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private String realname;

	@Override
	public String execute() throws Exception {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "desc");
		StringBuffer whereSql = new StringBuffer(" 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(realname != null && !"".equals(realname)){
			whereSql.append("and o.realname like ? ");
			params.add("%"+realname+"%");
		}
		pm = baseService.findScrollData(
				orderBy,whereSql.toString(),params.toArray());
		setPageInfo();
		return SUCCESS;
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
			Student oldEmployee = baseService.find(id);
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

	@Resource(name="studentService")
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


}
