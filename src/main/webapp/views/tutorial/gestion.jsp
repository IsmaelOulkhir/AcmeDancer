<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Crear/Editar/Borrar Tutorial</title>
</head>
<body>

<h1><c:if test="${action eq 'create'}"><spring:message code="tutorial.create"/></c:if>
    <c:if test="${action eq 'edit'}"><spring:message code="tutorial.edit"/></c:if>
    <c:if test="${action eq 'delete'}"><spring:message code="tutorial.delete"/></c:if>
</h1>

<form:form modelAttribute="tutorial" method="post">
    <form:hidden path="id"/>
    
    <c:choose>
        <c:when test="${action eq 'delete'}">
            <p><spring:message code="tutorial.confirm.delete"/></p>
        </c:when>
        <c:otherwise>
            <form:label path="titulo"><spring:message code="tutorial.titulo"/>:</form:label><br>
            <form:input path="titulo"/><br>
            
            <form:label path="descripcion"><spring:message code="tutorial.descripcion"/>:</form:label><br>
            <form:input path="descripcion"/><br>
            
            <form:label path="video"><spring:message code="tutorial.video"/>:</form:label><br>
            <form:input path="video"/><br>
        </c:otherwise>
    </c:choose>
    
    <c:choose>
        <c:when test="${action eq 'create'}">
            <input type="submit" name="save" value="<spring:message code="tutorial.save"/>">
        </c:when>
        <c:when test="${action eq 'edit'}">
            <input type="submit" name="save" value="<spring:message code="tutorial.save"/>">
        </c:when>
        <c:when test="${action eq 'delete'}">
            <input type="submit" name="delete" value="<spring:message code="tutorial.delete"/>">
        </c:when>
    </c:choose>
    
    <a href="/tutorial/list"><spring:message code="tutorial.cancel"/></a>
</form:form>

</body>
</html>
