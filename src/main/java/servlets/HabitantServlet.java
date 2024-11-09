package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import beans.Habitant;
import dao.DAOFactory;
import dao.HabitantDao;



public class HabitantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HabitantDao habitantDao;

	 public void init() throws ServletException {
	        DAOFactory daoFactory = DAOFactory.getInstance();
	        this.habitantDao =daoFactory.getHabitantDao();
	    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HabitantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("habitants", habitantDao.habitants());
		this.getServletContext().getRequestDispatcher("/habitants.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String password = request.getParameter("password");
        String cin = request.getParameter("cin");
        String addresse = request.getParameter("addresse");
        String dateString = request.getParameter("dateDeNaissance"); // in format yyyy-MM-dd
        String metier = request.getParameter("metier");
        String email = request.getParameter("email");
     // Parse date
        Date dateDeNaissance = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateDeNaissance = (Date) dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

       
        

        // Create a new Habitant object
        Habitant habitant = new Habitant();
        habitant.setUsername(username);
        habitant.setNom(nom);
        habitant.setPrenom(prenom);
        habitant.setPassword(password);
        habitant.setCin(cin);
        habitant.setAddresse(addresse);
        habitant.setDateDeNaissance(dateDeNaissance);
        habitant.setMetier(metier);
        habitant.setEmail(email);

        habitantDao.createHabitant(habitant);
		// Redirect to a success page
		response.sendRedirect("habitants.jsp");
    }
	}


