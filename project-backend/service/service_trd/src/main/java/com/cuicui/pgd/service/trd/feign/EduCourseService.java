package com.cuicui.pgd.service.trd.feign;
import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.base.dto.CourseDto;
import com.cuicui.pgd.service.trd.feign.fallback.EduCourseServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Service
@FeignClient(value = "service-edu")
public interface EduCourseService {

    @GetMapping("/api/edu/course/inner/get-course-dto/{courseId}")
    CourseDto getCourseDtoById(@PathVariable(value = "courseId") String courseId);

    @GetMapping("/api/edu/course/inner/update-buy-count/{id}")
    R updateBuyCountById(@PathVariable("id") String id);
}
