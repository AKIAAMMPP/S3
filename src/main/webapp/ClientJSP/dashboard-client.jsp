
    <%@ include file="/include/css.jsp" %>
</head>
<body>
<section id ="container">
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
                        <div class="col-xl-8 col-lg-7">
						    <div class="card shadow mb-4">
						        <!-- Card Header - Dropdown -->
						        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						            <h6 class="m-0 font-weight-bold text-primary">Services Disponibles</h6>
						        </div>
						        
						        <!-- Contenu principal de la section -->
						        <div class="card-body">
						            <!-- Section pour afficher les services sous forme de cartes -->
						            <div class="row">
						                <!-- Exemple de carte de service -->
						                <c:forEach var="service" items="${services}">
						                    <div class="col-lg-4 col-md-6 mb-4">
						                        <!-- Carte pour chaque service -->
						                        <div class="card">
						                            <div class="card-body">
						                                <h5 class="card-title">${service.nom}</h5>
						                                <p class="card-text">${service.description}</p>
						                                <div class="d-flex justify-content-between">
						                                    <!-- Boutons pour chaque service -->
						                                    <form action="demanderService" method="post" style="display:inline;">
						                                        <input type="hidden" name="serviceId" value="${service.id}" />
						                                        <button type="submit" class="btn btn-primary">Demander</button>
						                                    </form>
						                                    <form action="annulerService" method="post" style="display:inline;">
						                                        <input type="hidden" name="serviceId" value="${service.id}" />
						                                        <button type="submit" class="btn btn-danger">Annuler</button>
						                                    </form>
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


