package com.sys.bean.privilege;

import javax.persistence.*;

import com.sys.bean.school.ClassRoom;
import com.sys.enums.AdminType;
import com.sys.system.Condition;
import com.sys.system.FieldType;
import com.sys.system.Renewable;

/**
 * 员工
 */
@Entity
@Table(name = "t_employee")
public class Employee {
	@Id @GeneratedValue
	private Integer id;
	/**
	 *用户名
	 */
	@Renewable(label="用户名",maxLength=10,value=false)
	@Column(nullable=false,length=10,unique=true)
	private String username;
	@Column(nullable=false,length=40)
	private String password;
	/**
	 * 真实姓名
	 */
	@Renewable(label="真实姓名",maxLength=10)
	@Column(nullable=false,length=10)
	private String realname;
	
	/**
	 * 联系电话10位
	 * 
	 */
	@Column(length=18)
	@Renewable(label="联系电话",condition={Condition.PHONENUM},maxLength=18)
	private String phone;
	/**
	 * 头像
	 */
	@Renewable(label="头像",type=FieldType.FILE)
	@Column
	private String image;
	@Column
	@Renewable(false)
	private int sex;
	@Column
	@Enumerated
	private AdminType type;
	@OneToOne(mappedBy = "employee")
	private ClassRoom classRoom;

	/**
	 * 获得图片保存路径
	 * @return
	 */
	public String getImagePath(){
		if(this.username != null && this.image != null){
			return "images/employee/"+this.id+"_"+this.username+"/"+this.image;
		}
		return null;
	}
	/**
	 * 获得图片默认保存路劲
	 * @return
	 */
	public String getSavePath(){
		if(this.username != null){
			return "images/employee/"+this.id+"_"+this.username+"/";
		}
		return null;
	}
	@Override
	public int hashCode() {
		if(id == 0){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AdminType getType() {
		return type;
	}

	public void setType(AdminType type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
}
