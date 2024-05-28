package com.wbarcellosn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.wbarcellosn.entities.Produto;

public class ProdutoDAO implements IProdutoDAO{
	private static EntityManagerFactory emf;
	
	public ProdutoDAO() {
		emf = Persistence.createEntityManagerFactory("ExercicioJPA");
	}

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


	@Override
	public Produto cadastrar(Produto produto) {
		EntityManager em = getEntityManager();
		
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();

        em.close();
        
        return produto;
	}
	

	@Override
	public Produto buscar(Long id) {
		EntityManager em = getEntityManager();
        
		Produto produto = em.find(Produto.class, id);
		return produto;
	}
	
	
	@Override
	public List<Produto> buscarTodos() {
		EntityManager em = getEntityManager();
        
		List<Produto> produtos = em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
		em.close();

		return produtos;	
	}
	

	@Override
	public void excluir(Long id) {
		EntityManager em = getEntityManager();
		
		Produto produto = em.find(Produto.class, id);
		em.getTransaction().begin();
        em.remove(produto);
        em.getTransaction().commit();

        em.close();
	}

	@Override
	public void atualizar(Produto produto) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();

        em.close();
	}
	
	public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
