package com.agloco.service.util;

import org.springframework.context.ApplicationContext;

import com.agloco.model.FormalMember;
import com.agloco.model.TemporaryMember;
import com.agloco.service.MemberService;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author terry_zhao
 *
 */
public class MemberServiceUtil {

	private MemberService memberService;
	
	public static FormalMember getFormalMember(Long memberId){
		return getMemberService().getFormalMember(memberId);
	}
	public static FormalMember getFormalMemberByUserId(String userId){
		return getMemberService().getFormalMemberByUserId(userId);
	}
	public static FormalMember getFormalMemberByEmail(String emailAddress){
		return getMemberService().getFormalMemberByEmail(emailAddress);
	}
	public static FormalMember getFormalMemberByCode(String memberCode){
		return getMemberService().getFormalMemberByCode(memberCode);
	}
	
	
	public static TemporaryMember getTemporaryMember(Long memberId){
		return getMemberService().getTemporaryMember(memberId);
	}
	public static TemporaryMember getTemporaryMemberByUserId(String userId){
		return getMemberService().getTemporaryMemberByUserId(userId);
	}
	public static TemporaryMember getTemporaryMemberByEmail(String emailAddress){
		return getMemberService().getTemporaryMemberByEmail(emailAddress);
	}
	public static TemporaryMember getTemporaryMemberByCode(String memberCode){
		return getMemberService().getTemporaryMemberByCode(memberCode);
	}
	
	private static MemberService getMemberService(){
		ApplicationContext ctx = SpringUtil.getContext();
		MemberServiceUtil util = (MemberServiceUtil)ctx.getBean(MemberServiceUtil.class.getName());
		return util.memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
}
