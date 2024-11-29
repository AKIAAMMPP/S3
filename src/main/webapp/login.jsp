<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
 <!-- Affichage des messages -->
    <% String message = request.getParameter("message"); %>
    <% if (message != null) { %>
        <p >vous êtes logout.</p>
    <% } %>
	<h1>Login</h1>
    <form action="LoginServlet?action=login" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Login</button>
    </form>
    <!-- Affichage des erreurs -->
    <% String error = request.getParameter("error"); %>
    <% if (error != null) { %>
        <p >Le mot de passe ou le username est incorrect.</p>
    <% } %>
</body>
</html>