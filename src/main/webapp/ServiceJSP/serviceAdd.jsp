<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
	    <meta charset="utf-8" />
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	    <meta name="description" content="" />
	    <meta name="author" content="" />
	    <title>Ajouter Un Service</title>
	    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
	    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
	    <link href="${pageContext.request.contextPath}/template1/css/styles.css" rel="stylesheet" />
	</head>

    <body id="page-top">
        <!-- Ajouter Un Service Section -->
        <section class="page-section" id="service">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Ajouter Un Service</h2>
                        <hr class="divider" />
                        <p class="text-muted mb-5">Remplissez le formulaire ci-dessous pour ajouter un service.</p>
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
			        <div class="col-lg-6">
			            <form id="ServiceServlet" action="${pageContext.request.contextPath}/ServiceServlet" method="POST">
			                <!-- Nom input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="nom" name="nom" type="text" placeholder="Nom" required />
			                    <label for="nom">Nom</label>
			                </div>
			                <!-- Description input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="description" name="description" type="text" placeholder="Description" required />
			                    <label for="description">Description</label>
			                </div>
			                <!-- Prix input -->
			                <div class="form-floating mb-3">
			                    <input class="form-control" id="prix" name="prix" type="number" step="0.01" placeholder="Prix" required />
			                    <label for="prix">Prix</label>
			                </div>
			                <!-- Submit button -->
			                <div class="d-grid">
			                    <button class="btn btn-primary btn-xl" type="submit">Ajouter le service</button>
			                </div>
			            </form>
			        </div>
			    </div>
            </div>
        </section>
    </body>
</html>
