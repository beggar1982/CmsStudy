package com.cms.manager.dao.mapper;

import java.util.List;

import com.cms.manager.dao.util.TbTeacherExample;
import com.cms.manager.model.dto.TbTeacher;
import org.apache.ibatis.annotations.Param;

public interface TbTeacherMapper {
    int countByExample(TbTeacherExample example);

    int deleteByExample(TbTeacherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbTeacher record);

    int insertSelective(TbTeacher record);

    List<TbTeacher> selectByExampleWithBLOBs(TbTeacherExample example);

    List<TbTeacher> selectByExample(TbTeacherExample example);

    TbTeacher selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbTeacher record, @Param("example") TbTeacherExample example);

    int updateByExampleWithBLOBs(@Param("record") TbTeacher record, @Param("example") TbTeacherExample example);

    int updateByExample(@Param("record") TbTeacher record, @Param("example") TbTeacherExample example);

    int updateByPrimaryKeySelective(TbTeacher record);

    int updateByPrimaryKeyWithBLOBs(TbTeacher record);

    int updateByPrimaryKey(TbTeacher record);
}