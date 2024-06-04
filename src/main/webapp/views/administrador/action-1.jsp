<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="administrador.action.1" /></p>
<h2><spring:message code="administrador.titulo1" /></h2>
    <table border="1">
        <tr>
            <th><spring:message code="administrador.minimo" /></th>
            <th><spring:message code="administrador.media" /></th>
            <th><spring:message code="administrador.desviacion" /></th>
            <th><spring:message code="administrador.maximo" /></th>
        </tr>
        <tr>
            <td>${minimoCursoAcademia}</td>
            <td>${mediaCursoAcademia}</td>
            <td>${desviacionCursoAcademia}</td>
            <td>${maximoCursoAcademia}</td>
        </tr>
    </table>

    <h2><spring:message code="administrador.titulo2" /></h2>
    <table border="1">
        <tr>
            <th><spring:message code="administrador.minimo" /></th>
            <th><spring:message code="administrador.media" /></th>
            <th><spring:message code="administrador.desviacion" /></th>
            <th><spring:message code="administrador.maximo" /></th>
        </tr>
        <tr>
            <td>${minimoSolicitudCurso}</td>
            <td>${mediaSolicitudCurso}</td>
            <td>${desviacionSolicitudCurso}</td>
            <td>${maximoSolicitudCurso}</td>
        </tr>
    </table>

    <h2><spring:message code="administrador.titulo3" /></h2>
    <table border="1">
        <tr>
            <th><spring:message code="administrador.minimo" /></th>
            <th><spring:message code="administrador.media" /></th>
            <th><spring:message code="administrador.maximo" /></th>
        </tr>
        <tr>
            <td>${minimoTutorialAcademia}</td>
            <td>${mediaTutorialAcademia}</td>
            <td>${maximoTutorialAcademia}</td>
        </tr>
    </table>

    <h2><spring:message code="administrador.titulo4" /></h2>
    <table border="1">
        <tr>
            <th><spring:message code="administrador.minimo" /></th>
            <th><spring:message code="administrador.media" /></th>
            <th><spring:message code="administrador.maximo" /></th>
        </tr>
        <tr>
            <td>${minimoTutorialVisualizacion}</td>
            <td>${mediaTutorialVisualizacion}</td>
            <td>${maximoTutorialVisualizacion}</td>
        </tr>
    </table>