package com.cms.manager.controller;

import com.cms.manager.model.vo.CmsResult;
import com.cms.manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 讲师管理controllrt
 *
 * @author xianfu.xia
 * @since 2019/2/2
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CmsResult getTeacherInfo(@PathVariable Long id) {
        return teacherService.getTeacherInfo(id);
    }
}
