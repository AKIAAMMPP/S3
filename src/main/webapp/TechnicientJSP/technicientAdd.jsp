<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter Un Technicien</title>
</head>
<body>
    <h1>Ajouter Un Technicien</h1>
    


    <!-- Ajouter un bouton de test pour vérifier l'envoi POST -->
    <form action="TechnicienServlet" method="POST">
    	 <label for="nom">Nom :</label>
	    <input type="text" id="nom" name="nom" required><br><br>
	
	    <label for="prenom">Prénom :</label>
	    <input type="text" id="prenom" name="prenom" required><br><br>
	
	    <label for="email">Email :</label>
	    <input type="email" id="email" name="email" required><br><br>
	
	    <label for="specialite">Spécialité :</label>
	    <input type="text" id="specialite" name="specialite" required><br><br>
	
	    <label for="experience">Expérience (en années) :</label>
	    <input type="number" id="experience" name="experience" min="0" required><br><br>
	
	    <label for="password">Mot de passe :</label>
	    <input type="password" id="password" name="password" required><br><br>
        <button type="submit" name="testSubmit" value="true">Test POST</button>
    </form>
</body>
</html>
