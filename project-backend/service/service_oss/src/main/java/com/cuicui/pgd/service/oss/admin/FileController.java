package com.cuicui.pgd.service.oss.admin;



import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.common.base.result.ResultCodeEnum;
import com.cuicui.pgd.common.base.util.ExceptionUtils;
import com.cuicui.pgd.service.base.exception.cuicuiwException;
import com.cuicui.pgd.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Api(description = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(value = "文件",required = true)
                    @RequestParam("file")
            MultipartFile file,
            @RequestParam("module")
            @ApiParam(value = "模块",required = true)
                    String module) throws IOException {

        try {
            InputStream inputStream=file.getInputStream();
            String originalFilename=file.getOriginalFilename();
            String uploadUrl=fileService.upload(inputStream,module,originalFilename);
            return  R.ok().message("文件上传成功").data("url",uploadUrl);
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(ExceptionUtils.getMessage(e));
            throw new cuicuiwException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation(value="文件删除")
    @DeleteMapping("remove")
    public R removeFile(
            @ApiParam(value="要删除的文件url路径",required = true)
            @RequestBody String url){
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }

    @ApiOperation(value="测试")
    @GetMapping("test")
    public R test(){
        log.info("oss test被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
}
