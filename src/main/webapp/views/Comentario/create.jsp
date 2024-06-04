<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<security:authorize access="isAuthenticated()">

	<form:form url="Comentario/actor/save.do" skip_fields="text"
		hiddenFields="fechaCom,actor" entity="${Comentario}">
		<script>
			tinymce.init({
				selector : 'textarea'
			});
		</script>
		<label> <spring:message code="Comentario.text" />
		</label>
		<textarea class="form-control" name="text">${Comentario.text}</textarea>
		<br/>
	</form:form>
</security:authorize>