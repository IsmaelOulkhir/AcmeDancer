<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Visualizar Tutorial</title>
</head>
<body>
    <h1>Tutorial</h1>
    
    <table border="1">
        <tr>
            <th><spring:message code="tutorial.titulo" /></th>
            <td>${tutorial.titulo}</td>
        </tr>
        <tr>
            <th><spring:message code="tutorial.descripcion" /></th>
            <td>${tutorial.descripcion}</td>
        </tr>
        <tr>
            <th><spring:message code="tutorial.video" /></th>
            <td><iframe width="400" height="400" src="${tutorial.video}"></iframe></td>
        </tr>
        <tr>
            <th><spring:message code="tutorial.visualizaciones" /></th>
            <td>${tutorial.visualizaciones}</td>
        </tr>
        <tr>
            <th><spring:message code="tutorial.academia" /></th>
            <td>${tutorial.academia.nombre}</td>
        </tr>
    </table>
    
    <a href="/Acme-Dancer/tutorial/list.do"><spring:message code="tutorial.return" /></a>
</body>
</html>
