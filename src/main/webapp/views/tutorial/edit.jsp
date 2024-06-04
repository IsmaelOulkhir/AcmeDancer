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
<meta charset="ISO-8859-1">
<title>Crear Tutorial</title>
</head>
<body>
	<form:form action="tutorial/edit.do" modelAttribute="tutorial" method="post">
		<form:hidden path="id" value="${tutorial.id}"/>
		<form:hidden path="version" value="${tutorial.version}"/>
		<form:hidden path="academia" value="${academia.id}" />
		<form:hidden path="visualizaciones" value="${tutorial.visualizaciones}" />

		<form:label path="titulo">
			<spring:message code="tutorial.titulo" />
		</form:label>
		<form:input path="titulo" />
		<form:errors class="error" path="titulo" />
		<br />

		<form:label path="descripcion">
			<spring:message code="tutorial.descripcion" />
		</form:label>
		<form:input path="descripcion" />
		<form:errors class="error" path="descripcion" />
		<br />

		<form:label path="video">
			<spring:message code="tutorial.video" />
		</form:label>
		<form:input path="video" />
		<form:errors class="error" path="video" />
		<br />

		<c:if test="${showError}">
			<div class="error">
				<spring:message code="tutorial.commit.error" />
			</div>
		</c:if>

		<c:if test="${tutorial.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code='tutorial.save' />" />&nbsp; 
			<input type="button" name="cancel"
				value="<spring:message code='tutorial.cancel' />"
				onclick="javascript: window.location.href='list-academy.do';" />
			<br />
	</c:if>

		<c:if test="${tutorial.id != 0}">
			<input type="submit" name="edit"
				value="<spring:message code='tutorial.edit' />" />&nbsp; 
			<input type="submit" name="delete"
				value="<spring:message code='tutorial.delete' />"
				onclick="return confirm('<spring:message code='tutorial.confirm.delete' />')" />&nbsp;
			<input type="button" name="cancel"
				value="<spring:message code='tutorial.cancel' />"
				onclick="javascript: window.location.href='list-academy.do';" />
		<br />
	</c:if>

	</form:form>

</body>
</html>
