<%--
 * tarjetaDeCredito-form.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p><spring:message code="tarjetaDeCredito.form.title" /></p>

<form:form modelAttribute="tarjetaDeCredito" method="post">
    <form:hidden path="id" />
    <form:hidden path="version" />
    
    <form:label path="nombre"><spring:message code="tarjetaDeCredito.nombre" />:</form:label>
    <form:input path="nombre" />
    <form:errors path="nombre" cssClass="error"/>
    <br />
    
    <form:label path="marca"><spring:message code="tarjetaDeCredito.marca" />:</form:label>
    <form:input path="marca" />
    <form:errors path="marca" cssClass="error"/>
    <br />

    <form:label path="numero"><spring:message code="tarjetaDeCredito.numero" />:</form:label>
    <form:input path="numero" pattern="[0-9]{16}" title="Debe contener exactamente 16 dígitos" />
    <form:errors path="numero" cssClass="error"/>
    <br />

    <form:label path="mesCaducidad"><spring:message code="tarjetaDeCredito.mesCaducidad" />:</form:label>
    <form:input path="mesCaducidad" type="number" min="1" max="12" />
    <form:errors path="mesCaducidad" cssClass="error"/>
    <br />

    <form:label path="anioCaducidad"><spring:message code="tarjetaDeCredito.anioCaducidad" />:</form:label>
    <form:input path="anioCaducidad" type="number" />
    <form:errors path="anioCaducidad" cssClass="error"/>
    <br />

    <form:label path="cvv"><spring:message code="tarjetaDeCredito.cvv" />:</form:label>
    <form:input path="cvv" pattern="[0-9]{3}" title="Debe contener exactamente 3 dígitos" />
    <form:errors path="cvv" cssClass="error"/>
    <br />

   <input type="submit" name="save" value="<spring:message code="tarjetaDeCredito.save" />" />
<input type="button" name="cancel" value="<spring:message code="tarjetaDeCredito.cancel" />" onclick="javascript: window.location.href='<c:url value="/ruta-alternativa"/>';" />
</form:form>
   