<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear Estilo</title>
</head>
<body>

	<form:form action="estilo/edit.do" modelAttribute="estilo"
		method="post">
		<form:hidden path="id" value="${estilo.id}" />
		<form:hidden path="version" value="${estilo.version}" />

		<form:label path="nombre">
			<spring:message code="estilo.nombre" />
		</form:label>
		<form:input path="nombre" />
		<form:errors class="error" path="nombre" />
		<br />

		<form:label path="descripcion">
			<spring:message code="estilo.descripcion" />
		</form:label>
		<form:input path="descripcion" />
		<form:errors class="error" path="descripcion" />
		<br />

		<form:label path="imagenes">
			<spring:message code="estilo.imagenes" />
		</form:label>
		<form:input path="imagenes" />
		<form:errors class="error" path="imagenes" />
		<br />

		<form:label path="videos">
			<spring:message code="estilo.videos" />
		</form:label>
		<form:input path="videos" />
		<form:errors class="error" path="videos" />
		<br />

		<jstl:if test="${showError == true}">
			<div class="error">
				<spring:message code="estilo.commit.error" />
			</div>
		</jstl:if>

		<jstl:if test="${estilo.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code='estilo.save' />" />&nbsp; 
			<input type="button" name="cancel"
				value="<spring:message code='estilo.cancel' />"
				onclick="javascript: window.location.href='list.do';" />
			<br />
	</jstl:if>

		<jstl:if test="${estilo.id != 0}">
			<input type="submit" name="edit"
				value="<spring:message code='estilo.edit' />" />&nbsp; 
			<input type="submit" name="delete"
				value="<spring:message code='estilo.delete' />"
				onclick="return confirm('<spring:message code='estilo.confirm.delete' />')" />&nbsp;
			<input type="button" name="cancel"
				value="<spring:message code='estilo.cancel' />"
				onclick="javascript: window.location.href='list-admin.do';" />
		<br />
	</jstl:if>

	</form:form>

</body>
</html>