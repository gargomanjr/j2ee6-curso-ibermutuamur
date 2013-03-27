<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultado</title>
<c:if test="${not empty ciudad1}">
    <h2>${ciudad1.city}</h2>
    <h2>${ciudad1.country.country}</h2>
    <h2>${ciudad2.city}</h2>
    <h2>${ciudad2.country.country}</h2>
</c:if>
</head>
<body>

</body>
</html>