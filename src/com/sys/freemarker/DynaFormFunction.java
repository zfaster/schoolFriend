package com.sys.freemarker;

import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sys.system.FieldType;
import com.sys.system.FormModel;
import com.sys.system.Renewable;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 动态创建添加页面
 * @author ZZY
 *
 */
public class DynaFormFunction {
	private static String defaultTemplate = "add_input.ftl";
	/**
	 * 本来想当缓存使用，测试结果在不适用缓存时花费时间就是0所以可不是用缓存,虽然使用反射效率下降但是使用次数不多
	 * 不会对新能影响太大
	 */
	private final static Map<String,Map<String,Renewable>> modelForm = new HashMap<String,Map<String,Renewable>>();
	public static String form(Object targetObject ){
		try {
			String className = targetObject.getClass().getName();
			if(className.indexOf("_") != -1){
				className = className.substring(0,className.indexOf("_"));
			}
			
			Configuration cfg = FreeMarkerUtil.getConfiguration();
			
			Template template = null;
			template = cfg.getTemplate(defaultTemplate);
			
			List<FormModel> params= new ArrayList<FormModel>();
			if(modelForm.get(className)==null){
				setParamNoCache(targetObject, className, params);
			}else{
				setParamFromCache(targetObject, className, params);
			}
			
			
			template.setEncoding("UTF-8");
			template.setOutputEncoding("utf-8");
			//最终输出位置
			Writer out = new StringWriter();
			//定义数据模型
			Map rootMap = new HashMap ();
			rootMap.put("params", params);
			//模板引擎解释模板
			template.process(rootMap, out);
			//modelForm.put(className, out.toString());
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	private static void setParamNoCache(Object targetObject, String className,
			List<FormModel> params) throws Exception{
		Class object = Class.forName(className);
		Field[] fields = object.getDeclaredFields();
		modelForm.put(className, new LinkedHashMap<String, Renewable>());
		for(Field field:fields){
			Renewable sup = field.getAnnotation(Renewable.class);
			if(sup != null && sup.value()){
				modelForm.get(className).put(field.getName(), sup);
				FormModel model = new FormModel();
				model.setLabel(sup.label());
				model.setType(sup.type());
				if(!model.getType().equals(FieldType.FILE))
					model.setConditions(sup.condition());
				model.setMaxLength(sup.maxLength());
				if(sup.type().equals(FieldType.FILE)){
					model.setProperName(field.getName());
				}else{
					model.setProperName("object."+field.getName());
				}
				  
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(),object);
				Method m = pd.getReadMethod();
				model.setValue(m.invoke(targetObject, null));
				params.add(model);
			}
		}
	}
	
	private static void setParamFromCache(Object targetObject, String className,
			List<FormModel> params) throws Exception{
		Map<String,Renewable> anno = modelForm.get(className);
		for(String feildName : modelForm.get(className).keySet()){
			Renewable sup = anno.get(feildName);
			FormModel model = new FormModel();
			model.setLabel(sup.label());
			model.setType(sup.type());
			if(!model.getType().equals(FieldType.FILE))
				model.setConditions(sup.condition());
			model.setMaxLength(sup.maxLength());
			if(sup.type().equals(FieldType.FILE)){
				model.setProperName(feildName);
			}else{
				model.setProperName("object."+feildName);
			}
			Class object = Class.forName(className);  
			PropertyDescriptor pd = new PropertyDescriptor(feildName,object);
			Method m = pd.getReadMethod();
			model.setValue(m.invoke(targetObject, null));
			params.add(model);
		}
	}

}
