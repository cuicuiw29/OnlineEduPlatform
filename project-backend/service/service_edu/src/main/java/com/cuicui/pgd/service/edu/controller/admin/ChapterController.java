package com.cuicui.pgd.service.edu.controller.admin;



import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.entity.Chapter;
import com.cuicui.pgd.service.edu.entity.vo.ChapterVo;
import com.cuicui.pgd.service.edu.service.IChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//2022-04-22 20:41:02 |ERROR |main |SpringApplication.java:826 |org.springframework.boot.SpringApplication |Application run failed
//org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'chapterController': Unsatisfied dependency expressed through field 'chapterService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'chapterServiceImpl': Unsatisfied dependency expressed through field 'baseMapper'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'chapterMapper' defined in file [F:\projectGD\service\service_edu\target\classes\com\cuicui\pgd\service\edu\mapper\ChapterMapper.class]: Unsatisfied dependency expressed through bean property 'sqlSessionFactory'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sqlSessionFactory' defined in class path resource [com/baomidou/mybatisplus/autoconfigure/MybatisPlusAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.apache.ibatis.session.SqlSessionFactory]: Factory method 'sqlSessionFactory' threw exception; nested exception is org.springframework.core.NestedIOException: Failed to parse mapping resource: 'file [F:\projectGD\service\service_edu\target\classes\com\cuicui\pgd\service\edu\mapper\xml\CourseMapper.xml]'; nested exception is org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. The XML location is 'file [F:\projectGD\service\service_edu\target\classes\com\cuicui\pgd\service\edu\mapper\xml\CourseMapper.xml]'. Cause: org.apache.ibatis.builder.BuilderException: Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias 'com.atguigu.guli.service.edu.entity.vo.WebCourseVo'.  Cause: java.lang.ClassNotFoundException: Cannot find class:
// com.atguigu.guli.service.edu.entity.vo.WebCourseVo
/**
 * <p>
 * ?????? ???????????????
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@CrossOrigin
@Api(description = "????????????")
@Slf4j
@RestController
@RequestMapping("/admin/edu/chapter")
public class ChapterController {
    @Autowired
    private IChapterService chapterService;

    @ApiOperation("????????????")
    @PostMapping("save")
    public R save(
            @ApiParam(value="????????????", required = true)
            @RequestBody Chapter chapter){
        boolean result = chapterService.save(chapter);
        if (result) {
            return R.ok().message("????????????");
        } else {
            return R.error().message("????????????");
        }
    }

    @ApiOperation("??????id????????????")
    @GetMapping("get/{id}")
    public R getById(
            @ApiParam(value="??????id", required = true)
            @PathVariable String id){

        Chapter chapter = chapterService.getById(id);
        if (chapter != null) {
            return R.ok().data("item", chapter);
        } else {
            return R.error().message("???????????????");
        }
    }

    @ApiOperation("??????id????????????")
    @PutMapping("update")
    public R updateById(
            @ApiParam(value="????????????", required = true)
            @RequestBody Chapter chapter){

        boolean result = chapterService.updateById(chapter);
        if (result) {
            return R.ok().message("????????????");
        } else {
            return R.error().message("???????????????");
        }
    }

    @ApiOperation("??????ID????????????")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(value = "??????ID", required = true)
            @PathVariable String id){

        //TODO: ??????????????????
        //????????????vod?????????????????????????????????

        //????????????
        boolean result = chapterService.removeChapterById(id);
        if(result){
            return R.ok().message("????????????");
        }else{
            return R.error().message("???????????????");
        }
    }

    @ApiOperation("????????????????????????")
    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(value = "??????ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }
}

