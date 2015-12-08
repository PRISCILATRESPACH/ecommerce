package br.univel.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.univel.dao.LivroDao;
import br.univel.dao.PedidosDao;
import br.univel.dao.UsuarioDao;
import br.univel.ecommerce.Carrinho;
import br.univel.ecommerce.Livro;
import br.univel.ecommerce.PedidoLivros;
import br.univel.ecommerce.Pedidos;

@Stateful
@Path("/cart")
public class CarrinhoEndpoint implements Serializable {

	@Inject
	private Carrinho carrinho;

	@Inject
	private LivroDao pe;

	@Inject
	PedidosDao pdao;

	@Inject
	UsuarioDao udao;

	@GET
	@Path("/adicionar/{id:[0-9][0-9]*}/{qtd:[0-9][0-9]*}")
	public synchronized Response adicionarProduto(@PathParam("id") long id,
			@PathParam("qtd") int qtd) {
		Livro l = pe.findById(id);
		carrinho.addLivro(l, qtd);

		return Response.ok().build();
	}
	@GET
	@Path("/limparCarrinho/")
	public synchronized Response limpar() {
		carrinho.limpar();

		return Response.ok().build();
	}
	@GET
	@Path("/finalizar/{idusuario:[0-9][0-9]*}")
	public synchronized Response finalizar(
			@PathParam("idusuario") long Idusuario) {
		Pedidos pedido = new Pedidos();
		Set<PedidoLivros> lista = new HashSet<>();
		double total = 0;

		for (Livro p : carrinho.getLivros()) {
			PedidoLivros pl = new PedidoLivros();
			pl.setNomeproduto(p.getTitulo());
			pl.setPreco(p.getPreco());
			pl.setQuantidade(carrinho.getMap().get(p));
			lista.add(pl);
			total += p.getPreco() * pl.getQuantidade();
		}

		pedido.setPedidoslivros(lista);
		pedido.setTotalVenda(total);
		pedido.setUsuario(udao.findById(Idusuario));
		pdao.create(pedido);
		return Response.ok().build();
	}
}