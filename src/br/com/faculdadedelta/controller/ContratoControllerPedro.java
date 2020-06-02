package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ContratoDaoPedro;
import br.com.faculdadedelta.modelo.ContratoPedro;
import br.com.faculdadedelta.modelo.ProcessoPedro;

@ManagedBean
@SessionScoped
public class ContratoControllerPedro {
	
	private ContratoPedro contrato = new ContratoPedro();
	private ContratoDaoPedro dao = new ContratoDaoPedro();
	private ProcessoPedro processoSelecionado = new ProcessoPedro();

	public ContratoPedro getContrato() {
		return contrato;
	}

	public void setContrato(ContratoPedro contrato) {
		this.contrato = contrato;
	}

	public ProcessoPedro getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(ProcessoPedro processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public void limparCampos() {
		contrato = new ContratoPedro();
		processoSelecionado= new  ProcessoPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if(contrato.getId() == null) {
				contrato.setProcesso(processoSelecionado);
				dao.incluir(contrato);
				limparCampos();
				exibirMensagem("Inclusao realizada com sucesso.");
			} else {
				contrato.setProcesso(processoSelecionado);
				dao.alterar(contrato);
				limparCampos();
				exibirMensagem("Alteracao realizada com sucesso.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		
		return "CadastroContrato.xhtml";
		
	}
	
	public String editar() {
		contrato.setProcesso(processoSelecionado);
		return "CadastroContrato.xhtml";
	}
	
	public String excluir() {
		
		try {
			contrato.setProcesso(processoSelecionado);
			dao.excluir(contrato);
			limparCampos();
			exibirMensagem("Exclusao realizada com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return "ListaContrato.xhtml";
	}
	
	public List<ContratoPedro> getLista(){
		List<ContratoPedro>listaRetorno = new ArrayList<ContratoPedro>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
		
	}
	
	

}
