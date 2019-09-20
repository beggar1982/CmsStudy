package com.cms.manager.service.impl;

import com.cms.manager.dao.mapper.TbTeacherMapper;
import com.cms.manager.model.dto.TbTeacher;
import com.cms.manager.model.vo.CmsResult;
import com.cms.manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xianfu.xia
 * @since 2019/2/2
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TbTeacherMapper teacherMapper;

    /**
     * 添加讲师
     *
     * @param tbTeacher
     * @return
     */
    @Override
    public CmsResult insertTeacher(TbTeacher tbTeacher) {
        return null;
    }

    /**
     * 删除讲师
     *
     * @param tbTeacher
     * @return
     */
    @Override
    public CmsResult delTeacher(TbTeacher tbTeacher) {
        return null;
    }

    /**
     * 更新讲师信息
     *
     * @param tbTeacher
     * @return
     */
    @Override
    public CmsResult updateTeacher(TbTeacher tbTeacher) {
        return null;
    }

    /**
     * 获取讲师详细信息
     *
     * @param id
     * @return
     */
    @Override
    public CmsResult getTeacherInfo(Long id) {
        TbTeacher teacher = teacherMapper.selectByPrimaryKey(id);
        if (teacher != null) {
            return CmsResult.ok(teacher);
        } else {
            return CmsResult.error();
        }
    }

    /**
     * 获取所有讲师列表
     *
     * @return
     */
    @Override
    public CmsResult getAllTeacherList() {
        return null;
    }

    /**
     * 获取所有讲师数量
     *
     * @return
     */
    @Override
    public CmsResult getAlTeacherCount() {
        return null;
    }
}
