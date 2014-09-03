package com.agloco.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.MemberDAO;
import com.agloco.model.FormalMember;
import com.agloco.model.TemporaryMember;

/**
 * 
 * @author terry_zhao
 *
 */
public class MemberDAOHibernate extends HibernateDaoSupport implements MemberDAO {
	

	public FormalMember getFormalMember(Long memberId) {
		return (FormalMember)getHibernateTemplate().get(FormalMember.class, memberId);
	}

	public FormalMember getFormalMemberByCode(String memberCode) {
		List list = getHibernateTemplate().find("from FormalMember fm where fm.memberCode=?", memberCode.toUpperCase());
		if(list == null || list.size() < 1){
			return null;
		}
		return (FormalMember)list.iterator().next();
	}

	public FormalMember getFormalMemberByEmail(String emailAddress) {
		List list = getHibernateTemplate().find("from FormalMember fm where fm.emailAddress=?", emailAddress);
		if(list == null || list.size() < 1){
			return null;
		}
		return (FormalMember)list.iterator().next();
	}

	public FormalMember getFormalMemberByUserId(String userId) {
		List list = getHibernateTemplate().find("from FormalMember fm where fm.userId=?", userId);
		if(list == null || list.size() < 1){
			return null;
		}
		return (FormalMember)list.iterator().next();
	}

	public TemporaryMember getTemporaryMember(Long memberId) {
		return (TemporaryMember)getHibernateTemplate().get(TemporaryMember.class, memberId);
	}

	public TemporaryMember getTemporaryMemberByCode(String memberCode) {
		List list = getHibernateTemplate().find("from TemporaryMember tm where tm.memberCode=?", memberCode.toUpperCase());
		if(list == null || list.size() < 1){
			return null;
		}
		return (TemporaryMember)list.iterator().next();
	}

	public TemporaryMember getTemporaryMemberByEmail(String emailAddress) {
		List list = getHibernateTemplate().find("from TemporaryMember tm where tm.emailAddress=?", emailAddress);
		if(list == null || list.size() < 1){
			return null;
		}
		return (TemporaryMember)list.iterator().next();
	}

	public TemporaryMember getTemporaryMemberByUserId(String userId) {
		List list = getHibernateTemplate().find("from TemporaryMember tm where tm.userId=?", userId);
		if(list == null || list.size() < 1){
			return null;
		}
		return (TemporaryMember)list.iterator().next();
	}

}
