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

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="tutoriales" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	
	<spring:message code="tutorial.titulo" var="titleHeader" />
	<display:column property="titulo" title="${titleHeader}" sortable="false" />

	<spring:message code="tutorial.descripcion" var="descriptionHeader" />
	<display:column property="descripcion" title="${descriptionHeader}" sortable="false" />

	<spring:message code="tutorial.academia" var="academyHeader" />
	<display:column property="academia.nombreComercial" title="${academyHeader}" sortable="false" />

	<spring:message code="tutorial.visualizaciones" var="viewHeader" />
	<display:column property="visualizaciones" title="${viewHeader}" sortable="true" />
	
</display:table>
