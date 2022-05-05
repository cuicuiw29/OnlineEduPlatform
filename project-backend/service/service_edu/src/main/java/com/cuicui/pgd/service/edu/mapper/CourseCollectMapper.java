package com.cuicui.pgd.service.edu.mapper;

import com.cuicui.pgd.service.edu.entity.CourseCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuicui.pgd.service.edu.entity.vo.CourseCollectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程收藏 Mapper 接口
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */

@Repository
public interface CourseCollectMapper extends BaseMapper<CourseCollect> {
    List<CourseCollectVo> selectPageByMemberId(String memberId);
}
