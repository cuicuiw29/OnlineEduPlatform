package com.cuicui.pgd.service.edu.feign;

import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.feign.fallback.OssFileServiceFallBack;
import com.cuicui.pgd.service.edu.feign.fallback.VodMediaServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="service-vod",fallback = VodMediaServiceFallBack.class)
@Service
public interface VodMediaService {

    @DeleteMapping("/admin/vod/media/remove/{vodId}")
    R removeVideo(@PathVariable("vodId") String vodId);
}
