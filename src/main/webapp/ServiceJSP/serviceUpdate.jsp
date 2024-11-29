<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <h1>Modifier un Service</h1>

<form action="ServiceServlet?action=modifier&id=${service.id}" method="post">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" value="${service.nom}" required><br><br>

    <label for="description">Description :</label>
    <input type="text" id="description" name="description" value="${service.description}" required><br><br>

    <label for="prix">Prix :</label>
    <input type="number" step="0.01" id="prix" name="prix" value="${service.prix}" required><br><br>

    <button type="submit">Mettre à jour</button>
</form>
     
</body>
</html>