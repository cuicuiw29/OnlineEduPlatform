package com.cuicui.pgd.service.edu.controller.api;

import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.entity.Course;
import com.cuicui.pgd.service.edu.entity.Teacher;
import com.cuicui.pgd.service.edu.service.ICourseService;
import com.cuicui.pgd.service.edu.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Api(description = "首页")
@RestController
@RequestMapping("/api/edu/index")
public class ApiIndexController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ITeacherService teacherService;

    @ApiOperation("课程和讲师的首页数据")
    @GetMapping
    public R index(){
        List<Course> courseList=courseService.selectHotCourse();

        List<Teacher> teacherList=teacherService.selectHotTeacher();

        return  R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
