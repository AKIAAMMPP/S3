<%@ include file="/include/css.jsp" %>
</head>
<body>
<section id="container">
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Sidebar -->
        <%@ include file="/include/sidebar1.jsp" %>
        <!-- End of Sidebar -->
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- Topbar -->
                <%@ include file="/include/header1.jsp" %>
                <!-- End of Topbar -->
                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Content Row -->
                    <%@ include file="/include/header.jsp" %>
                    <!-- Content Row -->
                    <div class="row">
                        <!-- Area Chart -->
                        <div class="col-12">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">

                                </div>
                                <!-- Contenu principal de la section -->
                                <div class="card-body">
								    <!-- Section pour afficher les services sous forme de cartes -->
								    <div class="row d-flex justify-content-start">
								        <!-- Exemple de carte de service -->
								        <c:forEach var="service" items="${services}">
								            <div class="col-lg-3 col-md-4 col-sm-6 col-12 mb-4">
								                <!-- Carte pour chaque service -->
								               <div class="card h-100 shadow-sm">
											    <div class="card-body d-flex flex-column">
											        <h5 class="card-title" style="font-size: 1.5rem;">${service.nom}</h5>
											        <!-- Section des boutons et ID -->
											        <p class="text-muted small mb-2">Prix: ${service.prix} DH</p>
											        <div class="mt-auto">
											            <!-- Affichage de l'ID -->
											            <p class="card-text text-muted">Description : ${service.description}</p>
											            <!-- Boutons -->
											            <div class="d-flex justify-content-between">
											                <a href="DemandeServlet?action=ajouter&serviceId=${service.id}&userId=${sessionScope.userId}" 
											                   class="btn btn-sm btn-danger px-3">Demander</a>
											            </div>
											        </div>
											    </div>
											</div>
								               
								            </div>
								        </c:forEach>
								    </div>
								    <!-- Section pour afficher la table des interventions -->
								    <div class="row">
								        <div class="col-12">
								            <c:choose>
											    <c:when test="${empty interventions}">
											    </c:when>
											    <c:otherwise>
											        <h6 class="font-weight-bold text-secondary mt-4">Table des Interventions</h6>
											        <table class="table table-bordered mt-3">
											            <thead>
											                <tr>
											                    <th>Nom du Service</th>
                   											    <th>Nom du Technicien</th>
											                    <th>Date Intervention</th>
											                    <th>Statut</th>
											                    <th>Actions</th>
											                </tr>
											            </thead>
											            <tbody>
														    <c:forEach var="intervention" items="${interventions}">
														        <tr>
														            <td>${intervention.serviceName}</td>
														            <td>${intervention.technicienName}</td>
														            <td>${intervention.dateIntervention}</td>
														            <td>${intervention.statut}</td>
														            <td>
														                <!-- Formulaire pour modifier la note et le commentaire (visible dans la section des actions uniquement) -->
														                <form action="ClientServlet" method="get" style="display:inline;">
														                    <input type="hidden" name="interventionId" value="${intervention.id}" />
														                    <input type="hidden" name="action" value="update" />
														                    <!-- Champ de saisie pour la note -->
														                    <input type="text" name="note" value="${intervention.note}" class="form-control" />
														                    <!-- Champ de saisie pour le commentaire -->
														                    <textarea name="commentaire" class="form-control">${intervention.commentaire}</textarea>
														                    <button type="submit" class="btn btn-success btn-sm">Sauvegarder</button>
														                </form>
														            </td>
														        </tr>
														    </c:forEach>
														</tbody>
											            
											        </table>
											    </c:otherwise>
											</c:choose>
								        </div>
								    </div>
								    <!-- Section pour afficher les demandes du client -->
									<div class="row">
									    <div class="col-12">
									        <h6 class="font-weight-bold text-secondary mt-4">Table des Demandes</h6>
									        <c:choose>
									            <c:when test="${empty demandes}">
									            </c:when>
									            <c:otherwise>
									                <table class="table table-bordered mt-3">
									                    <thead>
									                        <tr>
									                            <th>Nom du Service</th>
									                            <th>Description</th>
									                            <th>Statut</th>
									                            <th>Actions</th>
									                        </tr>
									                    </thead>
									                    <tbody>
									                        <c:forEach var="demande" items="${demandes}">
									                            <tr>
									                                <td>${demande.serviceName}</td>
									                                <td>${demande.demandeDescription}</td>
									                                <td>${demande.statut}</td>
									                                <td>
									                                    <!-- Bouton pour annuler la demande -->
									                                    <form action="DemandeServlet" method="get" style="display:inline;">
									                                        <input type="hidden" name="action" value="annuler" />
									                                        <input type="hidden" name="demandeId" value="${demande.id}" />
									                                        <button type="submit" class="btn btn-danger btn-sm">Annuler</button>
									                                    </form>
									                                </td>
									                            </tr>
									                        </c:forEach>
									                    </tbody>
									                </table>
									            </c:otherwise>
									        </c:choose>
									    </div>
									</div>
								</div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
            <!-- Footer -->
            <%@ include file="/include/footer.jsp" %>
            <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->
</section>

<%@ include file="/include/js.jsp" %>
</body>
</html>
