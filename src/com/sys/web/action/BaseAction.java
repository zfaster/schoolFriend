package com.sys.web.action;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import com.sys.service.base.DAO;
import com.sys.system.FieldType;
import com.sys.system.PagerModel;
import com.sys.system.Renewable;
import com.sys.system.SystemContext;
import org.apache.struts2.ServletActionContext;


public class BaseAction<T> extends ActionSupport {

	private static Properties allowTypeFile; 
	private int pageSize;
	private int offset;
	protected PagerModel pm;
	protected Integer id;
	protected DAO<T> baseService;
	protected Integer[] ids;
	public T object ;
	public int menu;
	static{
		if(allowTypeFile == null) 
			allowTypeFile = new Properties();
		try {
			allowTypeFile.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("allowfiletype.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("配置文件不存在");
		}
	}
	@SuppressWarnings("unchecked")
	public BaseAction() {
		//如果不建出对应的实例，在进行添加和修改时将报错提示找不到对应的属性
		/*struts2通过反射技术调用与请求参数同名的属性的setter方法来获取请求参数值，
		在商城项目中baseAction的Object类用泛型声明的之所以需要主动在action构造方法中将object新建出来，
		很可能是struts自身无法获得object的真实属性，也就无法自动注入属性值而报错*/
		try {
			object = (T) getClassType().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打开添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addInput() throws Exception {
		return "input";
	}

	/**
	 * 打开更新页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateInput() throws Exception {
		if(id!=null){
			object = (T) baseService.find( id);
		}
		return "input";
	}
	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(id == null){
			baseService.save(object);
		}else{
			T oldObj = baseService.find(id);
			
			injectProperties(oldObj, object);
			
			baseService.update(oldObj);
		}
		return "update_success";
	}
	
	/**
	 * 自动注入可修改的属性值，默认action使用的泛型
	 * @param oldObject
	 * @param newObject
	 * @return
	 */
	public T injectProperties(Object oldObject,Object newObject){
		Class<T> clazz = getClassType();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Renewable anno = field.getAnnotation(Renewable.class);
			if(anno != null && !anno.type().equals(FieldType.FILE)){
				try {
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
					Method read = pd.getReadMethod();
					Object result = read.invoke(newObject, null);
					Method write = pd.getWriteMethod();
					write.invoke(oldObject, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return (T)oldObject;
	}
	/**
	 * 转换对象内容的字符编码
	 * @param target
	 * @param encoding
	 * @return
	 */
	public static Object changeObjectEncoding(Object target,String encoding){
		Class clazz = target.getClass();
		Field[] fields = target.getClass().getDeclaredFields();
		for(Field f : fields){
			if(f.getType() == String.class){
				try {
					PropertyDescriptor pd = new PropertyDescriptor(f.getName(),clazz);
					Method read = pd.getReadMethod();
					String result = (String)read.invoke(target, null);
					if(result == null){
						continue;
					}
					result = new String(result.getBytes("iso8859-1"),encoding);
					Method write = pd.getWriteMethod();
					write.invoke(target, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return target;
	}
	public void delete() throws Exception {
		baseService.delete(ids);
		sendMessage(ServletActionContext.getResponse(),"删除成功");
	}
	/**
	 * 自动注入可修改的属性值,指定的类型
	 * @param clazz
	 * @param oldObject
	 * @param newObject
	 * @return
	 */
	public static Object injectProperties(Class clazz ,Object oldObject,Object newObject){
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Renewable anno = field.getAnnotation(Renewable.class);
			if(anno != null && !anno.type().equals(FieldType.FILE)){
				try {
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
					Method read = pd.getReadMethod();
					Object result = read.invoke(newObject, null);
					Method write = pd.getWriteMethod();
					write.invoke(oldObject, result);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return oldObject;
	}
	
	/**
	 * 获得泛型T的实际类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getClassType(){
		Type types = this.getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType)types;
		Class clazz = (Class)pType.getActualTypeArguments()[0];
		return clazz;
	}
	
	public static byte[] getBytesFromFile(File file) {
		byte[] ret = null;
		try {
			if (file == null) {
				return null;
			}
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
			byte[] b = new byte[4096];
			int n;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			in.close();
			out.close();
			ret = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * AJAX向前台发送数据
	 * @param response
	 * @param message
	 */
	public static void sendMessage(HttpServletResponse response , String message){
		try {
			response.setCharacterEncoding("utf-8");
			response.setHeader("param", "no-cache");
			response.setHeader("cache-control", "no-cache");
			Writer writer = response.getWriter();
			writer.write(message);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setPageInfo() {
		offset = SystemContext.getOffset();
		pageSize = SystemContext.getPageSize();
	}

	public DAO<T> getBaseService() {
		return baseService;
	}


	public void setPm(PagerModel<Object> pm) {
		this.pm = pm;
	}

	public PagerModel<Object> getPm() {
		return pm;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	/**
	 * 检查上传文件是否合法
	 * @param fileType
	 * @param fileName
	 * @return
	 */
	public static boolean checkFileType(String fileType,String fileName) {
		String property = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).toLowerCase();
		Set props = allowTypeFile.keySet();
		List<String> allowType = new ArrayList<String>();
		for(Object key : props){
			String typeStr = (String)allowTypeFile.get(key);
			String[] type = typeStr.split(",");
			allowType.addAll(Arrays.asList(type));
		}
		return props.contains(property) && allowType.contains(fileType);
	}



	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMenu() {
		return menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}
	
}
