package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {

	Connection con;
	String sql;
	PreparedStatement pst;
	ResultSet rs;

	public void salvar(Aluno a) throws SQLException {
		try {
			con = db.getConexao();
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf(a.getDt_nasc());
			pst.setDate(3, dataSql);
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeResources();
		}
	}

	public List<Aluno> listar() {
		List<Aluno> alunos = new ArrayList<>();
		try {
			con = db.getConexao();
			sql = "SELECT * FROM aluno";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String sexo = rs.getString("sexo");
				Date dataSql = rs.getDate("dt_nasc");
				java.util.Date dataNasc = new java.util.Date(dataSql.getTime());
				Aluno aluno = new Aluno(id, nome, sexo, dataNasc);
				alunos.add(aluno);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeResources();
		}
		return alunos;
	}

	public void apagar(int id) {
		try {
			con = db.getConexao();
			sql = "DELETE FROM aluno WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Aluno apagado com sucesso!");
			} else {
				System.out.println("Nenhum aluno encontrado com o ID fornecido.");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeResources();
		}
	}

	public void alterar(Aluno a) {
		try {
			con = db.getConexao();
			sql = "UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ? WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf(a.getDt_nasc());
			pst.setDate(3, dataSql);
			pst.setInt(4, a.getId());
			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Aluno alterado com sucesso!");
			} else {
				System.out.println("Nenhum aluno encontrado com o ID fornecido.");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeResources();
		}
	}

	private void closeResources() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
			db.closeConexao();
		} catch