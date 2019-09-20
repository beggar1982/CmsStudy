package com.cms.manager.service;

import java.util.List;

import com.cms.manager.model.vo.CmsResult;
import com.cms.manager.model.vo.EUTreeNode;

/**
 * 内容分类管理服务接口定义
 *
 * @author xianfu.xia
 * @since 2018/12/10
 */
public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);

	String getCateName(Long cateId);

	List<EUTreeNode> getCategoryTree();

	CmsResult insertContentCategory(long parentId, String nodeName);

	CmsResult modifyContentCategory(long id, String newName);

	CmsResult removeContentCategory(long id);
}
