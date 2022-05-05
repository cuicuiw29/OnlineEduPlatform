package com.cuicui.pgd.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuicui.pgd.service.edu.entity.Subject;
import com.cuicui.pgd.service.edu.entity.excel.ExcelSubjectData;
import com.cuicui.pgd.service.edu.mapper.SubjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {
    public ExcelSubjectDataListener() {
    }

    public ExcelSubjectDataListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @Autowired
    private SubjectMapper subjectMapper;


    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext context) {
        log.info("解析到一条记录: {}", data);
        //处理读取出来的数据
        String levelOneTitle = data.getLevelOneTitle();//一级标题
        String levelTwoTitle = data.getLevelTwoTitle();//二级标题
        log.info("levelOneTitle: {}", levelOneTitle);
        log.info("levelTwoTitle: {}", levelTwoTitle);

        //判断数据是否存在
        Subject subjectLevelOne = this.getByTitle(levelOneTitle);
        String parentId = null;
        if(subjectLevelOne == null){
            //组装一级类别
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            // 存入数据库
            subjectMapper.insert(subject);
            parentId = subject.getId();
        }else{
            parentId = subjectLevelOne.getId();
        }

        //判断数据是否存在
        Subject subjectLevelTwo = this.getSubByTitle(levelTwoTitle, parentId);
        if(subjectLevelTwo == null){
            //组装二级类别
            Subject subject = new Subject();
            subject.setTitle(levelTwoTitle);
            subject.setParentId(parentId);
            // 存入数据库
            subjectMapper.insert(subject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("全部数据解析完成");
    }

    /**
     * 根据一级分类的名称查询数据是否存在
     * @param title
     * @return
     */
    private Subject getByTitle(String title){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0"); //一级分类
        return subjectMapper.selectOne(queryWrapper);
    }


    /**
     * 根据分类的名称和父id查询数据是否存在
     * @param title
     * @param parentId
     * @return
     */
    private Subject getSubByTitle(String title, String parentId){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId); //二级分类
        return subjectMapper.selectOne(queryWrapper);
    }
}
