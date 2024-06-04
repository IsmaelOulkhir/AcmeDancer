<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
    name="tutoriales" requestURI="${requestURI}" id="row">
    <!-- Action links -->
    <security:authorize access="hasRole('ACADEMY')">
        <display:column title="Actions">
            <a href="tutorial/edit.do?tutorialId=${row.id}">
                <spring:message code="tutorial.edit" />
            </a>
            <a href="tutorial/delete.do?tutorialId=${row.id}">
                <spring:message code="tutorial.delete" />
            </a>
             <a href="tutorial/create.do?tutorialId=${row.id}">
                <spring:message code="tutorial.create" />
            </a>
        </display:column>
    </security:authorize>
    
    <!-- Attributes -->
    <spring:message code="tutorial.titulo" var="titleHeader" />
    <display:column property="titulo" title="${titleHeader}" sortable="true" />

    <spring:message code="tutorial.descripcion" var="descriptionHeader" />
    <display:column property="descripcion" title="${descriptionHeader}" sortable="true" />

    <spring:message code="tutorial.video" var="videoHeader" />
    <display:column title="${videoHeader}">
        ${row.embedCode}
    </display:column>
</display:table>

