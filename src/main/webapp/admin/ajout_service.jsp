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
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Login</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
			    <div class="col-lg-6">
			        <form id="addServiceForm" action="ServiceServlet" method="POST">
			            <!-- Nom du service input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="serviceName" name="nom" type="text" placeholder="Nom du service" data-sb-validations="required" />
			                <label for="serviceName">Nom du Service</label>
			                <div class="invalid-feedback" data-sb-feedback="serviceName:required">Un nom est requis.</div>
			            </div>
			            <!-- Description input-->
			            <div class="form-floating mb-3">
			                <textarea class="form-control" id="serviceDescription" name="description" placeholder="Description du service" style="height: 100px;" data-sb-validations="required"></textarea>
			                <label for="serviceDescription">Description</label>
			                <div class="invalid-feedback" data-sb-feedback="serviceDescription:required">Une description est requise.</div>
			            </div>
			            <!-- Prix input-->
			            <div class="form-floating mb-3">
			                <input class="form-control" id="servicePrice" name="prix" type="number" step="0.01" placeholder="Prix du service" data-sb-validations="required" />
			                <label for="servicePrice">Prix</label>
			                <div class="invalid-feedback" data-sb-feedback="servicePrice:required">Un prix est requis.</div>
			            </div>
			            <!-- Submit Button-->
			            <div class="d-grid">
			                <button class="btn btn-primary btn-xl" id="submitServiceButton" type="submit">Ajouter Service</button>
			            </div>
			        </form>
			    </div>
			</div>
            </div>
        </section>
</html>
