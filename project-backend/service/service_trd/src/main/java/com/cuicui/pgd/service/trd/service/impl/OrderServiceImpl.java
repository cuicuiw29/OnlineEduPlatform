package com.cuicui.pgd.service.trd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuicui.pgd.common.base.result.ResultCodeEnum;
import com.cuicui.pgd.service.base.dto.CourseDto;
import com.cuicui.pgd.service.base.dto.MemberDto;
import com.cuicui.pgd.service.base.exception.cuicuiwException;
import com.cuicui.pgd.service.trd.entity.Order;
import com.cuicui.pgd.service.trd.feign.EduCourseService;
import com.cuicui.pgd.service.trd.feign.UctMemberService;
import com.cuicui.pgd.service.trd.mapper.OrderMapper;
import com.cuicui.pgd.service.trd.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuicui.pgd.service.trd.util.OrderNoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-25
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private UctMemberService uctMemberService;

    @Override
    public String saveOrder(String courseId, String memberId) {

        //查询当前用户是否已有当前课程的订单
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("member_id", memberId);
        Order orderExist = baseMapper.selectOne(queryWrapper);
        if (orderExist != null) {
            return orderExist.getId();//如果订单已存在，则直接返回订单id

        }

        //查询课程信息
        CourseDto courseDto = eduCourseService.getCourseDtoById(courseId);
        if (courseDto == null) {
            throw new cuicuiwException(ResultCodeEnum.PARAM_ERROR);
        }

        //查询用户信息
        MemberDto memberDto = uctMemberService.getMemberDtoByMemberId(memberId);
        if (memberDto == null) {
            throw new cuicuiwException(ResultCodeEnum.PARAM_ERROR);
        }

        //创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtils.getOrderNo()); //订单号

        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName(courseDto.getTeacherName());
        order.setTotalFee(courseDto.getPrice().multiply(new BigDecimal(100)));//单位：分

        order.setMemberId(memberId);
        order.setMobile(memberDto.getMobile());
        order.setNickname(memberDto.getNickname());

        order.setStatus(0);//未支付
        order.setPayType(1); //微信支付

        baseMapper.insert(order);
        return order.getId();
    }

    @Override
    public Order getByOrderId(String orderId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId).eq("member_id", memberId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean isBuyByCourseId(String courseId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("course_id", courseId)
                .eq("member_id", memberId)
                .eq("status", 1);

        Integer count = baseMapper.selectCount(queryWrapper);
        return count.intValue() > 0;
    }

    @Override
    public List<Order> selectByMemberId(String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("gmt_create")
                .eq("member_id", memberId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean removeById(String orderId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", orderId)
                .eq("member_id", memberId);
        return this.remove(queryWrapper);
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderNo);

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateOrderStatus(String orderNo,String memberId) {
        //更新订单状态
        log.info("更新的订单号为"+orderNo);

        Order order = this.getOrderByOrderNo(orderNo);
        log.info("查询到的order对象为"+order.toString());
        order.setStatus(1);//支付成功
        baseMapper.updateById(order);

        //更新课程销量
        eduCourseService.updateBuyCountById(order.getCourseId());


    }
}
