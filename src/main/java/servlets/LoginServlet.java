package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import beans.User;
import dao.DAOAuth;
import dao.DAOFactory;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOAuth  authDao;

	 public void init() throws ServletException {
	        DAOFactory daoFactory = DAOFactory.getInstance();
	        this.authDao = daoFactory.getAuthDao();
	    }  
   
   
    public LoginServlet() {
        super();
       
    }
    
    

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	 req.getRequestDispatcher("login.jsp").forward(req, resp);
	}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String email = request.getParameter("email");
    String password = request.getParameter("password");

   
    User user = authDao.authenticate(email, password);

    if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("email", user.getEmail());
        session.setAttribute("typeUser", user.getTypeUser());

        // Rediriger en fonction du type d'utilisateur
        switch (user.getTypeUser()) {
            case "client":
                response.sendRedirect("ClientJSP/dashboard-client.jsp");
                System.out.print("hi");
                break;
            
            case "admin":
                response.sendRedirect("admin/admin.jsp");
                break;
            case "technicien":
                response.sendRedirect("TechnicientJSP/technicient.jsp");
                break;
        }
    } else {
        // Authentification échouée
        response.sendRedirect("login.jsp?error=invalid");
    }
	}

}
