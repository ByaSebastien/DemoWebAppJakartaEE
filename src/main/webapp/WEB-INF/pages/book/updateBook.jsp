<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@ include file="/WEB-INF/imports/core.jsp" %>
    <script src="${pageContext.request.contextPath}/js/form-handler.js" defer></script> <!-- Chargement du JS -->
</head>
<body>

<%@ include file="/WEB-INF/layout/header.jsp" %>
<%@ include file="/WEB-INF/layout/navbar.jsp" %>

<main>
    <form id="bookForm" action="${pageContext.request.contextPath}/admin/book/update" method="POST"
          onsubmit="return validateForm()">
        <h2>Modifier le livre</h2>

        <!-- Champ caché pour l'ISBN (clé primaire) -->
        <input type="hidden" id="isbn" name="isbn" value="${book.isbn}" />

        <!-- Section pour le titre, ISBN et la description -->
        <div>
            <label for="title">Titre :</label>
            <input type="text" id="title" name="title" value="${book.title}" required><br>
        </div>
        <div>
            <label for="description">Description :</label>
            <textarea id="description" name="description" rows="5">${book.description}</textarea><br>
        </div>
        <div>
            <label for="publicationDate">Date de publication :</label>
            <input type="date" id="publicationDate" name="publicationDate" value="${book.publicationDate}" required><br>
        </div>

        <!-- Section pour choisir un auteur existant -->
        <div id="selectAuthorSection">
            <label for="authorSelect">Sélectionner un auteur existant : <button class="add-auteur-button" type="button" id="createNewAuthorBtn">➕</button></label>
            <select id="authorSelect" name="authorId">
                <option value="">-- Sélectionner un auteur --</option>
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}" <c:if test="${author.id == book.author.id}">selected</c:if>>${author.name}</option>
                </c:forEach>
            </select><br>
        </div>

        <!-- Section pour créer un nouvel auteur (cachée par défaut) -->
        <fieldset id="newAuthorSection" style="display: none;">
            <legend>Nouvel auteur :</legend>

            <div>
                <label for="newAuthorFirstName">Prénom :</label>
                <input type="text" id="newAuthorFirstName" name="newAuthorFirstName"><br>
            </div>

            <div>
                <label for="newAuthorLastName">Nom :</label>
                <input type="text" id="newAuthorLastName" name="newAuthorLastName"><br>
            </div>

            <div>
                <label for="newAuthorPseudo">Pseudo (facultatif) :</label>
                <input type="text" id="newAuthorPseudo" name="newAuthorPseudo"><br>
            </div>

            <div>
                <label for="newAuthorBirthDate">Date de naissance :</label>
                <input type="date" id="newAuthorBirthDate" name="newAuthorBirthDate"><br>
            </div>

            <button type="button" id="backToSelectAuthorBtn">Revenir à la sélection d'un auteur</button>
        </fieldset>

        <button class="submit-button" type="submit">Mettre à jour le livre</button>
    </form>
</main>

<%@ include file="/WEB-INF/layout/footer.jsp" %>
</body>
</html>
