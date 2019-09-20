package com.cms.manager.service;

import com.cms.manager.model.dto.TbTeacher;
import com.cms.manager.model.vo.CmsResult;

/**
 * 讲师服务接口
 *
 * @author xianfu.xia
 * @since 2019/2/2
 */
public interface TeacherService {

    /**
     * 添加讲师
     *
     * @param tbTeacher
     * @return
     */
    CmsResult insertTeacher(TbTeacher tbTeacher);

    /**
     * 删除讲师
     *
     * @param tbTeacher
     * @return
     */
    CmsResult delTeacher(TbTeacher tbTeacher);

    /**
     * 更新讲师信息
     *
     * @param tbTeacher
     * @return
     */
    CmsResult updateTeacher(TbTeacher tbTeacher);

    /**
     * 获取讲师详细信息
     *
     * @param id
     * @return
     */
    CmsResult getTeacherInfo(Long id);

    /**
     * 获取所有讲师列表
     *
     * @return
     */
    CmsResult getAllTeacherList();

    /**
     * 获取所有讲师数量
     *
     * @return
     */
    CmsResult getAlTeacherCount();
}
