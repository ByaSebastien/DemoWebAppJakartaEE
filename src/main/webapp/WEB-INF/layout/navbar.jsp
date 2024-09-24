<%@ page import="be.bstorm.demowebappjakartaee.entities.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% User user = (User) session.getAttribute("user"); %>
<nav class="navbar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
        <li>
            <c:if test="${user == null}">
                <form class="login-form" action="${pageContext.request.contextPath}/login" method="POST">
                    <input type="text" name="username" placeholder="Nom d'utilisateur" required>
                    <input type="password" name="password" placeholder="Mot de passe" required>
                    <button type="submit">Se connecter</button>
                </form>
            </c:if>
            <c:if test="${user != null}">
                <form class="logout-form" action="${pageContext.request.contextPath}/logout" method="POST">
                    <button type="submit">Se déconnecter</button>
                </form>
            </c:if>
        </li>
    </ul>
</nav>
