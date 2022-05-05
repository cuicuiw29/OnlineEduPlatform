package com.cuicui.pgd.service.edu.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.entity.Teacher;
import com.cuicui.pgd.service.edu.entity.vo.TeacherQueryVo;
import com.cuicui.pgd.service.edu.feign.OssFileService;
import com.cuicui.pgd.service.edu.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@Slf4j
@CrossOrigin //允许跨域
@Api(description ="讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;

    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

    @ApiOperation("根据ID删除讲师")
    @DeleteMapping("remove/{id}")
    public R removeByid(@PathVariable String id){
//        删除讲师头像
        teacherService.removeAvatarById(id);

//        删除讲师
        boolean result=teacherService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("讲师分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam("当前页码") @PathVariable Long page,
                      @ApiParam("每页记录个数") @PathVariable Long limit,
                      @ApiParam("查询对象") TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel= teacherService.selectpage(pageParam,teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total=pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher){

        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R update(@ApiParam("讲师对象") @RequestBody Teacher teacher) {
        boolean result = teacherService.updateById(teacher);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据ID获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师对象") @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher != null) {
            return R.ok().data("item",teacher);
        }else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据ID列表删除讲师")
    @DeleteMapping("batch-remove")
    public R removeRows(
            @ApiParam(value="讲师ID列表",required = true)
            @RequestBody List<String> IDList){
        boolean result=teacherService.removeByIds(IDList);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据关键字查询讲师名列表")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "关键字",required = true)
            @PathVariable String key) {
        List<Map<String,Object>> namelist = teacherService.selectNameList(key);
        return R.ok().data("nameList",namelist);
    }

    @ApiOperation("测试服务调用")
    @GetMapping("test")
    public R test(){
        ossFileService.test();
        log.info("edu执行成功");
        return R.ok();
    }

    @ApiOperation("测试并发")
    @GetMapping("test_concurrent")
    public R testConcurrent(){

        log.info("test_concurrent");
        return R.ok();
    }

    @GetMapping("/message1")
    public String message1(){
        return "message1";
    }

    @GetMapping("/message2")
    public String message2(){
        return "message2";
    }
}

