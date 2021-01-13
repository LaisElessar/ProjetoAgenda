<%@  page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, app.*" %>

<!DOCTYPE html SYSTEM "about:legacy-compat">

	<%
		ContatoDao dao = new ContatoDao();
		Contato cont = dao.getContato(request, response);
		
		SimpleDateFormat forData = new SimpleDateFormat("yyyy-MM-dd");
		String dt_nasc = forData.format(cont.getDataNascimento().getTime());
	%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Alterar Contato</title>
</head>
<body>
	<h1>Altera Contato</h1>
	
	<hr />
	<form action="altContServlet">
		Id: <input type="text" name="id" value="<%= cont.getId() %>" /><br />
		Nome: <input type="text" name="nome" value="<%=cont.getNome() %>"/><br />
		E-mail: <input type="email" name="email" value="<%=cont.getEmail() %>"/><br />
		Endereço: <input type="text" name="endereco" value="<%=cont.getEndereco() %>"/><br />
		Data Nascimento:
			 <input type="date" name="dataNascimento" value="<%= dt_nasc %>"/><br />
			 <input type="submit" value="Alterar"/><br />
	</form>

</body>
</html>