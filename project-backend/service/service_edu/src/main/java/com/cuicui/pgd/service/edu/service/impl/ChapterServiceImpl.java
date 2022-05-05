package com.cuicui.pgd.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuicui.pgd.service.edu.entity.Chapter;
import com.cuicui.pgd.service.edu.entity.Video;
import com.cuicui.pgd.service.edu.entity.vo.ChapterVo;
import com.cuicui.pgd.service.edu.entity.vo.VideoVo;
import com.cuicui.pgd.service.edu.mapper.ChapterMapper;
import com.cuicui.pgd.service.edu.mapper.VideoMapper;
import com.cuicui.pgd.service.edu.service.IChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {

    @Autowired
    private VideoMapper videoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterById(String id) {

        //根据courseId删除Video(课时)
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        videoMapper.delete(videoQueryWrapper);

        //删除章节
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> nestedList(String courseId) {

        //方案1：效率低  1+n个sql
        //通过course_id获取章节列表信息：List<Chapter>  sql
        //遍历List<Chapter>{ n
        //    通过chapter_id查询List<Video> sql
        // }

        //方案2：效率高 1+1个sql
        //通过course_id获取章节列表信息：List<Chapter>  sql
        //通过course_id查询List<Video> sql

        //获取章节信息列表
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        chapterQueryWrapper.orderByAsc("sort", "id");
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        //获取课时信息列表
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        videoQueryWrapper.orderByAsc("sort", "id");
        List<Video> videoList = videoMapper.selectList(videoQueryWrapper);


        //组装章节列表：List<ChapterVo>
        List<ChapterVo> chapterVoList = new ArrayList<>();
//        for (Chapter chapter : chapterList) {
//
//        }
        for (Chapter chapter:chapterList) {
//
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);

            //组装章节列表：List<ChapterVo>
            List<VideoVo> videoVoList = new ArrayList<>();
            for (Video video:videoList) {

                if(chapter.getId().equals(video.getChapterId())){

                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }

        return chapterVoList;

    }
}
