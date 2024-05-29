<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
    name="solicitudes" requestURI="${requestURI}" id="row">
    <!-- Attributes -->
    
    <spring:message code="solicitud.fechaSolicitud" var="dateRequestHeader" />
    <display:column property="fechaSolicitud" title="${dateRequestHeader}" sortable="true" />

    <spring:message code="solicitud.estado" var="stateHeader" />
    <display:column property="estado" title="${stateHeader}" sortable="true"/>
	
	<spring:message code="solicitud.titulo" var="titleHeader" />
	<display:column property="curso.titulo" title="${titleHeader}" sortable="true" />

	<spring:message code="solicitud.fechaInicio" var="initDateHeader" />
	<display:column property="curso.fechaInicio" title="${initDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="solicitud.fechaFin" var="endDateHeader" />
	<display:column property="curso.fechaFin" title="${endDateHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="solicitud.diaSemana" var="dayWeekHeader" />
	<display:column property="curso.diaSemana" title="${dayWeekHeader}" sortable="true" />
	
	<spring:message code="solicitud.hora" var="hourHeader" />
	<display:column property="curso.hora" title="${hourHeader}" sortable="true" />
	
	<spring:message code="solicitud.nivel" var="levelHeader" />
	<display:column property="curso.nivel" title="${levelHeader}" sortable="false" />
	
	<spring:message code="solicitud.estilo" var="styleHeader" />
	<display:column property="curso.estilo.nombre" title="${styleHeader}" sortable="false" />
	
	<spring:message code="solicitud.academia" var="academyHeader" />
	<display:column property="curso.academia.nombreComercial" title="${academyHeader}" sortable="false" />

    
</display:table>