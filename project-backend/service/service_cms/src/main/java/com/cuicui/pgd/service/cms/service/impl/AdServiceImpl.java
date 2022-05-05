package com.cuicui.pgd.service.cms.service.impl;

import com.cuicui.pgd.common.base.result.R;
import com.cuicui.pgd.service.cms.entity.Ad;
import com.cuicui.pgd.service.cms.entity.vo.AdVo;
import com.cuicui.pgd.service.cms.feign.OssFileService;
import com.cuicui.pgd.service.cms.mapper.AdMapper;
import com.cuicui.pgd.service.cms.service.AdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    @Autowired
    private OssFileService ossFileService;

    @Override
    public IPage<AdVo> selectPage(Long page, Long limit) {

        QueryWrapper<AdVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.type_id", "a.sort");

        Page<AdVo> pageParam = new Page<>(page, limit);

        List<AdVo> records = baseMapper.selectPageByQueryWrapper(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(!StringUtils.isEmpty(imagesUrl)){
                //删除图片
                R r = ossFileService.removeFile(imagesUrl);
                return r.getSuccess();
            }
        }
        return false;
    }

    @Override
    public List<Ad> selectByAdTypeId(String adTypeId) {

        QueryWrapper<Ad> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.orderByAsc("sort","id");
        objectQueryWrapper.eq("type_id",adTypeId);
        return baseMapper.selectList(objectQueryWrapper);
    }

}
