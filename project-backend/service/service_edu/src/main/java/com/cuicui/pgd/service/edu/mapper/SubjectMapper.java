package com.cuicui.pgd.service.edu.mapper;

import com.cuicui.pgd.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuicui.pgd.service.edu.entity.vo.SubjectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {


    List<SubjectVo> selectNestedListByParentId(String parentId);
}
