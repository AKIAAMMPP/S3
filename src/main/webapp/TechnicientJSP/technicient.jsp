
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
                                    <h6 class="m-0 font-weight-bold text-primary">Revenue Sources</h6>
                                   			<div class="card-body">
                                    <%
                                        Integer userIdi = (Integer) session.getAttribute("userId");
                                        if (userId != null) {
                                    %>
                                    <form action="TechnicienServlet?action=MettreAjour" method="post">
                                        <input type="hidden" name="id" value="<%= userIdi %>"/>
                                        <label for="disponibilite">Disponible :</label>
                                        <input type="checkbox" id="disponibilite" name="disponibilite" value="true"
                                               <c:if test="${not empty technicien and technicien.disponibilite}">checked</c:if> />
                                        <button type="submit">Mettre à jour</button>
                                    </form>
                                    <% } else { %>
                                    <p>Vous n'êtes pas connecté. <a href="login.jsp">Connexion</a></p>
                                    <% } %>
                                </div>
                          
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-pie pt-4 pb-2">
                                        <canvas id="myPieChart"></canvas>
                                    </div>
                                    <div class="mt-4 text-center small">
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-primary"></i> Direct
                                        </span>
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-success"></i> Social
                                        </span>
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-info"></i> Referral
                                        </span>
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


