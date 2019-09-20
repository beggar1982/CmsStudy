package com.cms.manager.model.vo;

import java.io.Serializable;

/**
 * 讲师信息数据
 *
 * @author xianfu.xia
 * @since 2019/2/2
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = 2153339233124654990L;

    /*
    讲师id
     */
    private long id;

    /*
    讲师姓名
     */
    private String teacherName;

    /*
    讲师title
     */
    private String teacherTitle;

    /*
    讲师描述信息
     */
    private String teacherDesc;

    /*
    讲师图片URL地址
     */
    private String teacherPicUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public String getTeacherDesc() {
        return teacherDesc;
    }

    public void setTeacherDesc(String teacherDesc) {
        this.teacherDesc = teacherDesc;
    }

    public String getTeacherPicUrl() {
        return teacherPicUrl;
    }

    public void setTeacherPicUrl(String teacherPicUrl) {
        this.teacherPicUrl = teacherPicUrl;
    }
}
