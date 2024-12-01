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
					        <h6 class="font-weight-bold text-secondary">Table des Demandes</h6>
					        <table class="table table-bordered mt-3">
							    <thead>
							        <tr>
							       		<th>Service</th>
							            <th>Description</th>
							            <th>Actions</th>
							        </tr>
							    </thead>
							    <tbody>
							        <c:forEach var="demande" items="${demandes}">
							            <tr>
							            	<td>${demande.serviceName}</td>
							                <td>${demande.demandeDescription}</td>
							                <td>
											    <!-- Ajouter le choix des techniciens s'il y en a -->
											    <c:if test="${not empty techniciens}">
											        <form action="InterventionServlet" method="post" class="d-flex align-items-center">
											            <input type="hidden" name="demandeId" value="${demande.id}" />
											            <input type="hidden" name="rapport" value="NONE" /> <!-- Valeur par défaut -->
											            <input type="hidden" name="note" value="NONE" /> <!-- Valeur par défaut -->
											            <input type="hidden" name="commentaire" value="NONE" /> <!-- Valeur par défaut -->
											            
											            <!-- Sélection de techniciens -->
											            <select name="technicienId" class="form-control mr-2">
											                <c:forEach var="technicien" items="${techniciens}">
											                    <option value="${technicien.id}">${technicien.nom} ${technicien.prenom}</option>
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
					<!-- Pour la table des interventions -->
					<c:choose>
					    <c:when test="${empty interventions}">
					    </c:when>
					    <c:otherwise>
					        <h6 class="font-weight-bold text-secondary">Table des Interventions</h6>
					        <table class="table table-bordered mt-3">
					            <thead>
					                <tr>
					                    <th>ID Intervention</th>
					                    <th>Demande ID</th>
					                    <th>Technicien ID</th>
					                    <th>Date Intervention</th>
					                    <th>Statut</th>
					                    <th>Rapport</th>
					                    <th>Note</th>
					                    <th>Commentaire</th>
					                    <th>Actions</th>
					                </tr>
					            </thead>
					            <tbody>
					                <c:forEach var="intervention" items="${interventions}">
					                    <tr>
					                        <td>${intervention.id}</td>
					                        <td>${intervention.demandeId}</td>
					                        <td>${intervention.technicienId}</td>
					                        <td>${intervention.dateIntervention}</td>
					                        <td>${intervention.statut}</td>
					                        <td>${intervention.rapport}</td>
					                        <td>${intervention.note}</td>
					                        <td>${intervention.commentaire}</td>
					                        <td>
					                            <form action="InterventionServlet" method="get" style="display:inline;">
					                                <input type="hidden" name="interventionId" value="${intervention.id}" />
					                                <button type="submit" name="action" value="delete" class="btn btn-danger btn-sm">Supprimer</button>
					                            </form>
					                            <form action="TechnicienServlet" method="get" style="display:inline;">
					                                <input type="hidden" name="interventionId" value="${intervention.id}" />
					                                <button type="submit" name="action" value="affecter" class="btn btn-primary btn-sm">Affecter</button>
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
</section>
<%@ include file="/include/js.jsp" %>
</body>
</html>
