package com.cuicui.pgd.service.edu.controller.admin;


import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.common.base.result.ResultCodeEnum;
import com.cuicui.pgd.common.base.util.ExceptionUtils;
import com.cuicui.pgd.service.base.exception.cuicuiwException;
import com.cuicui.pgd.service.edu.entity.Subject;
import com.cuicui.pgd.service.edu.entity.vo.SubjectVo;
import com.cuicui.pgd.service.edu.service.ISubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */

@Slf4j
@CrossOrigin //允许跨域
@Api(description ="课程分类管理")
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectController {
        @Autowired
    private ISubjectService subjectService;

        @ApiOperation("Excel批量导入课程分类")
        @PostMapping("import")
        public R batchImport(
                @ApiParam(value="Excel文件",required = true)
                @RequestParam("file")
                MultipartFile file) {
            try {
                InputStream inputStream = file.getInputStream();
                subjectService.batchImport(inputStream);
                return R.ok().message("批量导入成功");
            }catch (Exception e){
                log.error(ExceptionUtils.getMessage(e));
                throw new cuicuiwException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
//                抛出自定义异常
            }
        }

        @ApiOperation("嵌套数据列表")
        @GetMapping("nested-list")
        public R nestedList(){
            List<SubjectVo> subjectVoList=subjectService.nestedList();
            return R.ok().data("items",subjectVoList);
        }
}

