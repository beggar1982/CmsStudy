package com.cms.manager.service.impl;

import com.cms.manager.dao.mapper.TbContentMapper;
import com.cms.manager.dao.util.TbContentExample;
import com.cms.manager.model.dto.TbContent;
import com.cms.manager.model.vo.CmsResult;
import com.cms.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容管理服务实现类
 *
 * @author xianfu.xia
 * @since 2018/12/10
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public CmsResult insertContent(TbContent content) {
        //补全pojo内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        return CmsResult.ok();
    }

    @Override
    public CmsResult updateContent(TbContent content) {
        contentMapper.updateByPrimaryKeySelective(content);

        return CmsResult.ok();
    }

    @Override
    public CmsResult deleteContents(String ids) {

        String[] strarr = ids.split(",");
        int[] arr = new int[strarr.length];
        for (int i = 0; i < strarr.length; i++) {
            arr[i] = Integer.parseInt(strarr[i]);
        }
        contentMapper.deleteByIds(arr);

        return CmsResult.ok();
    }

    @Override
    public CmsResult getContent(Long id) {
        TbContent content = contentMapper.selectByPrimaryKey(id);
        if (content != null) {
            return CmsResult.ok(content);
        } else {
            return CmsResult.error();
        }
    }

    @Override
    public CmsResult getAllContent(List<Long> cateIdList) {
        if (cateIdList == null || cateIdList.size() == 0) {
            return CmsResult.ok();
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.or();
        criteria.andCateIdIn(cateIdList);
        List<TbContent> list = contentMapper.selectByExample(example);
        if (list != null) {
            return CmsResult.ok(list);
        } else {
            return CmsResult.error();
        }
    }

    /**
     * 最新上线
     *
     * @return
     */
    @Override
    public CmsResult gettLatestContent() {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.or();
        criteria.andIsnewsEqualTo(true);
        List<TbContent> list = contentMapper.selectByExample(example);
        if (list != null) {
            return CmsResult.ok(list);
        } else {
            return CmsResult.error();
        }
    }

    /**
     * 精品课程
     *
     * @return
     */
    @Override
    public CmsResult gettSupremeContent() {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.or();
        criteria.andIssupremeEqualTo(true);
        List<TbContent> list = contentMapper.selectByExample(example);
        if (list != null) {
            return CmsResult.ok(list);
        } else {
            return CmsResult.error();
        }
    }

    @Override
    public List<TbContent> getContentListByCategory(Long cateId) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.or();
        criteria.andCateIdEqualTo(cateId);
        List<TbContent> list = contentMapper.selectByExample(example);
        return list;
    }
}
