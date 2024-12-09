<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Ajouter un Client</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/template1/css/styles.css" rel="stylesheet" />
</head>

<body id="page-top">
    <!-- Section pour ajouter un client -->
    <section class="page-section" id="ajouter-client">
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-lg-8 col-xl-6 text-center">
                    <h2 class="mt-0">Ajouter un Client</h2>
                    <hr class="divider" />
                </div>
            </div>
            <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                <div class="col-lg-6">
                    <form id="ClientForm" action="${pageContext.request.contextPath}/ClientServlet" method="POST">
                        <!-- Nom -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="nom" name="nom" type="text" placeholder="Nom" required />
                            <label for="nom">Nom</label>
                            <div class="invalid-feedback" data-sb-feedback="nom:required">Le nom est requis.</div>
                        </div>
                        <!-- Prénom -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="prenom" name="prenom" type="text" placeholder="Prénom" required />
                            <label for="prenom">Prénom</label>
                            <div class="invalid-feedback" data-sb-feedback="prenom:required">Le prénom est requis.</div>
                        </div>
                        <!-- Email -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="email" name="email" type="email" placeholder="name@example.com" required />
                            <label for="email">Email</label>
                            <div class="invalid-feedback" data-sb-feedback="email:required">L'email est requis.</div>
                            <div class="invalid-feedback" data-sb-feedback="email:email">L'email n'est pas valide.</div>
                        </div>
                        <!-- Adresse -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="adresse" name="adresse" type="text" placeholder="Adresse" required />
                            <label for="adresse">Adresse</label>
                            <div class="invalid-feedback" data-sb-feedback="adresse:required">L'adresse est requise.</div>
                        </div>
                        <!-- Mot de passe -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="password" name="password" type="password" placeholder="Mot de passe" required />
                            <label for="password">Mot de passe</label>
                            <div class="invalid-feedback" data-sb-feedback="password:required">Le mot de passe est requis.</div>
                        </div>
                        <!-- Téléphone -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="telephone" name="telephone" type="text" placeholder="Téléphone" required />
                            <label for="telephone">Téléphone</label>
                            <div class="invalid-feedback" data-sb-feedback="telephone:required">Le numéro de téléphone est requis.</div>
                        </div>
                        <!-- Bouton d'envoi -->
                        <div class="d-grid">
                            <button class="btn btn-primary btn-xl" id="submitButton" type="submit">Ajouter Client</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
