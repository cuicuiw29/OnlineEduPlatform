package com.cuicui.pgd.service.edu.feign.fallback;

import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.feign.VodMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class VodMediaServiceFallBack implements VodMediaService {
    @Override
    public R removeVideo(String vodId) {
        log.info("熔断保护");
        return R.error();
    }
}
