package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		String nome = "Notebook'i5 2013";
		String descricao = "Notebook i5";

		// try fecha a connection automaticamente
		try (Connection connection = new ConnectionPool().getConnection()) {
			connection.setAutoCommit(false);
			// for sql injection
			String sql = "INSERT INTO Produto (nome, descricao) values (?, ?)";

			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				adiciona("Notebook'i7", "Notebook i7", statement);
				adiciona("TV LCD", "32 polegadas", statement);
				adiciona("Blueray", "FULL HDMI", statement);
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				System.out.println("Roolback efetuado");
			}

		}
	}

	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		
		if(nome.equals("Blueray")) {
			throw new IllegalArgumentException("Problema ocorrido");
		}
		
		statement.setString(1, nome);
		statement.setString(2, descricao);

		boolean resultado = statement.execute();
		System.out.println(resultado);

		ResultSet resultSet = statement.getGeneratedKeys();
		while (resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id);
		}

		resultSet.close();
	}
}
