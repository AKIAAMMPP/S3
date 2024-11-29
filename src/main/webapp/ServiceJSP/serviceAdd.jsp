<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
      <h1>Ajouter Un Service</h1>
<form action="ServiceServlet" method="POST">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required><br><br>

    <label for="description">Description :</label>
    <input type="text" id="description" name="description" required><br><br>

    <label for="prix">Prix :</label>
    <input type="number" step="0.01" id="prix" name="prix" required><br><br>

    <button type="submit">Ajouter le service</button>
</form>
      
</body>
</html>