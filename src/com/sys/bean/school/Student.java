package com.sys.bean.school;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 校友
 */
@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String realname;
    @Column
    private String phone;
    @Column
    private String image;
    @Column
    private String address;
    @Column
    private Date createTime;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ClassRoom room;
    @OneToMany(mappedBy = "student")
    private List<Message> messageList;
    /**
     * 获得图片保存路径
     * @return
     */
    public String getImagePath(){
        if(this.username != null && this.image != null){
            return "images/student/"+this.id+"_"+this.username+"/"+this.image;
        }
        return null;
    }
    /**
     * 获得图片默认保存路劲
     * @return
     */
    public String getSavePath(){
        if(this.username != null){
            return "images/student/"+this.id+"_"+this.username+"/";
        }
        return null;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClassRoom getRoom() {
        return room;
    }

    public void setRoom(ClassRoom room) {
        this.room = room;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
