<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
    <form action="curso/list.do" method="post" style="display:inline;">
        <label for="busqueda"><spring:message code="curso.buscar" /></label><br>
        <input type="text" id="busqueda" name="busqueda"><br><br>
        <input type="submit" value="Buscar">
    </form>
</security:authorize>


<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="cursos" requestURI="${requestURI}" id="row">
	<!-- Action links -->

		<display:column>
			<a href="academia/list.do?cursoId=${row.id}">
				<spring:message	code="curso.verAcademia" />
			</a>	
			<br/>
			<br/>
			<security:authorize access="hasRole('ALUMN')">
	            <form action="curso/solicitar.do" method="post" style="display:inline;">
	                <input type="hidden" name="cursoId" value="${row.id}" />
	                <button type="submit">
	                    <spring:message code="curso.solicitar" />
	                </button>
	            </form>
        	</security:authorize>
		</display:column>	
	<!-- Attributes -->
	
	<spring:message code="curso.titulo" var="titleHeader" />
	<display:column property="titulo" title="${titleHeader}" sortable="true" />

	<spring:message code="curso.fechaInicio" var="initDateHeader" />
	<display:column property="fechaInicio" title="${initDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="curso.fechaFin" var="endDateHeader" />
	<display:column property="fechaFin" title="${endDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="curso.diaSemana" var="dayWeekHeader" />
	<display:column property="diaSemana" title="${dayWeekHeader}" sortable="true" />
	
	<spring:message code="curso.hora" var="hourHeader" />
	<display:column property="hora" title="${hourHeader}" sortable="true" />
	
	<spring:message code="curso.nivel" var="levelHeader" />
	<display:column property="nivel" title="${levelHeader}" sortable="false" />
	
	<spring:message code="curso.estilo" var="styleHeader" />
	<display:column property="estilo.nombre" title="${styleHeader}" sortable="false" />
	
	<spring:message code="curso.academia" var="academyHeader" />
	<display:column property="academia.nombreComercial" title="${academyHeader}" sortable="false" />
	
</display:table>
