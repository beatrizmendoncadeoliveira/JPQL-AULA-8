package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.CpfNotFoundException;

public interface ClienteDAO extends GenericDAO<Cliente,Integer>{

	//List<Cliente> listar();
	
	Cliente buscarPorCpf(String cpf) throws CpfNotFoundException;
	
	List<Cliente> buscarPorMesNascimento(int mes);
	
	List<Cliente> buscarPorNome(String nome);
	
	List<Cliente> buscarPorNome(String nome, int maximo, int primeiraPosicao);
	
	List<Cliente> buscarPorEstado(String estado);
	
	List<Cliente> buscarPorDiasReserva(int dias);
	
}