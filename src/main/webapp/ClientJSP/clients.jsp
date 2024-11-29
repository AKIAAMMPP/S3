<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Clients</title>
</head>
<body>
    <h1>Liste des Clients</h1>

    <!-- Bouton Ajouter un Client -->
    <form action="ClientServlet" method="get" style="display:inline;">
        <input type="hidden" name="action" value="ajouter">
        <button type="submit">Ajouter un Client</button>
    </form>

    <!-- Vérifiez si la liste des clients est vide -->
    <c:choose>
        <c:when test="${empty clients}">
            <p>Aucun client disponible pour le moment.</p>
        </c:when>
        <c:otherwise>
            <!-- Création du tableau -->
            <table border="1">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Adresse</th>
                        <th>Téléphone</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Parcours de la liste des clients -->
                    <c:forEach var="client" items="${clients}">
                        <tr>
                            <td>${client.nom}</td>
                            <td>${client.prenom}</td>
                            <td>${client.email}</td>
                            <td>${client.adresse}</td>
                            <td>${client.telephone}</td>
                            <td>
                                <!-- Bouton Modifier -->
                                <form action="modifierClient" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${client.id}" />
                                    <button type="submit">Modifier</button>
                                </form>

                                <!-- Bouton Supprimer -->
                                <form action="supprimerClient" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${client.id}" />
                                    <button type="submit">Supprimer</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
