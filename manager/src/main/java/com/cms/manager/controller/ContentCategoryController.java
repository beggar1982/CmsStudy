package com.cms.manager.controller;

import com.cms.manager.model.vo.CateNodeData;
import com.cms.manager.model.vo.CmsResult;
import com.cms.manager.model.vo.EUTreeNode;
import com.cms.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.List;

/**
 * 内容分类管理
 *
 * @author	xianfu.xia
 * @version 1.0
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    /** 行业热点  */
    private static final Integer HOTVIEW_CATEID = 1;
    /** 创业基础课  */
    private static final Integer BASICCOURSE_CATEID = 2;
    /** 创业进阶  */
    private static final Integer VENTURE_CATEID = 3;
    /** 创业案例  */
    private static final Integer BUSINESCASE_CATEID = 4;

    @Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}

	@RequestMapping("/tree")
    @ResponseBody
	public List<EUTreeNode> getCategoryTree() {
        List<EUTreeNode> list = contentCategoryService.getCategoryTree();
        return list;
    }

    @RequestMapping("/catedata")
    @ResponseBody
    public CmsResult getCateNodeData(@RequestParam(value = "parentid", defaultValue = "0") Long parentid) {
        CateNodeData data;
        data = new CateNodeData();
        data.setCateId(parentid);
        data.setCateTitle(contentCategoryService.getCateName(parentid));

        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentid);
        data.setChildren(list);

        return CmsResult.ok(data);
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public CmsResult createContentCategory(@RequestParam(value="parentId") Long parentId,
                                           @RequestParam(value="nodeName") String nodeName) {
        CmsResult result = contentCategoryService.insertContentCategory(parentId, utf8Decoded(nodeName));
		return result;
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
    @ResponseBody
	public CmsResult modifyeContentCategory(@RequestParam(value="id") Long id,
                                            @RequestParam(value="newName") String newName) {
        return contentCategoryService.modifyContentCategory(id, utf8Decoded(newName));
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    @ResponseBody
    public CmsResult removeContentCategory(@RequestParam(value="id") Long id) {
	    return contentCategoryService.removeContentCategory(id);
    }

    private String utf8Decoded(String text) {
        String resultText = text;
        try {
            resultText = URLDecoder.decode(text, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultText;
    }
}
