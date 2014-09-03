<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TEST SERVICES</title>
</head>
<body>
<form action="servlet" method="post"
enctype="multipart/form-data" name="form1">
	<select name="serviceName">
		<option value="loginService">loginService</option>
		<option value="autoLoginService">autoLoginService</option>
		<option value="logoutService">logoutService</option>
		<option value="searchKeywordsService">searchKeywordsService</option>
		<option value="urlTrackService">urlTrackService</option>
		<option value="timeTrackService">timeTrackService</option>
		<option value="affiliateService">affiliateService</option>
		<option value="autoUpdateService">autoUpdateService</option>
		<option value="performanceService">performanceService</option>
		<option value="viewbarCheckService">viewbarCheckService</option>
		<option value="downloadCountService">downloadCountService</option>
		<option value="moptionService">moptionService</option>

	</select>
  <input type="file" name="fileName">
  <input type="submit" name="Submit" value="upload">
</form>
</body>
</html>