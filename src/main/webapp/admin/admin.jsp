
    <%@ include file="/include/css.jsp" %>
</head>
<body>
<section id ="container">
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Sidebar -->
      	<%@ include file="/include/sidebar.jsp" %>
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
								    <!-- Ajout de la table des interventions -->
								    <h6 class="font-weight-bold text-secondary">Table des Interventions</h6>
								    <table class="table table-bordered mt-3">
								        <thead>
								            <tr>
								                <th>Service</th>
								                <th>Technicien</th>
								                <th>Description</th>
								                <th>Note</th>
								                <th>Commentaire</th>
								            </tr>
								        </thead>
								        <tbody>
								            <tr>
								                <td>Plomberie</td>
								                <td>John Doe</td>
								                <td>Réparation de fuite</td>
								                <td>5/5</td>
								                <td>Travail impeccable</td>
								            </tr>
								            <tr>
								                <td>Électricité</td>
								                <td>Jane Smith</td>
								                <td>Remplacement de disjoncteur</td>
								                <td>4/5</td>
								                <td>Travail correct</td>
								            </tr>
								            <tr>
								                <td>Peinture</td>
								                <td>Mike Brown</td>
								                <td>Peinture intérieure</td>
								                <td>3/5</td>
								                <td>Peu mieux faire</td>
								            </tr>
								        </tbody>
								    </table>
								</div>
                                
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="myAreaChart"></canvas>
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


