package com.cuicui.pgd.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuicui.pgd.service.base.dto.CourseDto;
import com.cuicui.pgd.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuicui.pgd.service.edu.entity.form.CourseInfoForm;
import com.cuicui.pgd.service.edu.entity.vo.*;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
public interface ICourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo);

    WebCourseVo selectWebCourseVoById(String id);

    List<Course> selectHotCourse();

    CourseDto getCourseDtoById(String courseId);

    void updateBuyCountById(String id);
}
