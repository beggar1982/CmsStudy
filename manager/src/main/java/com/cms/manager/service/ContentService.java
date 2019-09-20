package com.cms.manager.service;

import com.cms.manager.model.dto.TbContent;
import com.cms.manager.model.vo.CmsResult;

import java.util.List;

/**
 * 内容管理服务接口定义
 *
 * @author xianfu.xia
 * @since 2018/12/10
 */
public interface ContentService {

	CmsResult insertContent(TbContent content);

	CmsResult updateContent(TbContent content);

	CmsResult deleteContents(String ids);

	CmsResult getContent(Long id);

	CmsResult getAllContent(List<Long> cateIdList);

	/**
	 * 最新上线
	 *
	 * @return
	 */
	CmsResult gettLatestContent();

	/**
	 * 精品课程
	 *
	 * @return
	 */
	CmsResult gettSupremeContent();

	List<TbContent> getContentListByCategory(Long cateId);
}
