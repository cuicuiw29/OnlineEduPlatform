package com.cuicui.pgd.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuicui.pgd.service.base.dto.CourseDto;
import com.cuicui.pgd.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuicui.pgd.service.edu.entity.vo.CoursePublishVo;
import com.cuicui.pgd.service.edu.entity.vo.CourseVo;
import com.cuicui.pgd.service.edu.entity.vo.WebCourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {


    List<CourseVo> selectPageByCourseQueryVo(
            Page<CourseVo> pageParam,
            @Param(Constants.WRAPPER) QueryWrapper<CourseVo> queryWrapper);

    CoursePublishVo selectCoursePublishVoById(String id);

    WebCourseVo selectWebCourseVoById(String courseId);

    CourseDto selectCourseDtoById(String courseId);
}

