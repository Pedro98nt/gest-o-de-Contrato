package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.ProcessoPedro;
import br.edu.faculdadedelta.util.Conexao;

public class ProcessoDaoPedro {

	public void incluir(ProcessoPedro processo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO processos (numero_processo,descricao_processo,valor_processo) VALUES(?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, processo.getNumero().trim());
			ps.setString(2, processo.getProcesso().trim());
			ps.setDouble(3, processo.getValorProc());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ProcessoPedro processo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE processos SET numero_processo=?, descricao_processo =?, valor_processo=? WHERE id=? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setString(1, processo.getNumero().trim());
			ps.setString(2, processo.getProcesso().trim());
			ps.setDouble(3, processo.getValorProc());
			ps.setLong(4, processo.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(ProcessoPedro processo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM processos WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, processo.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<ProcessoPedro> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT * FROM processos  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProcessoPedro> listaRetorno = new ArrayList<ProcessoPedro>();

		try {

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProcessoPedro processo = new ProcessoPedro();
				processo.setId(rs.getLong("id"));
				processo.setNumero(rs.getString("numero_processo").trim());
				processo.setProcesso(rs.getString("descricao_processo").trim());
				processo.setValorProc(rs.getDouble("valor_processo"));
				listaRetorno.add(processo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}

	public ProcessoPedro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT * FROM processos WHERE id=? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProcessoPedro retorno = new ProcessoPedro();
		try {

			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNumero(rs.getString("numero_processo").trim());
				retorno.setProcesso(rs.getString("descricao_processo").trim());
				retorno.setValorProc(rs.getDouble("valor_processo"));
			}	

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return retorno;
	}

}
