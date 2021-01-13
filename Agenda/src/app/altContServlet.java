package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/altContServlet")
public class altContServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void service( HttpServletRequest request, HttpServletResponse response ) 
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		
		String dataEmTexto = request.getParameter("dataNascimento");
		
		Calendar dat_nasc = null;
		
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataEmTexto);
			dat_nasc = Calendar.getInstance();
			dat_nasc.setTime(date);
		} catch (ParseException e) {
			out.println("Erro de conversão da data");
			return;
		}
		
		Contato cont = new Contato();
		cont.setId(id);
		cont.setNome(nome);
		cont.setEmail(email);
		cont.setEndereco(endereco);
		cont.setDataNascimento(dat_nasc);
		
		ContatoDao dao = new ContatoDao();
		dao.altera(cont);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listContatos.jsp");
					rd.forward(request, response);
	}
	
}
