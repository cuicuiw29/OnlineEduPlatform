package com.cuicui.pgd.service.uct.service;

import com.cuicui.pgd.service.base.dto.MemberDto;
import com.cuicui.pgd.service.uct.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuicui.pgd.service.uct.entity.vo.LoginVo;
import com.cuicui.pgd.service.uct.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-24
 */
public interface IMemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    MemberDto getMemberDtoByMemberId(String memberId);
}
