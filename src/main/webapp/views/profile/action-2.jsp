<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html>
<head>
    <title>Editar datos</title>
</head>
<body>
    <form:form action="profile/action-2.do" modelAttribute="academia" method="post">
		<form:hidden path="id" value="${academia.id}" />
		<form:hidden path="version" value="${academia.version}" />
		<form:hidden path="userAccount" value="${userAccount.id}" />

		<form:label path="nombreComercial">
		<spring:message code="profile.nombreComercial" />
		</form:label>
            <form:input path="nombreComercial" />
            <form:errors class="error" path="nombreComercial" />
            <br />
            
        <!-- Campo para nombre -->
        <form:label path="nombre">
            <spring:message code="profile.firstName" />
        </form:label>
        <form:input path="nombre" required="true" />
        <form:errors class="error" path="nombre" />
        <br />

        <!-- Campo para apellidos -->
        <form:label path="apellidos">
            <spring:message code="profile.lastName" />
        </form:label>
        <form:input path="apellidos" required="true" />
        <form:errors class="error" path="apellidos" />
        <br />

        <!-- Campo para correo electr�nico -->
        <form:label path="correoElectronico">
            <spring:message code="profile.email" />
        </form:label>
        <form:input path="correoElectronico" type="email" required="true" />
        <form:errors class="error" path="correoElectronico" />
        <br />

        <!-- Campo para n�mero de tel�fono (opcional) -->
        <form:label path="numeroTelefono">
            <spring:message code="profile.phoneNumber" />
        </form:label>
        <form:input path="numeroTelefono" />
        <form:errors class="error" path="numeroTelefono" />
        <br />

        <!-- Campo para direcci�n postal (opcional) -->
        <form:label path="direccionPostal">
            <spring:message code="profile.postalAddress" />
        </form:label>
        <form:input path="direccionPostal" />
        <form:errors class="error" path="direccionPostal" />
        <br />

        <jstl:if test="${showError == true}">
            <div class="error">
                <spring:message code="profile.failed" />
            </div>
        </jstl:if>

        <input type="submit" name="save"
				value="<spring:message code='profile.save' />" />&nbsp; 
		<input type="button" name="cancel"
				value="<spring:message code='profile.cancel' />"
				onclick="javascript: window.location.href='/Acme-Dancer/';" />
			
    </form:form>
</body>
</html>
