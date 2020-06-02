package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ProcessoDaoPedro;
import br.com.faculdadedelta.modelo.ProcessoPedro;

@ManagedBean
@SessionScoped
public class ProcessoControllerPedro {

	private ProcessoPedro processo = new ProcessoPedro();
	private ProcessoDaoPedro dao = new ProcessoDaoPedro();

	public ProcessoPedro getProcesso() {
		return processo;
	}

	public void setProcesso(ProcessoPedro processo) {
		this.processo = processo;
	}
	
	public void limparCampos() {
		processo = new ProcessoPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
	
		if(processo.getId()==null) {
				dao.incluir(processo);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				dao.alterar(processo);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			}catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a opera��o, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroProcesso.xhtml";
		
	}
	
	public String editar() {
		return "CadastroProcesso.xhtml";
		
	}
	
	public String excluir() {
		
		try {
			dao.excluir(processo);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opera��o, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "ListaProcesso.xhtml";
		
	}
	
	public List<ProcessoPedro> getLista(){
		List<ProcessoPedro> listaRetorno = new ArrayList<ProcessoPedro>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opera��o, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
		
	}
	
	
}
