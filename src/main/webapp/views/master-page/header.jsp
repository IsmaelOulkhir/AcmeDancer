<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
    <a href="#"><img src="images/logo.png" alt="Acme Dancer Co., Inc." /></a>
</div>

<div>
    <ul id="jMenu">
        <!-- Do not forget the "fNiv" class for the first level links !! -->
        <security:authorize access="hasRole('ADMIN')">
            <li><a class="fNiv"><spring:message code="master.page.administrator" /></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="administrator/action-1.do"><spring:message code="master.page.administrator.action.1" /></a></li>
                    <li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>                    
                </ul>
            </li>
        </security:authorize>
        
        <security:authorize access="hasRole('CUSTOMER')">
            <li><a class="fNiv"><spring:message code="master.page.customer" /></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
                    <li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>                    
                </ul>
            </li>
        </security:authorize>
        
        <security:authorize access="isAnonymous()">
            <li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
        </security:authorize>
        
        <security:authorize access="isAuthenticated()">
            <li>
                <a class="fNiv">
                    <spring:message code="master.page.profile" />
                    (<security:authentication property="principal.username" />)
                </a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
                    <li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
                    <li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>                    
                    <li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
                </ul>
            </li>
        </security:authorize>

        <security:authorize access="isAuthenticated()">
            <li>
                <a class="fNiv">
                    <spring:message code="master.page.messages" />
                </a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="/Acme-Shout/Comentario/list.do"><spring:message code="master.page.actor.Comentario" /></a></li>
                    <li><a href="/Acme-Shout/Comentario/create.do"><spring:message code="master.page.actor.createComentario" /></a></li>
                    <li><a href="/Acme-Shout/Comentario/myListSubscribe.do"><spring:message code="master.page.actor.ComentarioSubscribe" /></a></li>
                    <li><a href="/Acme-Shout/Comentario/mylist.do"><spring:message code="master.page.actor.myComentario" /></a></li>
                </ul>
            </li>
        </security:authorize>
    </ul>
</div>

<div>
    <a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>
