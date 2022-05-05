package com.cuicui.pgd.service.edu.feign;

import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.feign.fallback.OssFileServiceFallBack;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@FeignClient(value="service-oss",fallback = OssFileServiceFallBack.class)
@Service
public interface OssFileService {

    @GetMapping("/admin/oss/file/test")
    R test();
    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}
