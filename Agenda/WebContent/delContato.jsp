<%@  page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Excluir Contato</title>
</head>
<body>
	<h1>Excluir Contato:</h1>
	
	<hr />
	<form action="delContatoServlet">
		Id: <input type="text" name="id" value="<%= cont.getId() %>" /><br />
		Nome: <input type="text" name="nome" value="<%=cont.getNome() %>"/><br />
		E-mail: <input type="email" name="email" value="<%=cont.getEmail() %>"/><br />
		Endereço: <input type="text" name="endereco" value="<%=cont.getEndereco() %>"/><br />
		Data Nascimento:
			 <input type="date" name="dataNascimento" value="<%= dt_nasc %>"/><br />
			 <input type="submit" value="Excluir"/><br />
	</form>

</body>
</html>