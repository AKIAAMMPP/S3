import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action reçue : " + action); // Log d'action

        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                System.out.println("Session invalidée avec succès.");
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            System.out.println("Action inconnue : " + action);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action inconnue.");
        }
    }
}
