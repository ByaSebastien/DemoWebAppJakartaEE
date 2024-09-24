<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<% User user = (User) session.getAttribute("user"); %>--%>
<html>
<head>
    <%@ include file="/WEB-INF/imports/core.jsp" %>
</head>
<body>
<%--<jsp:include page="/WEB-INF/layout/header.jsp">--%>
<%--    <jsp:param name="title" value="Mon super titre dynamique" /> => <h1>${param.title}</h1> --%>
<%--</jsp:include>--%>
<%@ include file="/WEB-INF/layout/header.jsp" %>
<%@ include file="/WEB-INF/layout/navbar.jsp" %>
<main>
    <div class="table-header">
        <h2>Liste des livres</h2>
        <c:if test="${user != null && user.getRole().equals('ADMIN')}">
            <button class="addButton"><a href="${pageContext.request.contextPath}/admin/book/create">Ajouter un nouveau
                livre</a></button>
        </c:if>
    </div>
    <table>
        <thead>
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Date de publication</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.authorName}</td>
                <td><fmt:formatDate value="${book.publicationDateAsUtilDate}" pattern="dd MMMM yyyy"/></td>
                <td>
                    <button class="detailsButton">
                        <a href="${pageContext.request.contextPath}/book/details?isbn=${book.isbn}">Details</a>
                    </button>
                    <c:if test="${user != null && user.getRole().equals('ADMIN')}">
                        <button class="updateButton"><a
                                href="${pageContext.request.contextPath}/admin/book/update?isbn=${book.isbn}">Modifier</a>
                        </button>
                        <form class="delete-form-button" action="${pageContext.request.contextPath}/admin/book/delete"
                              method="POST" style="display:inline;">
                            <input type="hidden" name="isbn" value="${book.isbn}"/>
                            <button type="submit" class="deleteButton">Supprimer</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<%@ include file="/WEB-INF/layout/footer.jsp" %>
</body>
</html>
