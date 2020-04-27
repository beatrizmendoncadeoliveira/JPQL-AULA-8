package br.com.fiap.view;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.dao.TransporteDAO;
import br.com.fiap.dao.impl.PacoteDAOImpl;
import br.com.fiap.dao.impl.TransporteDAOImpl;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ViewPacote {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		TransporteDAO transporteDao = new TransporteDAOImpl(em);
		PacoteDAO pacoteDao = new PacoteDAOImpl(em);
		
		//Pesquisando o transporte de código 1
		Transporte transporte = transporteDao.pesquisar(2);
		//Pesquisando os transportes que se relacionam com o transporte de código 1
		List<Pacote> pacotes = pacoteDao.buscarPorTransporte(transporte);
		
		//Exibir a descrição dos pacotes e o nome da empresa
		pacotes.forEach(item -> {
			System.out.println(item.getDescricao() + " " + item.getTransporte().getEmpresa());
		});
		
		em.close();
		fabrica.close();
	}
	
}
