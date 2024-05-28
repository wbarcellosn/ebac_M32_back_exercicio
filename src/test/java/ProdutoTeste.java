import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wbarcellosn.dao.IProdutoDAO;
import com.wbarcellosn.dao.ProdutoDAO;
import com.wbarcellosn.entities.Produto;

public class ProdutoTeste {
	
	IProdutoDAO produtoDAO;

	
	@Before
    public void inicializar() {
        produtoDAO = new ProdutoDAO();
    }
	
	@After
	public void excluirTodos() {
		// Buscando todos os produtos do banco
		List<Produto> produtos = produtoDAO.buscarTodos();
		
		// Excluindo cada produto localizado na iteração da lista criada acima
		produtos.forEach(prod -> produtoDAO.excluir(prod.getId()));
		
		// Fechando a conexão com o banco
		produtoDAO.close();
	}
	
	@Test
	public void testeCadastrar() {
		
		// Cadastrando o produto
		Produto produto = new Produto();
		produto.setNome("Produto 1");
		produto.setPreco(100.00d);
		produto = produtoDAO.cadastrar(produto);
		
		// Verificando se o produto foi cadastrado
		assertNotNull(produto);
		assertNotNull(produto.getId());
	}
	
	@Test
	public void testeAtualizar() {
		
		// Cadastrando o produto
        Produto produto = new Produto();
        produto.setNome("Produto 1");
        produto.setPreco(100.00d);
        produtoDAO.cadastrar(produto);

        // Buscando o produto no banco
        Produto produtoDoBanco = produtoDAO.buscar(produto.getId());
        
        // Atualizando o produto
        produtoDoBanco.setNome("Produto Alterado");
        produtoDAO.atualizar(produtoDoBanco);

        // Buscando novamente para verificar a atualização
        Produto produtoAtualizado = produtoDAO.buscar(produtoDoBanco.getId());
        
        // Verificando se o nome foi atualizado
        assertEquals("Produto Alterado", produtoAtualizado.getNome());
	}
	
	@Test
	public void testeBuscar() {
		
		// Cadastrando o produto
		Produto produto = new Produto();
		produto.setNome("Produto 1");
		produto.setPreco(100.00d);
		produtoDAO.cadastrar(produto);
		
		// Buscando o produto no banco
		Produto produtoDoBanco = produtoDAO.buscar(produto.getId());
		
		// Verificando se o produto foi localizado
		assertNotNull(produtoDoBanco);
		assertEquals("Produto 1", produtoDoBanco.getNome());
	}
	
	@Test
	public void testeBuscarTodos() {
		
		// Cadastrando os produtos
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		Produto produto3 = new Produto();
		
		produto1.setNome("Produto 1");
		produto1.setPreco(100.00d);
		
		produto2.setNome("Produto 2");
		produto2.setPreco(200.00d);
		
		produto3.setNome("Produto 3");
		produto3.setPreco(300.00d);
		
		produtoDAO.cadastrar(produto1);
		produtoDAO.cadastrar(produto2);
		produtoDAO.cadastrar(produto3);
		
		// Buscando todos os produtos do banco
		List<Produto> produtos = produtoDAO.buscarTodos();
		
		// Verificando se todos os produtos foram localizados
		assertNotNull(produtos);
		assertEquals(3, produtos.size());
	}

}
