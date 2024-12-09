<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
	    <meta charset="utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	    <meta name="description" content="" />
	    <meta name="author" content="" />
	    <title>Ajouter Un Technicien</title>
	    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
	    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
	    <link href="${pageContext.request.contextPath}/template1/css/styles.css" rel="stylesheet" />
	</head>

    <body id="page-top">
        <!-- Contact Section -->
        <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Ajouter Un Technicien</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
			        <div class="col-lg-6">
			            <form id="TechnicienServlet" action="${pageContext.request.contextPath}/TechnicienServlet" method="POST">
			                <!-- Nom input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="nom" name="nom" type="text" placeholder="Nom" required />
			                    <label for="nom">Nom</label>
			                </div>
			                <!-- Prénom input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="prenom" name="prenom" type="text" placeholder="Prénom" required />
			                    <label for="prenom">Prénom</label>
			                </div>
			                <!-- Email input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="email" name="email" type="email" placeholder="Email" required />
			                    <label for="email">Email</label>
			                </div>
			                <!-- Spécialité input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="specialite" name="specialite" type="text" placeholder="Spécialité" required />
			                    <label for="specialite">Spécialité</label>
			                </div>
			                <!-- Expérience input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="experience" name="experience" type="number" min="0" placeholder="Expérience (en années)" required />
			                    <label for="experience">Expérience (en années)</label>
			                </div>
			                <!-- Mot de passe input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="password" name="password" type="password" placeholder="Mot de passe" required />
			                    <label for="password">Mot de passe</label>
			                </div>
			                <!-- Service input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="service" name="service" type="text" placeholder="Service" required />
			                    <label for="service">Service</label>
			                </div>
			                <!-- Submit button -->
			                <div class="d-grid">
			                    <button class="btn btn-primary btn-xl" type="submit" name="testSubmit" value="true">Ajouter Technicien</button>
			                </div>
			            </form>
			        </div>
			    </div>
            </div>
        </section>
    </body>
</html>
