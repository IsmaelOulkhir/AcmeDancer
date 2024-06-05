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
    name="estilos" requestURI="${requestURI}" id="row">
    <!-- Action links -->

		<display:column>
			<a href="curso/list.do?estiloId=${row.id}">
				<spring:message	code="estilo.verCursos" />
			</a>	
			<br/>
			<br/>
			<security:authorize access="hasRole('ADMIN')">
					<a href="estilo/edit.do?estiloId=${row.id}">
						<spring:message	code="estilo.editar" />
					</a>	
			</security:authorize>
		</display:column>	
    <!-- Attributes -->
    
    <spring:message code="estilo.nombre" var="nameHeader" />
    <display:column property="nombre" title="${nameHeader}" sortable="true" />

    <spring:message code="estilo.descripcion" var="descriptionHeader" />
    <display:column property="descripcion" title="${descriptionHeader}" sortable="true"/>

    <spring:message code="estilo.imagenes" var="imageHeader" />
    <display:column property="imagenes" title="${imageHeader}" sortable="true"/>
    
    <spring:message code="estilo.videos" var="videoHeader" />
    <display:column property="videos" title="${videoHeader}" sortable="true"/>
    
</display:table>