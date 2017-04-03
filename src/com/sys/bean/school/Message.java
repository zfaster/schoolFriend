package com.sys.bean.school;

import javax.persistence.*;
import java.util.Date;

/**
 * 留言
 */
@Entity
@Table(name = "t_message")
public class Message {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @Column
    private Date createTime = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
