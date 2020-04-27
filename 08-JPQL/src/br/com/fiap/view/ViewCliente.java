package br.com.fiap.view;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.CpfNotFoundException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ViewCliente {

	public static void main(String[] args) {
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();

		ClienteDAO dao = new ClienteDAOImpl(em);

		System.out.println("LISTAR CLIENTE:");
		List<Cliente> lista = dao.listar();

		for (Cliente cliente : lista) {
			System.out.println(cliente.getNome());
		}

		System.out.println("BUSCAR POR CPF:");
		Cliente cliente;
		try {
			cliente = dao.buscarPorCpf("98728016");
			System.out.println(cliente.getCpf() + " " + cliente.getNome());
		} catch (CpfNotFoundException e) {
			System.out.println("Cliente não encontrado");
		}

		System.out.println("BUSCAR POR MÊS NASCIMENTO:");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		lista = dao.buscarPorMesNascimento(8);
		for (Cliente item : lista) {
			System.out.println(item.getNome() + " " + sdf.format(item.getDataNascimento().getTime()));
		}

		System.out.println("BUSCAR POR PARTE DO NOME:");
		lista = dao.buscarPorNome("a");
		for (Cliente item : lista) {
			System.out.println(item.getNome());
		}

		System.out.println("BUSCAR POR ESTADO:");
		lista = dao.buscarPorEstado("PR");
		lista.forEach(item -> System.out.println(item.getNome() + " " + item.getEndereco().getCidade().getUf()));

		System.out.println("BUSCAR POR DIAS DE RESERVA:");
		dao.buscarPorDiasReserva(10).forEach(item -> {
			System.out.println(item.getNome());
		});
		
		System.out.println("BUSCAR POR PARTE DO NOME:");
		//Nome, Máximo de resultados, Posição do primeiro retorno
		dao.buscarPorNome("a", 2, 0).forEach(item ->
			System.out.println(item.getNome()));

		em.close();
		fabrica.close();
	}

}
