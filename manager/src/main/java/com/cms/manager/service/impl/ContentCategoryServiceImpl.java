package com.cms.manager.service.impl;

import com.cms.manager.dao.mapper.TbContentCategoryMapper;
import com.cms.manager.dao.mapper.TbContentMapper;
import com.cms.manager.dao.util.TbContentCategoryExample;
import com.cms.manager.dao.util.TbContentExample;
import com.cms.manager.model.dto.TbContentCategory;
import com.cms.manager.model.vo.CmsResult;
import com.cms.manager.model.vo.EUTreeNode;
import com.cms.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理服务实现类
 *
 * @author xianfu.xia
 * @since 2018/12/10
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        //根据parentid查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentidEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            copyNode(tbContentCategory, node);
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public String getCateName(Long cateId) {
        TbContentCategory data = contentCategoryMapper.selectByPrimaryKey(cateId);
        if (data != null) {
            return data.getName();
        }

        return "";
    }

    @Override
    public List<EUTreeNode> getCategoryTree() {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        //遍历顶层树节点
        for (TbContentCategory tbContentCategory : list) {
            if (tbContentCategory.getParentid() == 0) {
                //创建一个节点
                EUTreeNode node = new EUTreeNode();
                copyNode(tbContentCategory, node);
                resultList.add(node);

                makeSubTreed(list, node);
            }
        }

        return resultList;
    }

    private void copyNode(TbContentCategory sourceNode, EUTreeNode destNode) {
        destNode.setId(sourceNode.getId());
        destNode.setText(sourceNode.getName());
        destNode.setState("open");
    }

    private void makeSubTreed(List<TbContentCategory> sourceList, EUTreeNode parentNode) {
        for (TbContentCategory tbContentCategory : sourceList) {
            if (tbContentCategory.getParentid() == parentNode.getId()) {
                //创建新节点并添加到parentNode的子节点列表中
                EUTreeNode newNode = new EUTreeNode();
                copyNode(tbContentCategory, newNode);
                parentNode.getChildren().add(newNode);

                //递归生成子节点树
                makeSubTreed(sourceList, newNode);
            }
        }
    }

    @Override
    public CmsResult insertContentCategory(long parentId, String nodeName) {
        try {
            //创建一个pojo
            TbContentCategory contentCategory = new TbContentCategory();
            contentCategory.setName(nodeName);
            contentCategory.setParentid(parentId);
            contentCategory.setSort(1);
            contentCategory.setCreated(new Date());
            contentCategory.setUpdated(new Date());
            contentCategory.setLevel((short) 1);
            //添加记录
            contentCategoryMapper.insert(contentCategory);
            //返回结果
            return CmsResult.ok(contentCategory);
        } catch (Exception ex) {
            return CmsResult.error(ex.getMessage());
        }
    }

    @Override
    public CmsResult modifyContentCategory(long id, String newName) {
        try {
            TbContentCategory record = new TbContentCategory();
            record.setId(id);
            record.setName(newName);
            int updateCount = contentCategoryMapper.updateByPrimaryKeySelective(record);
            if (updateCount > 0) {
                return CmsResult.ok(updateCount);
            } else {
                return CmsResult.error();
            }
        } catch (Exception ex) {
            return CmsResult.error(ex.getMessage());
        }
    }

    @Override
    public CmsResult removeContentCategory(long id) {
        try {
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.or();
            criteria.andCateIdEqualTo(id);
            contentMapper.deleteByExample(example);
            int updateCount = contentCategoryMapper.deleteByPrimaryKey(id);

            if (updateCount > 0) {
                return CmsResult.ok(updateCount);
            } else {
                return CmsResult.error();
            }
        } catch (Exception ex) {
            return CmsResult.error(ex.getMessage());
        }
    }
}
