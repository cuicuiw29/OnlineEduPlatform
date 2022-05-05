package com.cuicui.pgd.service.trd.service;

import com.cuicui.pgd.service.trd.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-25
 */
public interface IOrderService extends IService<Order> {

    String saveOrder(String courseId, String id);


    Order getByOrderId(String orderId, String id);

    Boolean isBuyByCourseId(String courseId, String id);

    List<Order> selectByMemberId(String id);

    boolean removeById(String orderId, String id);

    Order getOrderByOrderNo(String orderNo);

    void updateOrderStatus(String orderNo,String id);
}
