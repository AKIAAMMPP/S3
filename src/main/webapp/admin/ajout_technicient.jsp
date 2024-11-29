<!DOCTYPE html>
<html lang="en">
    <head>
	    <meta charset="utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	    <meta name="description" content="" />
	    <meta name="author" content="" />
	    <title>Creative - Start Bootstrap Theme</title>
	    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
	    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
	    <link href="${pageContext.request.contextPath}/template1/css/styles.css" rel="stylesheet" />
	 </head>
    <body id="page-top">
        <!-- Contact-->
        <section class="page-section" id="contact">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Login</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
			    <div class="col-lg-6">
			        <form id="addTechnicianForm" action="TechnicianServlet" method="POST">
			            <!-- Nom input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="technicianFirstName" name="nom" type="text" placeholder="Nom" data-sb-validations="required" />
			                <label for="technicianFirstName">Nom</label>
			                <div class="invalid-feedback" data-sb-feedback="technicianFirstName:required">Un nom est requis.</div>
			            </div>
			            <!-- Prénom input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="technicianLastName" name="prenom" type="text" placeholder="Prénom" data-sb-validations="required" />
			                <label for="technicianLastName">Prénom</label>
			                <div class="invalid-feedback" data-sb-feedback="technicianLastName:required">Un prénom est requis.</div>
			            </div>
			            <!-- Email input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="technicianEmail" name="email" type="email" placeholder="email@example.com" data-sb-validations="required,email" />
			                <label for="technicianEmail">Email</label>
			                <div class="invalid-feedback" data-sb-feedback="technicianEmail:required">Un email est requis.</div>
			                <div class="invalid-feedback" data-sb-feedback="technicianEmail:email">L'email n'est pas valide.</div>
			            </div>
			            <!-- Password input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="technicianPassword" name="password" type="password" placeholder="Mot de passe" data-sb-validations="required" />
			                <label for="technicianPassword">Mot de passe</label>
			                <div class="invalid-feedback" data-sb-feedback="technicianPassword:required">Un mot de passe est requis.</div>
			            </div>
			            <!-- Spécialité input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="technicianSpeciality" name="specialite" type="text" placeholder="Spécialité" data-sb-validations="required" />
			                <label for="technicianSpeciality">Spécialité</label>
			                <div class="invalid-feedback" data-sb-feedback="technicianSpeciality:required">Une spécialité est requise.</div>
			            </div>
			            <!-- Expérience input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="technicianExperience" name="experience" type="number" placeholder="Années d'expérience" data-sb-validations="required" />
			                <label for="technicianExperience">Années d'Expérience</label>
			                <div class="invalid-feedback" data-sb-feedback="technicianExperience:required">Une expérience est requise.</div>
			            </div>
			            <!-- Submit Button-->
			            <div class="d-grid">
			                <button class="btn btn-primary btn-xl" id="submitTechnicianButton" type="submit">Ajouter Technicien</button>
			            </div>
			        </form>
			    </div>
			</div>
        </section>
</html>
