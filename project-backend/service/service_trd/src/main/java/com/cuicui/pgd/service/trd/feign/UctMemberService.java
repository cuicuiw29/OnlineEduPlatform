package com.cuicui.pgd.service.trd.feign;
import com.cuicui.pgd.service.base.dto.MemberDto;
import com.cuicui.pgd.service.trd.feign.fallback.UcenterMemberServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Service
@FeignClient(value = "service-ucenter")
public interface UctMemberService {


    @GetMapping("/api/ucenter/member/inner/get-member-dto/{memberId}")
    MemberDto getMemberDtoByMemberId(@PathVariable(value = "memberId") String memberId);
}
