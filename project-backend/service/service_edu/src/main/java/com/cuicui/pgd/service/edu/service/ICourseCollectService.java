package com.cuicui.pgd.service.edu.service;

import com.cuicui.pgd.service.edu.entity.CourseCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuicui.pgd.service.edu.entity.vo.CourseCollectVo;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
public interface ICourseCollectService extends IService<CourseCollect> {

    boolean isCollect(String courseId, String memberId);

    void saveCourseCollect(String courseId, String memberId);

    List<CourseCollectVo> selectListByMemberId(String memberId);

    boolean removeCourseCollect(String courseId, String memberId);
}
