<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Demander un service</title>
</head>
<body>
    <div class="card-body d-flex flex-column">
        <h5 class="card-title">${service.nom}</h5>
        <p class="card-text">${service.description}</p>
        <form action="DemandeServlet" method="post">
            <!-- Champ caché pour l'ID du service -->
            <input type="hidden" name="serviceId" value="${serviceId}">
            <input type="hidden" name="statut" value="en_attente">
            <textarea name="description" placeholder="Ajoutez une description" required class="form-control mb-2"></textarea>
            <button type="submit" class="btn btn-primary">Demander</button>
        </form>
    </div>
</body>
</html>
