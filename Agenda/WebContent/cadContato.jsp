<%@  page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.*, java.text.*, app.*" %>

<!DOCTYPE html SYSTEM "about:legacy-compat">

	<!-- Buscar último número de cadastro -->
	<%
		ContatoDao dao = new ContatoDao();
	%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cadastro de Contato</title>

</head>
<body>
	<h1>Cadastro de Contatos:</h1>
	
	<hr />
	<form action="addContatoServlet" class="form">
		Id: <input type="text" name="id" value="<%= dao.getultCod() %>" disabled="disabled" size="3" /><br />
		Nome: <input type="text" name="nome" /><br />
		E-mail: <input type="email" name="email"/><br />
		Endereço: <input type="text" name="endereco"/><br />
		Data Nascimento:
			 <input type="date" name="dataNascimento"/><br />
			 <input type="submit" value="cadastrar"/><br />
	</form>

</body>
</html>