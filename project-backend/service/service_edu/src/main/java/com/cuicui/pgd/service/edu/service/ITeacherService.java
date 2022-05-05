package com.cuicui.pgd.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuicui.pgd.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuicui.pgd.service.edu.entity.vo.TeacherQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
public interface ITeacherService extends IService<Teacher> {

    IPage<Teacher> selectpage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo);

    List<Map<String, Object>> selectNameList(String key);
    boolean removeAvatarById(String id);

    Map<String, Object> selectTeacherInfoById(String id);

    List<Teacher> selectHotTeacher();
}
