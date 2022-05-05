package com.cuicui.pgd.service.edu.service;

import com.cuicui.pgd.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuicui.pgd.service.edu.entity.vo.SubjectVo;
import org.springframework.context.annotation.Import;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-05
 */
public interface ISubjectService extends IService<Subject> {
        void batchImport(InputStream inputStream);

        List<SubjectVo> nestedList();
}
