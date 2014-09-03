package com.agloco.dao.util;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.MemberDAO;
import com.agloco.model.FormalMember;
import com.agloco.model.TemporaryMember;
import com.agloco.spring.SpringUtil;


/**
 * 
 * @author terry_zhao
 *
 */
public class MemberDAOUtil {

	private MemberDAO memberDAO;
	
	public static FormalMember getFormalMember(Long memberId){
		return getMemberDao().getFormalMember(memberId);
	}
	public static FormalMember getFormalMemberByUserId(String userId){
		return getMemberDao().getFormalMemberByUserId(userId);
	}
	public static FormalMember getFormalMemberByEmail(String emailAddress){
		return getMemberDao().getFormalMemberByEmail(emailAddress);
	}
	public static FormalMember getFormalMemberByCode(String memberCode){
		return getMemberDao().getFormalMemberByCode(memberCode);
	}
	
	
	public static TemporaryMember getTemporaryMember(Long memberId){
		return getMemberDao().getTemporaryMember(memberId);
	}
	public static TemporaryMember getTemporaryMemberByUserId(String userId){
		return getMemberDao().getTemporaryMemberByUserId(userId);
	}
	public static TemporaryMember getTemporaryMemberByEmail(String emailAddress){
		return getMemberDao().getTemporaryMemberByEmail(emailAddress);
	}
	public static TemporaryMember getTemporaryMemberByCode(String memberCode){
		return getMemberDao().getTemporaryMemberByCode(memberCode);
	}

	private static MemberDAO getMemberDao(){
		ApplicationContext ctx = SpringUtil.getContext();
		MemberDAOUtil util = (MemberDAOUtil) ctx.getBean(MemberDAOUtil.class.getName());
		return util.memberDAO;
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
}
