package com.cuicui.pgd.service.uct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuicui.pgd.common.base.result.ResultCodeEnum;
import com.cuicui.pgd.common.base.util.FormUtils;
import com.cuicui.pgd.common.base.util.JwtInfo;
import com.cuicui.pgd.common.base.util.JwtUtils;
import com.cuicui.pgd.common.base.util.MD5;
import com.cuicui.pgd.service.base.dto.MemberDto;
import com.cuicui.pgd.service.base.exception.cuicuiwException;
import com.cuicui.pgd.service.uct.entity.Member;
import com.cuicui.pgd.service.uct.entity.vo.LoginVo;
import com.cuicui.pgd.service.uct.entity.vo.RegisterVo;
import com.cuicui.pgd.service.uct.mapper.MemberMapper;
import com.cuicui.pgd.service.uct.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author cuicuiw
 * @since 2022-04-24
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Override
    public void register(RegisterVo registerVo) {


        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String code = registerVo.getCode();
        String password = registerVo.getPassword();

        if(StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)){
            throw new cuicuiwException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        if(StringUtils.isEmpty(nickname)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code)){
            throw new cuicuiwException(ResultCodeEnum.PARAM_ERROR);
        }



        //用户是否注册：mysql
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new cuicuiwException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        //注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("https://project-gd-file.oss-cn-beijing.aliyuncs.com/avatar/avator/03.jpg");
        member.setDisabled(false);
        baseMapper.insert(member);
    }

    @Override
    public String login(LoginVo loginVo) {


        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验：参数是否合法
        if(StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)
                || StringUtils.isEmpty(password)){
            throw new cuicuiwException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验手机号是否存在
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new cuicuiwException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        //校验密码是否正确
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new cuicuiwException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        //校验用户是否被禁用
        if(member.getDisabled()){
            throw new cuicuiwException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //登录：生成token
        JwtInfo info = new JwtInfo();
        info.setId(member.getId());
        info.setNickname(member.getNickname());
        info.setAvatar(member.getAvatar());

        String jwtToken = JwtUtils.getJwtToken(info, 1800);

        return jwtToken;
    }

    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {

        Member member = baseMapper.selectById(memberId);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member,memberDto);
        return memberDto;

    }
}
