<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Crear Estilo</title>
</head>
<body>

<form:form action="crearEstilo" modelAttribute="estilo">

    <form:label path="nombre"><spring:message code="estilo.nombre" /></form:label>
    <form:input path="nombre"/>
    <form:errors class="error" path="nombre"/>
    <br/>

    <form:label path="descripcion"><spring:message code="estilo.descripcion" /></form:label>
    <form:input path="descripcion"/>
    <form:errors class="error" path="descripcion"/>
    <br/>

    <form:label path="imagenes"><spring:message code="estilo.imagenes" /></form:label>
    <form:input path="imagenes"/>
    <form:errors class="error" path="imagenes"/>
    <br/>

    <form:label path="videos"><spring:message code="estilo.videos" /></form:label>
    <form:input path="videos"/>
    <form:errors class="error" path="videos"/>
    <br/>
	
    <jstl:if test="${showError == true}">
		<div class="error">
			<spring:message code="estilo.commit.error" />
		</div>
	</jstl:if>
	
    <input type="submit" value="Crear Estilo"/>
    
</form:form>

</body>
</html>