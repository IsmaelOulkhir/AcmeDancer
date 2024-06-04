<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestionar Tutorial</title>
</head>
<body>

    <h1>Gestionar Tutorial</h1>

    <form:form action="${pageContext.request.contextPath}${tutorial.id != null ? '/edit' : '/create'}" modelAttribute="tutorial" method="post">
        <form:hidden path="id" />
        
        <div>
            <label for="titulo">Título:</label>
            <form:input path="titulo" id="titulo" />
            <form:errors path="titulo" cssClass="error" />
        </div>

        <div>
            <label for="descripcion">Descripción:</label>
            <form:input path="descripcion" id="descripcion" />
            <form:errors path="descripcion" cssClass="error" />
        </div>

        <div>
            <label for="video">Enlace al video:</label>
            <form:input path="video" id="video" />
            <form:errors path="video" cssClass="error" />
        </div>

        <button type="submit">${tutorial.id != null ? 'Guardar' : 'Crear'}</button>
    </form:form>

    <c:if test="${tutorial.id != null}">
        <form:form action="${pageContext.request.contextPath}/delete" method="post">
            <input type="hidden" name="id" value="${tutorial.id}" />
            <button type="submit" name="delete">Eliminar</button>
        </form:form>
    </c:if>

</body>
</html>


