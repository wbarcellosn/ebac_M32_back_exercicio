package com.wbarcellosn.dao;

import java.util.List;

import com.wbarcellosn.entities.Produto;

public interface IProdutoDAO {
	
	Produto cadastrar(Produto produto);
	
	Produto buscar(Long id);
	
	List<Produto> buscarTodos();
	
	void excluir(Long id);
	
	void atualizar(Produto produto);
	
	void close();

}
