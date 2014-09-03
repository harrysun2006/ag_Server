package com.test;

import java.io.IOException;

import junit.framework.TestCase;

import com.agloco.service.MemberService;
import com.agloco.service.ViewbarService;
import com.agloco.spring.SpringUtil;
import com.agloco.web.service.BaseService;

public class TestService extends TestCase {

	static{
		try {
			ContextHelper.addResource("jdbc.properties");
			ContextHelper.addResource("jdbc-vb.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testContainService(){
		MemberService mService = (MemberService) SpringUtil.getContext().getBean(MemberService.class.getName());
		assertNotNull(mService);
		
		ViewbarService vService = (ViewbarService)SpringUtil.getContext().getBean(ViewbarService.class.getName());
		assertNotNull(vService);
		
		BaseService bService = (BaseService)SpringUtil.getContext().getBean("loginService");
		assertNotNull(bService);
	}
}
