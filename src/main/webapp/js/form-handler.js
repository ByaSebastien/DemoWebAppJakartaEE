document.addEventListener("DOMContentLoaded", () => {
    const createNewAuthorBtn = document.getElementById("createNewAuthorBtn");
    const backToSelectAuthorBtn = document.getElementById("backToSelectAuthorBtn");
    const newAuthorSection = document.getElementById("newAuthorSection");
    const selectAuthorSection = document.getElementById("selectAuthorSection");
    let initialAuthorValue = authorSelect.value;
    // Fonction pour afficher le formulaire de création de nouvel auteur
    createNewAuthorBtn.addEventListener("click", () => {
        newAuthorSection.style.display = "block";
        selectAuthorSection.style.display = "none";
        const authorSelect = document.getElementById("authorSelect");
        authorSelect.selectedIndex = 0;
    });

    // Fonction pour revenir à la sélection d'un auteur existant
    backToSelectAuthorBtn.addEventListener("click", () => {
        newAuthorSection.style.display = "none";
        selectAuthorSection.style.display = "flex";
        const fieldsToReset = newAuthorSection.querySelectorAll("input");
        fieldsToReset.forEach(field => {
            field.value = field.defaultValue; // Remet la valeur par défaut définie en HTML
        });
        const authorSelect = document.getElementById("authorSelect");
        authorSelect.selectedIndex = initialAuthorValue;
    });
});

// Fonction de validation des données du formulaire
function validateForm() {
    const title = document.getElementById("title").value;
    const isbn = document.getElementById("isbn").value;
    const authorId = document.getElementById("authorSelect").value;
    const newAuthorFirstName = document.getElementById("newAuthorFirstName").value;
    const newAuthorLastName = document.getElementById("newAuthorLastName").value;

    // Vérification des champs obligatoires
    if (!title || !isbn) {
        alert("Le titre et l'ISBN sont obligatoires.");
        return false;
    }

    // Vérifier si un auteur est sélectionné ou si un nouvel auteur est créé
    if (!authorId && (!newAuthorFirstName || !newAuthorLastName)) {
        alert("Veuillez sélectionner un auteur existant ou créer un nouvel auteur.");
        return false;
    }

    return true; // Le formulaire est valide
}
