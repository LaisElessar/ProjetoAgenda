package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public class ContatoDao {
	// a conex√£o com o banco de dados
	private Connection connection;

	public ContatoDao() {
		this.connection = new Conexao().getConnection();
	}

	// Metodo para Insert no Banco
	public void adiciona(Contato contato) {
		String sql = "insert into contato " + "(nome,email,endereco,dataNascimento)" + " values (?,?,?,?)";
		try {
			// prepared statement para inserÁ„o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Metodo para Executar uma Select no Banco
	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contato");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data atrav√©s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adicionando o objeto √† lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Contato contato) {
		String sql = "update contato set nome=?, email=?, endereco=?," + " dataNascimento=? where id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Contato contato) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete" + "from contato where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from contato where id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Contato getContato(HttpServletRequest request, HttpServletResponse response)
			throws SerialException, IOException {

		// OBS: alterar o agrupamento do banco para utf8_general_ci

		try {
			int idContato = Integer.parseInt(request.getParameter("idContato").toString());
			PreparedStatement stmt = this.connection.prepareStatement("Select * From contato Where id =" + idContato);
			ResultSet rs = stmt.executeQuery();
			Contato cont = new Contato();

			while (rs.next()) {

				cont.setId(rs.getInt("id"));
				cont.setNome(rs.getString("nome"));
				cont.setEmail(rs.getString("email"));
				cont.setEndereco(rs.getString("endereco"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				System.out.println(data);
				cont.setDataNascimento(data);

			}

			rs.close();
			stmt.close();
			return cont;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// Buscar o numero do ultimo contato
	public int getultCod() {
		int cont = 0;
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT id FROM contato");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cont = rs.getInt("id");
			}

			cont++;

			rs.close();
			stmt.close();
			return cont;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
