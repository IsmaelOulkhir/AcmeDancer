<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Dance Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrador" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrador/action-1.do"><spring:message
								code="master.page.administrador.action.1" /></a></li>
					<li><a href="administrador/action-2.do"><spring:message
								code="master.page.administrador.action.2" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('ALUMN')">
			<li><a class="fNiv"><spring:message
						code="master.page.alumno" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="alumno/action-1.do"><spring:message
								code="master.page.alumno.action.1" /></a></li>
					<li><a href="alumno/action-2.do"><spring:message
								code="master.page.alumno.action.2" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('ACADEMY')">
			<li><a class="fNiv"><spring:message
						code="master.page.academia" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="academia/action-1.do"><spring:message
								code="master.page.academia.action.1" /></a></li>
					<li><a href="academia/action-2.do"><spring:message
								code="master.page.academia.action.2" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv" href="security/register.do"><spring:message
						code="master.page.register" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message
								code="master.page.profile.action.1" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>

		<li><a class="fNiv" href="academia/list.do"><spring:message
					code="master.page.academies" /></a></li>
		<li><a class="fNiv" href="curso/list.do"><spring:message
					code="master.page.courses" /></a></li>
		<li><a class="fNiv" href="estilo/list.do"><spring:message
					code="master.page.styles" /></a></li>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

