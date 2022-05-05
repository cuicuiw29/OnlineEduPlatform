package com.cuicui.pgd.service.edu.service.impl;

import com.cuicui.pgd.service.edu.entity.Video;
import com.cuicui.pgd.service.edu.feign.VodMediaService;
import com.cuicui.pgd.service.edu.mapper.VideoMapper;
import com.cuicui.pgd.service.edu.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Autowired
    private VodMediaService vodMediaService;
    @Override
    public void removeMediaVideoById(String id) {
        //根据VideoID找到视频id
        log.warn("VideoServiceImpl：video id = " + id);
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        log.warn("VideoServiceImpl：videoSourceId= " + videoSourceId);
        vodMediaService.removeVideo(videoSourceId);
    }
}
