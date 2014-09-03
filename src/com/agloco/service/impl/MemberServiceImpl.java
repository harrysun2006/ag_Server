package com.agloco.service.impl;

import com.agloco.dao.util.MemberDAOUtil;
import com.agloco.model.FormalMember;
import com.agloco.model.TemporaryMember;
import com.agloco.service.MemberService;

/**
 * 
 * @author terry_zhao
 *
 */
public class MemberServiceImpl extends BaseServiceImpl implements MemberService {


	public FormalMember getFormalMember(Long memberId) {
		return MemberDAOUtil.getFormalMember(memberId);
	}

	public FormalMember getFormalMemberByCode(String memberCode) {
		return MemberDAOUtil.getFormalMemberByCode(memberCode);
	}

	public FormalMember getFormalMemberByEmail(String emailAddress) {
		return MemberDAOUtil.getFormalMemberByEmail(emailAddress);
	}

	public FormalMember getFormalMemberByUserId(String userId) {
		return MemberDAOUtil.getFormalMemberByUserId(userId);
	}

	public TemporaryMember getTemporaryMember(Long memberId) {
		return MemberDAOUtil.getTemporaryMember(memberId);
	}

	public TemporaryMember getTemporaryMemberByCode(String memberCode) {
		return MemberDAOUtil.getTemporaryMemberByCode(memberCode);
	}

	public TemporaryMember getTemporaryMemberByEmail(String emailAddress) {
		return MemberDAOUtil.getTemporaryMemberByEmail(emailAddress);
	}

	public TemporaryMember getTemporaryMemberByUserId(String userId) {
		return MemberDAOUtil.getTemporaryMemberByUserId(userId);
	}
	


}
