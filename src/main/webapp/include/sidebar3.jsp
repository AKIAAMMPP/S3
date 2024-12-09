<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Sidebar -->
<ul class="navbar-nav sidebar sidebar-dark accordion" id="accordionSidebar" 
    style="background-color: #FF5623;">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink" style="color: white;"></i>
        </div>
        <div class="sidebar-brand-text mx-3" style="color: white;">Technicien</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0" style="border-color: white;">

 

    <!-- Divider -->
    <hr class="sidebar-divider" style="border-color: white;">

    <!-- Heading -->
    <div class="sidebar-heading" style="color: white;">Interface</div>

    <!-- Nav Item - Services -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/ServiceServlet?action=list_service_admin" style="color: white;">
            <i class="fas fa-fw fa-concierge-bell" style="color: white;"></i>
            <span>Services</span>
        </a>
    </li>



    <!-- Nav Item - Interventions -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/TechnicienServlet?action=list_intervention_terminee" style="color: white;">
            <i class="fas fa-fw fa-tools" style="color: white;"></i>
            <span>Interventions</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider" style="border-color: white;">

  

    <!-- Nav Item - Logout -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/index1.jsp" style="color: white;">
            <i class="fas fa-fw fa-sign-out-alt" style="color: white;"></i>
            <span>Logout</span>
        </a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block" style="border-color: white;">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle" style="background-color: white;"></button>
    </div>

</ul>
<!-- End of Sidebar -->
