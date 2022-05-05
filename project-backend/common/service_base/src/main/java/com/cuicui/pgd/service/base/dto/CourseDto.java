package com.cuicui.pgd.service.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CourseDto implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;
    private String title;
    private BigDecimal price;//课程售价
    private String cover;//封面
    private String teacherName;  //讲师

}
