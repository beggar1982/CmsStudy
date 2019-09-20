package com.cms.manager.controller;

import com.cms.manager.model.dto.TbContent;
import com.cms.manager.model.vo.CmsResult;
import com.cms.manager.model.vo.EUDataGridResult;
import com.cms.manager.model.vo.EUTreeNode;
import com.cms.manager.service.ContentCategoryService;
import com.cms.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容管理Controller
 *
 * @author xianfu.xia
 * @since 2018/12/10
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public CmsResult insertContent(TbContent content) {
        if (content.getCreated() == null) {
            content.setCreated(new Date());
        }
        if (content.getUpdated() == null) {
            content.setUpdated(new Date());
        }
        CmsResult result = contentService.insertContent(content);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CmsResult updateContent(TbContent content) {
        content.setUpdated(new Date());
        if (content.getIsnews() == null) {
            content.setIsnews(false);
        }
        if (content.getIssupreme() == null) {
            content.setIssupreme(false);
        }
        CmsResult result = contentService.updateContent(content);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CmsResult deleteContent(String ids) {
        return contentService.deleteContents(ids);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CmsResult getContent(@PathVariable Long id) {
        return contentService.getContent(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public EUDataGridResult getContentListByCategory(@RequestParam Long cateId) {
        List<TbContent> list = contentService.getContentListByCategory(cateId);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        if (list != null & list.size() > 0) {
            result.setTotal(list.size());
        } else {
            result.setTotal(0);
        }

        return result;
    }

    /**
     * 获取一级大分类下的所有课程列表
     *
     * @param pCateId
     * @return
     */
    @RequestMapping(value = "/alllist", method = RequestMethod.GET)
    @ResponseBody
    public CmsResult getAllContentList(@RequestParam Long pCateId) {
        List<EUTreeNode> list = contentCategoryService.getCategoryList(pCateId);
        List<Long> cateIdList = new ArrayList<>(list.size());
        for (EUTreeNode node: list) {
            cateIdList.add(node.getId());
        }

        return contentService.getAllContent(cateIdList);
    }

    @RequestMapping(value = "/latestlist", method = RequestMethod.GET)
    @ResponseBody
    public CmsResult getLatestContentList() {
        return contentService.gettLatestContent();
    }

    @RequestMapping(value = "/supremelist", method = RequestMethod.GET)
    @ResponseBody
    public CmsResult getSupremeContentList() {
        return contentService.gettSupremeContent();
    }


    @RequestMapping("showcontentadd")
    public String showContentAddForm() {
        return "content-add";
    }

    @RequestMapping("showcontentedit")
    public String showContentEditForm() {
        return "content-edit";
    }
}