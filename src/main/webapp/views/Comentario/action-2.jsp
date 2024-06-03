<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Comentarios</title>
</head>
<body>
    <h1>Comentarios</h1>
    <ul>
        <c:forEach var="comment" items="${comments}">
            <li>${comment.content} - ${comment.timestamp}</li>
        </c:forEach>
    </ul>
</body>
</html>