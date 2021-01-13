<%@ page language="java" contentType="text/html ; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, app.*" %>
<!DOCTYPE html SYSTEM "about:legacy-compat">

<html>
<head>
	<title>Lista de Contatos</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<table>
		<tr>
			<th colspan="5" > Lista de Contatos </th>
		</tr>
		
		<tr>
			<th>ID</th>
			<th align="left" >Nome:</th>
			<th align="left" >Email:</th>
			<th align="left" >End:</th>
			<th align="left" >Data Nasc.:</th>
			<th align="left" >Editar:</th>
			<th align="left" >Excluir:</th>
		</tr>
		<% 
			ContatoDao dao = new ContatoDao();
			List<Contato> contatos = dao.getLista();
			for ( Contato contato : contatos ) {
				SimpleDateFormat forData = new SimpleDateFormat("dd/MM/yyyy");
				String dt_nasc = forData.format(contato.getDataNascimento().getTime());
			
		%>
		<tr>
			<th><%=contato.getId()%></th>
			<th align="left" ><%=contato.getNome()%></th>
			<th align="left" ><%=contato.getEmail()%></th>
			<th align="left" ><%=contato.getEndereco()%></th>
			<th align="left" ><%=dt_nasc%></th>
			<th align="right" >
				<a href='altContato.jsp?idContato=<%=contato.getId()%>' >OK</a>
			</th>
			<th align="right" >
				<a href='delContato.jsp?idContato=<%=contato.getId()%>' >OK</a>
			</th>
		</tr>
		
		<%
			}
		%>
	</table>
</body>