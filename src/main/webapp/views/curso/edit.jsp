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
<title>Crear Curso</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
	$(function() {
		$("#fechaInicio").datepicker({
			dateFormat: 'mm/dd/yy'
		});
		$("#fechaFin").datepicker({
			dateFormat: 'mm/dd/yy'
		});
	});
</script>
</head>
<body>
	<jsp:useBean id="now" class="java.util.Date" />
	<form:form action="curso/edit.do" modelAttribute="curso" method="post">
		<form:hidden path="id" />
		<form:hidden path="academia" value="${academia}" />

		<form:label path="titulo">
			<spring:message code="curso.titulo" />
		</form:label>
		<form:input path="titulo" />
		<form:errors class="error" path="titulo" />
		<br />

		<fmt:formatDate value="${curso.fechaInicio}" pattern="MM/dd/yyyy"
			var="myFechaInicio" />
		<form:label path="fechaInicio">
			<spring:message code="curso.fechaInicio" />
		</form:label>
		<form:input path="fechaInicio" id="fechaInicio"
			value="${myFechaInicio}" />
		<form:errors class="error" path="fechaInicio" />
		<br />

		<fmt:formatDate value="${curso.fechaFin}" pattern="MM/dd/yyyy"
			var="myFechaFin" />
		<form:label path="fechaFin">
			<spring:message code="curso.fechaFin" />
		</form:label>
		<form:input path="fechaFin" id="fechaFin" value="${myFechaFin}" />
		<form:errors class="error" path="fechaFin" />
		<br />

		<form:label path="diaSemana">
			<spring:message code="curso.diaSemana" />
		</form:label>
		<form:input path="diaSemana" />
		<form:errors class="error" path="diaSemana" />
		<br />

		<form:label path="hora">
			<spring:message code="curso.hora" />
		</form:label>
		<form:input path="hora" />
		<form:errors class="error" path="hora" />
		<br />

		<form:label path="nivel">
			<spring:message code="curso.nivel" />
		</form:label>
		<form:select path="nivel">
			<form:option value="PRINCIPIANTE">
				<spring:message code="curso.nivel.principiante" />
			</form:option>
			<form:option value="INTERMEDIO">
				<spring:message code="curso.nivel.intermedio" />
			</form:option>
			<form:option value="AVANZADO">
				<spring:message code="curso.nivel.avanzado" />
			</form:option>
			<form:option value="PROFESIONAL">
				<spring:message code="curso.nivel.profesional" />
			</form:option>
		</form:select>
		<form:errors class="error" path="nivel" />
		<br />

		<form:label path="estilo">
			<spring:message code="curso.estilo" />
		</form:label>
		<form:select path="estilo">
			<form:options items="${estilos}" itemValue="id" itemLabel="nombre" />
		</form:select>
		<form:errors class="error" path="estilo" />
		<br />

		<c:if test="${showError}">
			<div class="error">
				<spring:message code="curso.commit.error" />
			</div>
		</c:if>

		<c:if test="${curso.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code='curso.edit' />" />&nbsp; 
	</c:if>

		<c:if test="${curso.id != 0}">
			<input type="submit" name="edit"
				value="<spring:message code='curso.save' />" />&nbsp; 
			<input type="submit" name="delete"
				value="<spring:message code='curso.delete' />"
				onclick="return confirm('<spring:message code='curso.confirm.delete' />')" />&nbsp;
	</c:if>
		<input type="button" name="cancel"
			value="<spring:message code='curso.cancel' />"
			onclick="javascript: window.location.href='curso/list.do';" />
		<br />

	</form:form>

</body>
</html>
