<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <h1>Modifier un Client</h1>

<form action="ClientServlet?action=modifier&id=${client.id}" method="post">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" value="${client.nom}" required><br><br>

    <label for="prenom">Prénom :</label>
    <input type="text" id="prenom" name="prenom" value="${client.prenom}" required><br><br>

    <label for="email">Email :</label>
    <input type="email" id="email" name="email" value="${client.email}" required><br><br>

    <label for="password">Mot de passe :</label>
    <input type="password" id="password" name="password" value="${client.password}" required><br><br>

    <label for="adresse">Adresse :</label>
    <input type="text" id="adresse" name="adresse" value="${client.adresse}" required><br><br>

    <label for="telephone">Téléphone :</label>
    <input type="text" id="telephone" name="telephone" value="${client.telephone}" required><br><br>

    <button type="submit">Mettre à jour</button>
</form>
     
</body>
</html>