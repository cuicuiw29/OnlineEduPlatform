package com.cuicui.pgd.service.edu.service;

import com.cuicui.pgd.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuicui.pgd.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
public interface IChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
