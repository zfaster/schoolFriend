package com.sys.action.school;

import com.sys.bean.privilege.Employee;
import com.sys.bean.school.IndexImage;
import com.sys.service.base.DAO;
import com.sys.web.action.BaseAction;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.UUID;

@Controller
@Scope("prototype")
public class IndexImageAction extends BaseAction<IndexImage> {

	private String actionPath = "control/school/indexImage";
	private File image;
	private String imageFileName;
	private String imageContentType;
	@Override
	public String execute() throws Exception {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "desc");
		pm = baseService.findScrollData(
				orderBy,null,null);
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
			IndexImage oldEmployee = baseService.find(id);
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
	@Resource(name="indexImageService")
	public void setBaseService(DAO baseService) {
		this.baseService = baseService;
	}
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
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

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
}
