package com.cms.manager.model.dto;

import java.io.Serializable;
import java.util.Date;

public class TbContent implements Serializable {
    private static final long serialVersionUID = 4379137339644760416L;

    private Long id;

    private Long cateId;

    private String title;

    private String description;

    private String pic;

    private Boolean isnews;

    private Boolean issupreme;

    private Date created;

    private Date updated;

    private String content;

    /** 时长描述信息 */
    private String shortDesc;

    /**
     * 讲师id
     */
    private long teacherId;

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Boolean getIsnews() {
        return isnews;
    }

    public void setIsnews(Boolean isnews) {
        this.isnews = isnews;
    }

    public Boolean getIssupreme() {
        return issupreme;
    }

    public void setIssupreme(Boolean issupreme) {
        this.issupreme = issupreme;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
}