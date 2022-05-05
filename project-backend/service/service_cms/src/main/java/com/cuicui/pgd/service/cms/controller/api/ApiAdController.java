package com.cuicui.pgd.service.cms.controller.api;


import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.cms.entity.Ad;
import com.cuicui.pgd.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin //解决跨域问题
@Api(description = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
@Slf4j
public class ApiAdController {
    @Autowired
    private AdService adService;

    @ApiOperation("根据推荐位ID显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public R listByAdTypeId(@ApiParam(value = "推荐位ID",required = true)@PathVariable String adTypeId)
    {
        List<Ad> adList=adService.selectByAdTypeId(adTypeId);
        return R.ok().data("items",adList);
    }
}
