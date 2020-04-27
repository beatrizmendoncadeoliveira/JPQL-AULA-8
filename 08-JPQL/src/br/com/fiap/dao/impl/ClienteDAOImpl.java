package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.CpfNotFoundException;

public class ClienteDAOImpl extends GenericDAOImpl<Cliente, Integer> implements ClienteDAO {

	public ClienteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Cliente buscarPorCpf(String cpf) throws CpfNotFoundException {
		// Criar a TypedQuery
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.cpf = :churros", Cliente.class);
		// Setar o parâmetro na query
		query.setParameter("churros", cpf);
		try {
			// Executar a query
			return query.getSingleResult();
		} catch (Exception e) {
			// Lança a exception caso não encontre o cliente com o CPF
			throw new CpfNotFoundException();
		}
	}

	@Override
	public List<Cliente> buscarPorMesNascimento(int mes) {
		// month -> função que retorna o mês da data
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where month(c.dataNascimento) = :abobora",
				Cliente.class);
		query.setParameter("abobora", mes);
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorNome(String nome) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :pNome", Cliente.class);
		query.setParameter("pNome", "%" + nome + "%");
		// Configurar a quantidade máxima de resultados
		query.setMaxResults(50);
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorEstado(String estado) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.endereco.cidade.uf = :ufParametro",
				Cliente.class);
		query.setParameter("ufParametro", estado);
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorDiasReserva(int dias) {
		TypedQuery<Cliente> query = em.createQuery("select r.cliente from Reserva r where r.numeroDias = :pDias",
				Cliente.class);
		query.setParameter("pDias", dias);
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorNome(String nome, int maximo, int primeiraPosicao) {
//		TypedQuery<Cliente> query = 
//			em.createQuery("from Cliente c where c.nome like :n", Cliente.class);
//		query.setParameter("n", "%"+nome+"%");
//		query.setMaxResults(maximo);
//		query.setFirstResult(primeiraPosicao);
//		return query.getResultList();

		return em.createQuery("from Cliente c where c.nome like :n", Cliente.class)
				.setParameter("n", "%" + nome + "%")
				.setMaxResults(maximo)
				.setFirstResult(primeiraPosicao)
				.getResultList();
	}

	/*
	 * @Override public List<Cliente> listar() { TypedQuery<Cliente> query =
	 * em.createQuery("from Cliente", Cliente.class); return query.getResultList();
	 * }
	 */

}
