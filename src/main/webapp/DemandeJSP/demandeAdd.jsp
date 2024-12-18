<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Page pour demander un service" />
    <meta name="author" content="" />
    <title>Demander un Service</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/template1/css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
    <!-- Section pour demander un service -->
    <section class="page-section" id="demande-service">
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-lg-8 col-xl-6 text-center">
                    <h2 class="mt-0">Demander un Service</h2>
                    <hr class="divider" />
                    <p class="mb-4">${service.description}</p>
                </div>
            </div>
            <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                <div class="col-lg-6">
                    <form id="DemandeServiceForm" action="${pageContext.request.contextPath}/DemandeServlet" method="POST">
                        <!-- Champ cach� pour l'ID du service -->
                        <input type="hidden" name="serviceId" value="${serviceId}" />
                        <input type="hidden" name="statut" value="en_attente" />

                        <!-- Description -->
                        <div class="form-floating mb-3">
                            <textarea class="form-control" id="description" name="description" placeholder="Ajoutez une description" required></textarea>
                            <label for="description">Description</label>
                            <div class="invalid-feedback">La description est requise.</div>
                        </div>

                        <!-- Adresse -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="adresse" name="adresse" type="text" placeholder="Adresse" />
                            <label for="adresse">Adresse</label>
                        </div>

                        <!-- T�l�phone -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="telephone" name="telephone" type="text" placeholder="T�l�phone" />
                            <label for="telephone">T�l�phone</label>
                        </div>

                        <!-- Bouton d'envoi -->
                        <div class="d-grid">
                            <button class="btn btn-primary btn-xl" id="submitButton" type="submit">Envoyer la demande</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
