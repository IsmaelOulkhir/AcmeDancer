<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<spring:message code="Comentario.fechaCom" var="fechaCom" />
<spring:message code="Comentario.texto" var="texto" />
<spring:message code="Comentario.actor" var="actor" />
<spring:message code="Comentario.delete" var="delete" />
<spring:message code="Comentario.subscribe" var="subscribe" />

<security:authorize access="isAuthenticated()">

    <c:if test="${a == 1}"> 
        <table>
            <thead>
                <tr>
                    <th><spring:message code="Comentario.actor" /></th>
                    <th><spring:message code="Comentario.delete" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="comentario" items="${Comentario}">
                    <tr>
                        <td>${comentario.actor.Nombre}</td>
                        <td><a href="Comentario/actor/delete.do?id=${comentario.id}"><spring:message code="Comentario.delete" /></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${a == 2}">
        <table>
            <thead>
                <tr>
                    <th><spring:message code="Comentario.actor" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="e" items="${Comentario}">
                    <tr>
                        <td>${e.actor.Nombre}</td>
                        <td>
                            <security:authentication property="principal.id" var="id" />
                            <c:if test="${!seguidores.contains(e.actor) and e.actor.userAccount.id != id}">
                                <a href="Comentario/actor/subscribe.do?q=${e.id}"><c:out value="${subscribe}" /></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${a == 3}">
        <table>
            <thead>
                <tr>
                    <th><spring:message code="Comentario.actor" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="comentario" items="${Comentario}">
                    <tr>
                        <td>${comentario.actor.Nombre}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

</security:authorize>
