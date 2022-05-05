package com.cuicui.pgd.service.trd.feign.fallback;
import com.cuicui.pgd.service.base.dto.MemberDto;
import com.cuicui.pgd.service.trd.feign.UctMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class UcenterMemberServiceFallback implements UctMemberService {
    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        log.info("熔断保护");
        return null;
    }
}
