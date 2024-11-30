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
                                    <h6 class="m-0 font-weight-bold text-primary">Services Disponibles</h6>
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
                                                        <h5 class="card-title">${service.nom}</h5>
                                                        <p class="card-text">${service.description}</p>
                                                        <div class="d-flex justify-content-between mt-auto">
														    <!-- Afficher l'ID du service pour vérification -->
														    <p>Service ID: ${service.id}</p>
														    <a href="DemandeServlet?action=ajouter&serviceId=${service.id}&userId=${sessionScope.userId}" class="btn btn-primary">Demander</a>
														    
														    <a href="annulerService?serviceId=${service.id}&userId=${sessionScope.userId}" class="btn btn-danger">Annuler</a>
														</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
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
