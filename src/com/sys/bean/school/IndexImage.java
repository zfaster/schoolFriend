package com.sys.bean.school;

import com.sys.system.Renewable;

import javax.persistence.*;

/**
 * 首页图片简介
 */
@Entity
@Table(name = "t_index_image")
public class IndexImage {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    @Renewable
    private String title;
    @Column
    @Lob
    @Renewable
    private String content;
    @Column
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获得图片保存路径
     * @return
     */
    public String getImagePath(){
        if(this.id != null && this.image != null){
            return "images/index/"+this.id+"/"+this.image;
        }
        return null;
    }
    /**
     * 获得图片默认保存路劲
     * @return
     */
    public String getSavePath(){
        if(this.id != null){
            return "images/index/"+this.id+"/";
        }
        return null;
    }
}
