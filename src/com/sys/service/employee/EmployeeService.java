package com.sys.service.employee;

import com.sys.bean.privilege.Employee;
import com.sys.service.base.DAO;

public interface EmployeeService extends DAO<Employee> {
	/**
	 * 验证用户名是否存在
	 * @return true表示存在, false表示不存在
	 */
	public boolean checkUsernameExists(String username,int employeeid);
	/**
	 * 员工登入验证
	 * @param username
	 * @param password
	 * @return
	 */
	public Employee login(String username, String password);
	/**
	 * 初始化系统管理员
	 */
	public void initAdmin();
	public void modifyPassword(int employeeId,String oldPassword,String newPassword);
}
