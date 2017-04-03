package com.sys.service.employee.impl;

import javax.annotation.PostConstruct;

import com.sys.enums.AdminType;
import org.springframework.stereotype.Service;

import com.sys.bean.privilege.Employee;
import com.sys.service.base.DaoSupport;
import com.sys.service.employee.EmployeeService;
import com.sys.utils.MD5;
@Service("employeeService")
public class EmployeeServiceImpl extends DaoSupport<Employee> implements
		EmployeeService {
	
	
	public boolean checkUsernameExists(String username ,int employeeid) {
		int count = ((Long)this.getSession().createQuery("select count(o) from "+getEntityName(Employee.class)+" o where o.username = ? and o.id != ? ")
			.setParameter(0, username).setParameter(1, employeeid).uniqueResult()).intValue();
		return count > 0;
	}
	
	
	public Employee login(String username, String password) {
		Employee employee = (Employee)this
				.getSession()
				.createQuery(
						"select o from "+getEntityName(Employee.class)+" o where o.username = ? and o.password = ?")
				.setParameter(0, username)
				.setParameter(1, password).uniqueResult();
		return employee;
	}
	@Override
	@PostConstruct
	public void initAdmin() {
		if(this.getCount()==0){
			Employee employee = new Employee();
			employee.setUsername("admin");
			employee.setPassword("admin");
			employee.setRealname("超级管理员");
			employee.setSex(1);
			employee.setType(AdminType.SUPER_ADMIN);
			this.save(employee);
		}	
	}
	@Override
	public void modifyPassword(int employeeId, String oldPassword,
			String newPassword) {
		Employee employee = this.find(employeeId);
		String realword = employee.getPassword();
		if(!oldPassword.equals(realword)){
			throw new RuntimeException("原始密码输入错误");
		}
		employee.setPassword(newPassword);
	}

}
