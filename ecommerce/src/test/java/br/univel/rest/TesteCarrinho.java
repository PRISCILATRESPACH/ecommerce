package br.univel.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.Test;

import br.univel.dao.CategoriaDao;
import br.univel.dao.UsuarioDao;
import br.univel.ecommerce.Carrinho;
import br.univel.ecommerce.Livro;
import br.univel.ecommerce.PedidoLivros;



public class TesteCarrinho {
	
	@Inject
	UsuarioDao udao;

	@Inject
	Carrinho carrinho;

	private Client createClient() {
		return ClientBuilder.newBuilder()
				.register(JacksonJaxbJsonProvider.class).build();
	}

	@Test
	public void testeGravacaoCarrinhoVenda() {
		Livro l1 = buscaLivro(3);
		Livro l2 = buscaLivro(6);

		assertEquals(addLivroQtd(l1.getId(), 2), true);
		assertEquals(addLivroQtd(l2.getId(), 1), true);
		assertEquals(addLivroQtd(l2.getId(), 5), true);

		assertEquals(finalizarCarrinho(1), true);

	}

	public Livro buscaLivro(long id) {
		Client webClientLeitura = createClient();

		WebTarget destinoLeitura = webClientLeitura
				.target("http://localhost:8080/ecommerce/rest/livros/" + id);

		Response respostaLeitura = destinoLeitura
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).get();

		Livro l = respostaLeitura.readEntity(Livro.class);
		return l;
	}

	private boolean addLivroQtd(Long idLivro, int qtd) {
		Client webClientLeitura = createClient();

		WebTarget destinoLeitura = webClientLeitura
				.target("http://localhost:8080/ecommerce/rest/cart/adicionar/"
						+ idLivro + "/" + qtd);

		Response respostaLeitura = destinoLeitura.request().get();
		assertEquals(Status.OK.getStatusCode(), respostaLeitura.getStatus());

		return Status.OK.getStatusCode() == respostaLeitura.getStatus();
	}

	private boolean finalizarCarrinho(int idCliente) {

		Client webClientLeitura = createClient();

		WebTarget destinoLeitura = webClientLeitura
				.target("http://localhost:8080/ecommerce/rest/cart/finalizar/"
						+ idCliente);

		Response respostaLeitura = destinoLeitura.request().get();
		assertEquals(Status.OK.getStatusCode(), respostaLeitura.getStatus());
		System.out.println(respostaLeitura.getStatus());

		return Status.OK.getStatusCode() == respostaLeitura.getStatus();
	}
}



