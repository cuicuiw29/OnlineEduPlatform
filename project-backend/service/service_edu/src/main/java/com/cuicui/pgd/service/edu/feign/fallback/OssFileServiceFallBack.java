package com.cuicui.pgd.service.edu.feign.fallback;

import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R test() {
        return R.error();
    }

    @Override
    public R removeFile(String url) {
      log.info("熔断保护");
        return R.error();
    }
}
