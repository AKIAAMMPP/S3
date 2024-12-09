<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <!-- Topbar Search -->
			        <form action="${pageContext.request.contextPath}/ServiceServlet" method="get" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
					    <div class="input-group">
					        <input type="text" class="form-control bg-light border-0 small" name="search" placeholder="Rechercher un service..."
					            aria-label="Search" aria-describedby="basic-addon2">
					        <input type="hidden" name="action" value="filtrer"> <!-- Indique l'action à exécuter -->
					        <div class="input-group-append">
					            <button class="btn" type="submit" style="background-color: #FF5623; border-color: #FF5623;">
					                <i class="fas fa-search fa-sm" style="color: white;"></i>
					            </button>
					        </div>
					    </div>
					</form>
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                      
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"> <%
									    Integer userId = (Integer) session.getAttribute("userId");
									    if (userId != null) {
									%>
									        <p><strong><%= userId %></strong></p>
									       
									<%
									    } else {
									%>
									        <p>Vous n'êtes pas connecté. <a href="login.jsp">Connexion</a></p>
									<%
									    }
									%>
									     </span>
                                <img class="img-profile rounded-circle" src="${pageContext.request.contextPath}/template/img/profile1.PNG">
     						</a>
                        </li>
                    </ul>
                </nav>