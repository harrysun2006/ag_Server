package com.test;

import java.io.File;
import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.service.util.RollingServiceUtil;
import com.agloco.util.AdsTemplateUtil;
import com.agloco.util.Ip2CountryUtil;
import com.agloco.util.IpAddressUtil;
import com.test.xmlbeans.CustomerDocument;
import com.test.xmlbeans.CustomerDocument.Customer;

public class Test {

	private static Log _log = LogFactory.getLog(Test.class);

	private static void testXmlBeans() throws Exception {
    File xmlFile = new File("customer.xml");
    CustomerDocument doc = CustomerDocument.Factory.parse(xmlFile);
    Customer customer = doc.getCustomer();
    System.out.println(customer.getInfo());
	}

	private static void testRollingTable() throws Exception {
		RollingServiceUtil.createNewTable("com.agloco.model.VBTimeTrack", "VB_Time_Track1");
		RollingServiceUtil.createNewTable("com.agloco.model.VBTimeSubTotal", "VB_Time_Subtotal1");
	}

	private static void testDependency() throws Exception {
		//Test2.sayHello();
		Test3.sayHello();
	}

	private static void testIPAddress() throws Exception {
		String ip = "222.92.112.66";
		long l = IpAddressUtil.ip2Long(ip);
		byte[] b = IpAddressUtil.ip2Bytes(ip);
		System.out.println(IpAddressUtil.bytes2Ip(b));
		System.out.println(IpAddressUtil.long2Ip(4294967295l));
		System.out.println(IpAddressUtil.ip2Long("127.0.0.1"));
		System.out.println(IpAddressUtil.long2Ip(2147483647l));
		System.out.println(IpAddressUtil.ip2Long("119.119.114.10"));
		System.out.println(IpAddressUtil.ip2Long("119.119.115.28"));
		System.out.println(IpAddressUtil.long2Ip(1991245824l));
		System.out.println(IpAddressUtil.long2Ip(2030043135l));
		System.out.println(Long.toHexString(l));
		for(int i = 0; i < b.length; i++) System.out.println((int)0x00FF & b[i]);
	}

	private static void testAdsTemplate() throws Exception {
		String[] ips = {
			"222.12.34.45", "157.22.244.29", "216.75.58.6", "222.92.112.66", "4.18.32.73", "66.220.13.235",
		};
		for (int i = 0; i < ips.length; i++) {
			 String countryCode = Ip2CountryUtil.getCountryCode(ips[i]);
//			Ip2CountryService service = new IpQueryWeb2Impl();
//			String countryCode = service.ip2Country(ips[i]);
			System.out.println(MessageFormat.format("ip = {0}, country = {1}, baseName = {2}, jsName = {3}"
				, new Object[]{
						ips[i], 
						countryCode,
						AdsTemplateUtil.getBaseName(countryCode),
						AdsTemplateUtil.getJsName(countryCode)}));
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			ConvertUtil.noop();
//			ContextHelper.addResource("test-mail.properties");
			ContextHelper.addResource("jdbc_agloco.properties");
			ContextHelper.addResource("jdbc_vb.properties");
//			testXmlBeans();
//			testRollingTable();
//			testDependency();
			testIPAddress();
//			testAdsTemplate();
		} catch (Exception e) {
			_log.error(e, e);
		} 
	}
}
