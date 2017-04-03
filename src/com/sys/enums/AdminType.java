package com.sys.enums;

/**
 * Created by Zhangzy on 2017/3/14.
 */
public enum AdminType {
    /**
     * 超级管理员
     */
    SUPER_ADMIN("超级管理员"),
    /**
     * 班级管理员
     */
    CLASS_ADMIN("班级管理员");
    private String name;

    AdminType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
