package com.cuicui.pgd.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.entity.Course;
import com.cuicui.pgd.service.edu.entity.Teacher;
import com.cuicui.pgd.service.edu.entity.vo.TeacherQueryVo;
import com.cuicui.pgd.service.edu.feign.OssFileService;
import com.cuicui.pgd.service.edu.mapper.CourseMapper;
import com.cuicui.pgd.service.edu.mapper.TeacherMapper;
import com.cuicui.pgd.service.edu.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private OssFileService ossFileService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Teacher> selectpage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {
        //显示分页查询列表
//        1、排序问题：按照sort字段排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

//        2、查询
        if(teacherQueryVo==null)
        {
            return baseMapper.selectPage(pageParam,queryWrapper);
        }
//        3/条件查询
        String name=teacherQueryVo.getName();
        Integer level=teacherQueryVo.getLevel();
        String joinDateBegin=teacherQueryVo.getJoinDateBegin();
        String joinDateEnd=teacherQueryVo.getJoinDateEnd();
        if(!StringUtils.isNullOrEmpty(name)){
            queryWrapper.likeRight("name",name);
        }
        if(level!=null){
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isNullOrEmpty(joinDateBegin)){
            queryWrapper.ge("join_date",joinDateBegin);
        }
        if(!StringUtils.isNullOrEmpty(joinDateEnd)){
            queryWrapper.le("join_date",joinDateEnd);
        }
        return baseMapper.selectPage(pageParam,queryWrapper);

//        return null;
    }

    @Override
    public List<Map<String, Object>> selectNameList(String key) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name",key);

        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        return maps;
    }

    @Override
    public boolean removeAvatarById(String id) {

        //根据id获取讲师avatar 的 url
        Teacher teacher = baseMapper.selectById(id);
        if(teacher != null){
            String avatar = teacher.getAvatar();
            if(!StringUtils.isNullOrEmpty(avatar)){
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }

        return false;
    }

    @Override
    public Map<String, Object> selectTeacherInfoById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id",id);
        List<Course> courses = courseMapper.selectList(courseQueryWrapper);

        Map<String,Object> map=new HashedMap<>();

        map.put("teacher",teacher);
        map.put("courseList",courses);
        return map;


    }

    @Override
    public List<Teacher> selectHotTeacher() {


        QueryWrapper<Teacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }
}
