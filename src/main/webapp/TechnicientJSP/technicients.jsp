<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Technicients</title>
</head>
<body>
    <h1>Liste des Techniciens</h1>

    <!-- Bouton Ajouter un Technicien -->
    <form action="TechnicienServlet" method="get" style="display:inline;">
        <input type="hidden" name="action" value="ajouter">
        <button type="submit">Ajouter un Technicien</button>
    </form>

    <!-- Vérifiez si la liste des techniciens est vide -->
    <c:choose>
        <c:when test="${empty technicients}">
            <p>Aucun technicien disponible pour le moment.</p>
        </c:when>
        <c:otherwise>
            <!-- Création du tableau -->
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Spécialité</th>
                        <th>Expérience</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Parcours de la liste des technicients -->
                    <c:forEach var="technicien" items="${technicients}">
                        <tr>
                            <td>${technicien.id}</td>
                            <td>${technicien.nom}</td>
                            <td>${technicien.prenom}</td>
                            <td>${technicien.email}</td>
                            <td>${technicien.specialite}</td>
                            <td>${technicien.experience} ans</td>
                            <td>
                                <!-- Bouton Modifier -->
                                <form action="modifierTechnicien" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${technicien.id}" />
                                    <button type="submit">Modifier</button>
                                </form>

                                <!-- Bouton Supprimer -->
                                <form action="supprimerTechnicien" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${technicien.id}" />
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
