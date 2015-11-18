package br.univel.util;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.univel.ecommerce.Carrinho;
import br.univel.ecommerce.Livro;
import br.univel.ecommerce.Venda;
import br.univel.rest.LivroEndpoint;
import br.univel.rest.VendaEndpoint;

public class CarrinhoController  {
	
	@Inject
	private Carrinho carrinho;
	
	@Inject
	private LivroEndpoint le;
	
	@Inject
	VendaEndpoint vendaEp;
	
	@Path("/adicionar/{id:[0-9][0-9][0-9]*}")
	public void adicionarLivro(@PathParam("id") long id){
    	Livro l = le.findById(id).readEntity(Livro.class);
    			
}


public void limpar(){
	carrinho.limpar();
}
public void finalizar(){
	Venda venda = new Venda();
	
	vendaEp.create(venda);
}
}