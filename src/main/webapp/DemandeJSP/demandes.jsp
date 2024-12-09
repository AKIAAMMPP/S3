<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/include/css.jsp" %>
</head>
<body>
<section id="container">
    <div id="wrapper">
        <!-- Sidebar -->
        <%@ include file="/include/sidebar.jsp" %>
        <!-- End of Sidebar -->

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@ include file="/include/header1.jsp" %>
                <div class="container-fluid">
                    <!-- Formulaire pour afficher les demandes -->
                    <%@ include file="/include/header.jsp" %>
                    <!-- Content Row -->
                    <div class="row">
                     
	                    <c:choose>
						    <c:when test="${empty demandes}">
						    </c:when>
						    <c:otherwise>
						        <h6 class="font-weight-bold text-secondary"></h6>
						        <table class="table table-bordered mt-3">
								    <thead>
								        <tr>
								       		<th>Service</th>
								            <th>Description</th>
								            <th>Affecter la demande</th>
								        </tr>
								    </thead>
								    <tbody>
								        <c:forEach var="demande" items="${demandes}">
								            <tr>
								            	<td>${demande.serviceName}</td>
								                <td>${demande.demandeDescription}</td>
								                <td>
												    <!-- Ajouter le choix des techniciens s'il y en a -->
												    <c:if test="${not empty techniciens_s}">
												        <form action="InterventionServlet" method="post" class="d-flex align-items-center">
												            <input type="hidden" name="demandeId" value="${demande.id}" />
												            <input type="hidden" name="rapport" value="NONE" />
												            <input type="hidden" name="note" value="NONE" />
												            <input type="hidden" name="commentaire" value="NONE" /> 
												            
												            <!-- Sélection de techniciens filtrés -->
									                        <select name="technicienId" class="form-control mr-2">
									                            <c:forEach var="technicien" items="${techniciens_s}">
									                                <!-- Filtrer les techniciens dont le nom de service correspond -->
									                                <c:if test="${technicien.service == demande.serviceName}">
									                                    <option value="${technicien.id}">${technicien.nom} ${technicien.prenom}</option>
									                                </c:if>
									                            </c:forEach>
									                        </select>
												            
												            <!-- Bouton de création de l'intervention -->
												            <button type="submit" name="action" value="creerIntervention" class="btn btn-success btn-sm">Affecter</button>
												        </form>
												    </c:if>
												</td>
	
								            </tr>
								        </c:forEach>
								    </tbody>
								</table>
						    </c:otherwise>
						</c:choose>
						<c:choose>
						    <c:when test="${empty interventions}">
						    </c:when>
						    <c:otherwise>
						        <h6 class="font-weight-bold text-secondary mt-4">Table des Interventions</h6>
						        <table class="table table-bordered mt-3">
						            <thead>
						                <tr>
						                    <th scope="col">Nom du Service</th>
						                    <th scope="col">Nom du Technicien</th>
						                    <th scope="col">Date Intervention</th>
						                    <th scope="col">Statut</th>
						                    <th scope="col">Note</th>
						                    <th scope="col">Commentaire</th>
						                    <th scope="col">Actions</th>
						                </tr>
						            </thead>
						            <tbody>
						                <c:forEach var="intervention" items="${interventions}">
						                    <tr>
						                        <td>${intervention.serviceName}</td>
						                        <td>${intervention.technicienName}</td>
						                        <td>${intervention.dateIntervention}</td>
						                       <td style="color: green;">
												    ${intervention.statut}
												</td>

						                        <td>${intervention.note}</td>
						                        <td>${intervention.commentaire}</td>
						                        <td>
						                            <!-- Actions -->
						                            <form action="ClientServlet" method="post" style="display:inline;">
						                                <!-- Bouton "Supprimer" avec confirmation -->
						                                <button type="submit" class="btn btn-danger btn-sm" 
						                                        onclick="return confirm('Voulez-vous vraiment supprimer cette intervention ?');">
						                                    Supprimer
						                                </button>
						                                <!-- Ajouter ici d'autres actions si nécessaire -->
						                            </form>
						                        </td>
						                    </tr>
						                </c:forEach>
						            </tbody>
						        </table>
						    </c:otherwise>
						</c:choose>
						
						<c:choose>
						    <c:when test="${empty total_techniciens}">
						    </c:when>
						    <c:otherwise>
						        <!-- Bouton pour ajouter un technicien, aligné à droite et collé au tableau -->
						        <div class="d-flex justify-content-between align-items-center mb-2">
						            <form action="${pageContext.request.contextPath}/TechnicientJSP/technicientAdd.jsp" method="post" style="display:inline;">
						                <button type="submit" name="action" value="ajouter" class="btn btn-primary btn-sm">Ajouter un Technicien</button>
						            </form>
						        </div>
						
						        <!-- Tableau des techniciens -->
						        <table class="table table-bordered mt-3">
						            <thead class="thead-light">
						                <tr>
						                    <th>Nom</th>
						                    <th>Prénom</th>
						                    <th>Expérience</th>
						                    <th>Service</th>
						                    <th>Actions</th>
						                </tr>
						            </thead>
						            <tbody>
						                <c:forEach var="technicien" items="${total_techniciens}">
						                    <tr>
						                        <td>${technicien.nom}</td>
						                        <td>${technicien.prenom}</td>
						                        <td>${technicien.experience} ans</td>
						                        <td>${technicien.service}</td>
						                        <td>
						                            <!-- Bouton Modifier -->
						                            <form action="TechnicienServlet" method="get" style="display:inline;">
						                                <input type="hidden" name="technicienId" value="${technicien.id}" />
						                                <button type="submit" name="action" value="modifier" class="btn btn-warning btn-sm">Modifier</button>
						                            </form>
						
						                            <!-- Bouton Supprimer -->
						                            <form action="TechnicienServlet" method="get" style="display:inline;">
						                                <input type="hidden" name="technicienId" value="${technicien.id}" />
						                                <button type="submit" name="action" value="supprimer" class="btn btn-danger btn-sm">Supprimer</button>
						                            </form>
						                        </td>
						                    </tr>
						                </c:forEach>
						            </tbody>
						        </table>
						    </c:otherwise>
						</c:choose>
						<!-- Vérifiez si la liste des services est vide -->
						<c:choose>
						    <c:when test="${empty services_s}">
						    </c:when>
						    <c:otherwise>
								<div class="d-flex justify-content-between align-items-center mb-2">
						            <form action="${pageContext.request.contextPath}/ServiceJSP/serviceAdd.jsp" method="post" style="display:inline;">
						                <button type="submit" name="action" value="ajouter" class="btn btn-primary btn-sm">Ajouter un Service</button>
						            </form>
						        </div>
						        <table class="table table-bordered mt-3">
						            <thead>
						                <tr>
						                    <th>Service</th>
						                    <th>Description</th>
						                    <th>Actions</th>
						                </tr>
						            </thead>
						            <tbody>
						                <c:forEach var="service" items="${services_s}">
						                    <tr>
						                        <td>${service.nom}</td>
						                        <td>${service.description}</td>
						                        <td>
						                            <form action="ServiceServlet" method="get" style="display:inline;">
													    <input type="hidden" name="action" value="modifier">
													    <input type="hidden" name="id" value="${service.id}">
													    <button type="submit" class="btn btn-warning btn-sm">Modifier</button>
													</form>

						                            <form action="ServiceServlet" method="post" style="display:inline;" onsubmit="return confirmDeletion();">
						                            	<input type="hidden" name="action" value="delete">
													    <input type="hidden" name="id" value="${service.id}" />
													    <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
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
</section>
<%@ include file="/include/js.jsp" %>
<script>
    function confirmDeletion() {
        return confirm("Êtes-vous sûr de vouloir supprimer ce service ?");
    }
</script>

</body>
</html>
