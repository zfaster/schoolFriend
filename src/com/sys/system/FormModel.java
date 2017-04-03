package com.sys.system;
/**
 * 用于存放表单显示数据
 * @author ZZY
 *
 */
public class FormModel {

	/**
	 * 表单名称
	 */
	private String label;
	/**
	 * 表单类型
	 */
	private FieldType type;

	/**
	 * 属性名称
	 */
	private String properName;
	/**
	 * 属性对应的值
	 */
	private Object value;
	/**
	 * 用于限制表单输入的条件
	 */
	private String[] conditions;
	/**
	 * 限制表单最大长度，默认255
	 */
	private int maxLength;
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public String getProperName() {
		return properName;
	}

	public void setProperName(String properName) {
		this.properName = properName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String[] getConditions() {
		return conditions;
	}

	public void setConditions(String[] conditions) {
		this.conditions = conditions;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	
}
