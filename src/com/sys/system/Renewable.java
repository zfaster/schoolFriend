package com.sys.system;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//元注解,指定生命周期在运行时
@Target(ElementType.FIELD)//指定只能放在类字段上
/**
 * 用于指定类中的字段是否要在更新页面时显示
 * @author ZZY
 *
 */
public @interface Renewable {
	/**
	 * 指定改字段是否可以更新
	 * 默认为true，即需要更新
	 * @return
	 */
	boolean value() default true;
	/**
	 * 指定显示的类型
	 * @return
	 */
	FieldType type() default FieldType.TEXT; 
	/**
	 * 指定显示的label名称
	 * @return
	 */
	String label() default "";
	/**
	 * 用于限制表单可输入的条件，配合Condition类使用
	 * @return
	 */
	String[] condition() default "";
	/**
	 * 限制表单长度，默认255
	 * @return
	 */
	int maxLength() default 255;
	
}
