package com.agloco.dao;

import com.agloco.model.FormalMember;
import com.agloco.model.TemporaryMember;

/**
 * 
 * @author terry_zhao
 * @see MemberDAOHibernate
 */
public interface MemberDAO {
	
	public FormalMember getFormalMember(Long memberId);
	public FormalMember getFormalMemberByUserId(String userId);
	public FormalMember getFormalMemberByEmail(String emailAddress);
	public FormalMember getFormalMemberByCode(String memberCode);
	
	
	public TemporaryMember getTemporaryMember(Long memberId);
	public TemporaryMember getTemporaryMemberByUserId(String userId);
	public TemporaryMember getTemporaryMemberByEmail(String emailAddress);
	public TemporaryMember getTemporaryMemberByCode(String memberCode);
	
	
	
	
}
