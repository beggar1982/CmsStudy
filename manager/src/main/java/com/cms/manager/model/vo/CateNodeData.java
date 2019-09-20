package com.cms.manager.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author xianfu.xia
 * @since 2019/2/2
 */
public class CateNodeData implements Serializable {

    private static final long serialVersionUID = -2815170892534347277L;

    private long cateId;

    private String cateTitle;

    private List<EUTreeNode> children;

    public long getCateId() {
        return cateId;
    }

    public void setCateId(long cateId) {
        this.cateId = cateId;
    }

    public String getCateTitle() {
        return cateTitle;
    }

    public void setCateTitle(String cateTitle) {
        this.cateTitle = cateTitle;
    }

    public List<EUTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EUTreeNode> children) {
        this.children = children;
    }
}
