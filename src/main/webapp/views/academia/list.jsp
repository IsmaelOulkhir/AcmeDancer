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
	name="academias" requestURI="${requestURI}" id="row">
	<!-- Action links -->

		<display:column>
			<a href="curso/list.do?academiaId=${row.id}">
				<spring:message	code="academia.verCursos" />
			</a>	
		</display:column>	
	<!-- Attributes -->
	
	<spring:message code="academia.nombreComercial" var="tradeNameHeader" />
	<display:column property="nombreComercial" title="${tradeNameHeader}" sortable="true" />

	<spring:message code="academia.correoElectronico" var="emailHeader" />
	<display:column property="correoElectronico" title="${emailHeader}" sortable="true" />

	<spring:message code="academia.numeroTelefono" var="phoneNumberHeader" />
	<display:column property="numeroTelefono" title="${phoneNumberHeader}" sortable="true" />

	<spring:message code="academia.direccionPostal" var="postalHeader" />
	<display:column property="direccionPostal" title="${postalHeader}" sortable="true" />
	
	
</display:table>
