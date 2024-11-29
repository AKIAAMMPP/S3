<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Services</title>
</head>
<body>
    <h1>Liste des Services</h1>
    <form action="ServiceServlet" method="post" style="display:inline;">
             <button type="submit">Modifier</button>
    </form>

    <!-- Vérifiez si la liste des services est vide -->
    <c:choose>
        <c:when test="${empty services}">
            <p>Aucun service disponible pour le moment.</p>
        </c:when>
        <c:otherwise>
            <!-- Création du tableau -->
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Prix</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Parcours de la liste des services -->
                    <c:forEach var="service" items="${services}">
                        <tr>
                            <td>${service.id}</td>
                            <td>${service.nom}</td>
                            <td>${service.description}</td>
                            <td>${service.prix}</td>
                            <td>
                                <!-- Bouton Modifier -->
                                <form action="modifierService" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${service.id}" />
                                    <button type="submit">Modifier</button>
                                </form>

                                <!-- Bouton Supprimer -->
                                <form action="supprimerService" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${service.id}" />
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
