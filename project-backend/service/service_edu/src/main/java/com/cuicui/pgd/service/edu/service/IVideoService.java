package com.cuicui.pgd.service.edu.service;

import com.cuicui.pgd.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
public interface IVideoService extends IService<Video> {
        void removeMediaVideoById(String id);
}
