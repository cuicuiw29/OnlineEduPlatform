package com.cuicui.pgd.service.trd.feign.fallback;
import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.base.dto.CourseDto;
import com.cuicui.pgd.service.trd.feign.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author helen
 * @since 2020/5/5
 */
@Service
@Slf4j
public class EduCourseServiceFallback implements EduCourseService {

    @Override
    public CourseDto getCourseDtoById(String courseId) {
        log.info("熔断保护");
        return null;
    }

    @Override
    public R updateBuyCountById(String id) {
        return null;
    }

}
