package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delContatoServlet")
public class delContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws IOException, ServletException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		ContatoDao dao = new ContatoDao();
		dao.remove(id);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listContatos.jsp");
			rd.forward(request, response);
	}
}
