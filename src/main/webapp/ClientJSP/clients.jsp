<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liste des clients</title>
</head>
<body>
    <h1>Clients :</h1>

<!-- Affichage de la liste des clients -->
<c:forEach var="client" items="${clients}">
    <!-- Affichage des informations sur chaque client -->
    <p>
        <strong>ID :</strong> <c:out value="${client.id}" /><br>
        <strong>Nom :</strong> <c:out value="${client.nom}" /><br>
        <strong>Prénom :</strong> <c:out value="${client.prenom}" /><br>
        <strong>Email :</strong> <c:out value="${client.email}" /><br>
        <strong>Adresse :</strong> <c:out value="${client.adresse}" /><br>
        <strong>Téléphone :</strong> <c:out value="${client.telephone}" /><br>

        <!-- Bouton pour modifier le client -->
        <a href="ClientServlet?action=modifier&id=${client.id}" class="button">Modifier</a>

        <!-- Bouton pour supprimer le client -->
        <a href="ClientServlet?action=supprimer&id=${client.id}" class="button" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce client ?');">Supprimer</a>
    </p>
</c:forEach>

<!-- Lien vers la page d'ajout d'un client -->
<a href="ClientServlet?action=ajouter" class="button">Ajouter un Client</a>


</body>
</html>