package com.cuicui.pgd.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.cuicui.pgd.service.edu.entity.Subject;
import com.cuicui.pgd.service.edu.entity.excel.ExcelSubjectData;
import com.cuicui.pgd.service.edu.entity.vo.SubjectVo;
import com.cuicui.pgd.service.edu.listener.ExcelSubjectDataListener;
import com.cuicui.pgd.service.edu.mapper.SubjectMapper;
import com.cuicui.pgd.service.edu.service.ISubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Override
    public void batchImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectDataListener(baseMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet().doRead();
    }

    @Override
    public List<SubjectVo> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }
}
