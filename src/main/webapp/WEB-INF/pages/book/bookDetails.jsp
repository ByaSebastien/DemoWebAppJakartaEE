<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@ include file="/WEB-INF/imports/core.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/layout/header.jsp" %>
<%@ include file="/WEB-INF/layout/navbar.jsp" %>

<main>
    <h2>Details pour le livre: ${book.title}</h2>
    <div class="book-details">
        <p><strong>Titre:</strong> ${book.title}</p>
        <p><strong>Auteur:</strong> ${book.author.name}</p>
        <p><strong>ISBN:</strong> ${book.isbn}</p>
        <p><strong>Date de publication: </strong>
            <fmt:formatDate value="${book.publicationDateAsUtilDate}" pattern="dd MMMM yyyy"/>
        </p>
        <p><strong>Description:</strong> ${book.description}</p>
    </div>
    <div class="back-to-list">
        <button><a href="${pageContext.request.contextPath}/">Retour à la liste</a></button>
    </div>
</main>

<%@ include file="/WEB-INF/layout/footer.jsp" %>
</body>
</html>
