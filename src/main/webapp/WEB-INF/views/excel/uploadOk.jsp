<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<h1> EXCEL DATA UPLOAD OK </h1>
	
	NAME : ${excelData.name} <br />
	PHONE : ${excelData.phone} <br />
	EMAIL : ${excelData.email} <br />
	
	<a href="${cp}/"> MAIN </a>
</body>
</html>