package com.sys.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtil {
	private static Configuration cfg = new Configuration();
	static{
		//定义模板位置，从类路径中加载模板
		cfg.setTemplateLoader(new ClassTemplateLoader(FreeMarkerUtil.class, "templates"));
		//设置对象包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		//设置异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		cfg.setDefaultEncoding("utf-8");
	}
	
	public static Configuration getConfiguration(){
		return cfg;
	}
}
