package br.univel.rest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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

	HashMap<Long, Integer> map = new HashMap<Long, Integer>();

	@Inject
	private Carrinho carrinho;

	@Inject
	private LivroDao pe;

	@Inject
	PedidosDao pdao;

	@Inject
	UsuarioDao udao;

	@Path("/adicionar/{id:[0-9][0-9]*}/{qtd:[0-9][0-9]*}")
	public void adicionarProduto(@PathParam("id") long id,
			@PathParam("qtd") int qtd) {
		Livro l = pe.findById(id);
		boolean flag = false;
		map.put(id, qtd);

		if (!carrinho.getLivros().isEmpty())
			for (Livro prod : carrinho.getLivros()) {
				if (prod.getId() == id) {
					map.replace(prod.getId(), qtd);
					flag = false;
				} else {

					map.put(l.getId(), qtd);
					flag = true;
				}
			}
		else
			carrinho.addLivro(l);
		if (flag)
			carrinho.getLivros().remove(l);
	}

	@Path("/limparCarrinho/")
	public void limpar() {
		carrinho.limpar();
		map.clear();
	}

	@Path("/finalizar/{idusuario:[0-9][0-9]*}")
	public void finalizar(@PathParam("idusuario") long Idusuario) {
		Pedidos pedido = new Pedidos();
		Set<PedidoLivros> lista = new HashSet<>();
		double total = 0;

		for (Livro p : carrinho.getLivros()) {
			PedidoLivros pl = new PedidoLivros();
			pl.setNomeproduto(p.getTitulo());
			pl.setPreco(p.getPreco());
			pl.setQuantidade(map.get(p.getId()));
			lista.add(pl);
			total += p.getPreco() * pl.getQuantidade();
		}

		pedido.setPedidoslivros(lista);
		pedido.setTotalVenda(total);
		pedido.setUsuario(udao.findById(Idusuario));
		pdao.create(pedido);
	}

}