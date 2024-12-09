<%@ include file="/include/css.jsp" %>
</head>
<body>
<section id="container">
    <!-- Page Wrapper -->
    <div id="wrapper">
        
        <!-- Sidebar -->
        <%@ include file="/include/sidebar3.jsp" %>
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
                    
                    <!-- Header -->
                    <%@ include file="/include/header.jsp" %>
                    
                    <!-- Main Content Row -->
                    <div class="row">
                        
                        <!-- Main Content Area (Interventions Table) -->
                        <div class="col-xl-8 col-lg-7 mb-4">
                            <!-- Interventions Table Card -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Table des Interventions</h6>
                                </div>
                                <div class="card-body">
                                    
                                    <!-- Interventions en cours -->
                                    <c:choose>
									    <c:when test="${empty interventions}">
									        <p class="text-muted">Aucune intervention n'est disponible pour le moment.</p>
									    </c:when>
									    <c:otherwise>
									        <table class="table table-bordered mt-3 table-responsive">
									            <thead>
									                <tr>
									                    <th>Description</th>
									                    <th>Adresse</th>
									                    <th>Téléphone</th>
									                    <th>Statut</th>
									                    <th>Affirmer la fin de la tâche</th>
									                </tr>
									            </thead>
									            <tbody>
									                <!-- Parcours des interventions en cours -->
									                <c:forEach var="intervention" items="${interventions}">
									                    <!-- Vérification de la condition -->
									                        <tr>
									                            <td>${intervention.description}</td>
									                            <td>${intervention.adresse}</td>
									                            <td>${intervention.telephone}</td>
									                            <td>${intervention.statut}</td>
									                            <td>
									                                <form action="TechnicienServlet" method="get" style="display:inline;">
									                                    <input type="hidden" name="interventionId" value="${intervention.id}" />
									                                    <input type="hidden" name="technicienId" value="${userId}" />
									                                    <button type="submit" name="action" value="termine" class="btn btn-success btn-sm">
									                                        Terminer votre tâche ?
									                                    </button>
									                                </form>
									                            </td>
									                        </tr>
									                </c:forEach>
									            </tbody>
									        </table>
									    </c:otherwise>
									</c:choose>
                                    
                                    <!-- Interventions terminées -->
                                    <c:choose>
                                        <c:when test="${empty interventions_terminee}">
                                            <p class="text-muted">Aucune intervention terminée.</p>
                                        </c:when>
                                        <c:otherwise>
                                            <table class="table table-bordered mt-3 table-responsive">
                                                <thead>
                                                    <tr>
                                                        <th>Description</th>
                                                        <th>Adresse</th>
                                                        <th>Téléphone</th>
                                                        <th>Statut</th>
                                                        <th>Note</th>
                                                        <th>Commentaire</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="intervention" items="${interventions_terminee}">
                                                        <tr>
                                                            <td>${intervention.description}</td>
                                                            <td>${intervention.adresse}</td>
                                                            <td>${intervention.telephone}</td>
                                                            <td>${intervention.statut}</td>
                                                            <td>${intervention.note}</td>
                                                            <td>${intervention.commentaire}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                </div>
                            </div>
                        </div>
                        
                        <!-- Sidebar (Changer le statut) -->
                        <div class="col-xl-4 col-lg-5">
                            <!-- Changer le statut Card -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Changer votre statut</h6>
                                </div>
                                <div class="card-body">
                                    <% 
                                        Integer userIdi = (Integer) session.getAttribute("userId");
                                        if (userIdi != null) {
                                    %>
                                    <form action="TechnicienServlet?action=MettreAjour" method="post" style="border: 1px solid #ddd; padding: 15px; margin: 10px; border-radius: 5px;">
                                        <input type="hidden" name="id" value="<%= userIdi %>" />
                                        <h4 style="text-align: center; margin-bottom: 15px;">Mettre à jour la disponibilité</h4>
                                        
                                        <!-- Disponible -->
                                        <div style="margin-bottom: 10px;">
                                            <input 
                                                type="radio" 
                                                id="disponible" 
                                                name="disponibilite" 
                                                value="true"
                                                <c:if test="${not empty technicien and technicien.disponibilite}">checked</c:if> />
                                            <label for="disponible" style="margin-left: 5px;">Disponible</label>
                                        </div>
                                        
                                        <!-- Non disponible -->
                                        <div style="margin-bottom: 15px;">
                                            <input 
                                                type="radio" 
                                                id="non_disponible" 
                                                name="disponibilite" 
                                                value="false"
                                                <c:if test="${not empty technicien and not technicien.disponibilite}">checked</c:if> />
                                            <label for="non_disponible" style="margin-left: 5px;">Non disponible</label>
                                        </div>
                                        
                                        <div style="text-align: center;">
                                            <button type="submit" style="padding: 8px 20px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer;">
                                                Mettre à jour
                                            </button>
                                        </div>
                                    </form>
                                    <% } else { %>
                                    <p>Vous n'êtes pas connecté. <a href="login.jsp">Connexion</a></p>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <!-- End of Page Content -->
                
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

<!-- Scripts -->
<%@ include file="/include/js.jsp" %>
</body>
</html>
