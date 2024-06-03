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

<h2><spring:message code="tutorial.titulo1" /></h2>
    <table border="1">
        <tr>
            <th><spring:message code="tutorial.minimo" /></th>
            <th><spring:message code="tutorial.media" /></th>
            <th><spring:message code="tutorial.maximo" /></th>
        </tr>
        <tr>
            <td>${minimoTutorialAcademia}</td>
            <td>${mediaTutorialAcademia}</td>
            <td>${maximoTutorialAcademia}</td>
        </tr>
    </table>

    <h2><spring:message code="tutorial.titulo2" /></h2>
    <table border="1">
        <tr>
            <th><spring:message code="tutorial.minimo" /></th>
            <th><spring:message code="tutorial.media" /></th>
            <th><spring:message code="tutorial.maximo" /></th>
        </tr>
        <tr>
            <td>${minimoTutorial}</td>
            <td>${mediaTutorial}</td>
            <td>${maximoTutorial}</td>
        </tr>
    </table>
