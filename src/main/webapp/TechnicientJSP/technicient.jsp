
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
                    <%@ include file="/include/header.jsp" %>
                    <div class="row">
						<!-- Area Chart -->
						<div class="col-xl-8 col-lg-7">
						    <div class="card shadow mb-4">
						        <!-- Card Header - Dropdown -->
						        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						            <h6 class="m-0 font-weight-bold text-primary">Earnings Overview</h6>
						            <div class="dropdown no-arrow">
						                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
						                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						                    <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
						                </a>
						                <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
						                    aria-labelledby="dropdownMenuLink">
						                    <div class="dropdown-header">Dropdown Header:</div>
						                    <a class="dropdown-item" href="#">Action</a>
						                    <a class="dropdown-item" href="#">Another action</a>
						                    <div class="dropdown-divider"></div>
						                    <a class="dropdown-item" href="#">Something else here</a>
						                </div>
						            </div>
						        </div>
						        <!-- Contenu principal de la section -->
						        <div class="card-body">
						            <!-- Section des cartes -->
						            <div class="row">
						                <!-- Carte 1 -->
						                <div class="col-md-4">
						                    <div class="card shadow mb-4">
						                        <div class="card-header py-3">
						                            <h6 class="m-0 font-weight-bold text-primary">Service 1</h6>
						                        </div>
						                        <div class="card-body">
						                            <p><strong>Type:</strong> Plomberie</p>
						                            <p><strong>Description:</strong> Réparation de fuite.</p>
						                            <button class="btn btn-primary btn-sm">Voir Détails</button>
						                        </div>
						                    </div>
						                </div>
						                <!-- Carte 2 -->
						                <div class="col-md-4">
						                    <div class="card shadow mb-4">
						                        <div class="card-header py-3">
						                            <h6 class="m-0 font-weight-bold text-primary">Service 2</h6>
						                        </div>
						                        <div class="card-body">
						                            <p><strong>Type:</strong> Électricité</p>
						                            <p><strong>Description:</strong> Remplacement de disjoncteur.</p>
						                            <button class="btn btn-primary btn-sm">Voir Détails</button>
						                        </div>
						                    </div>
						                </div>
						                <!-- Carte 3 -->
						                <div class="col-md-4">
						                    <div class="card shadow mb-4">
						                        <div class="card-header py-3">
						                            <h6 class="m-0 font-weight-bold text-primary">Service 3</h6>
						                        </div>
						                        <div class="card-body">
						                            <p><strong>Type:</strong> Peinture</p>
						                            <p><strong>Description:</strong> Peinture intérieure.</p>
						                            <button class="btn btn-primary btn-sm">Voir Détails</button>
						                        </div>
						                    </div>
						                </div>
						            </div>
						        </div>
						    </div>
						</div>
                        <!-- Pie Chart -->
                        <div class="col-xl-4 col-lg-5">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Changer votre statut</h6>
                                    <div class="dropdown no-arrow">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                            aria-labelledby="dropdownMenuLink">
                                            <div class="dropdown-header">Dropdown Header:</div>
                                            
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="#">Something else here</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-pie pt-1 pb-2">
                                        <canvas id="myPieChart"></canvas>
                                    </div>
                                    <div>
  <form action="TechnicienServlet?action=MettreAjour" method="post">
    <input type="hidden" name="action" value="updateDisponibilite"/> 
    <input type="hidden" name="id" value="${technicien.id}"/> 
    <label for="disponibilite">Disponible :</label> 
    <input type="checkbox" id="disponibilite" name="disponibilite" value="true"
        <c:if test="${technicien.disponibilite}">checked</c:if>/> <!-- "true" si disponible -->

    <button type="submit">MettreAjour</button>
</form>




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


