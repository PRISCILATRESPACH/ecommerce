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
	public void testeGravacaoLeituraCliente() {
		
		 Set<PedidoLivros> pedidoslivros = new HashSet<PedidoLivros>();
		 
		for(Livro li :carrinho.getLivros()){
			PedidoLivros t = new PedidoLivros();
			t.setNomeproduto(li.getTitulo());
			t.setPreco(li.getPreco());
			t.setQuantidade(Integer.parseInt(li.getQuantidade()));
			pedidoslivros.add(t);
		}
		
		

		String urlClienteCriado;
		{
			Entity<Pedidos> clienteJson = Entity.json(pedidos);

			Client webClientGravacao = createClient();

			WebTarget destinoGravacao = webClientGravacao
					.target("http://localhost:8080/ecommerce/rest/livros");

			Response respostaGravacao = destinoGravacao
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).post(clienteJson);

			assertEquals(Status.CREATED.getStatusCode(),
					respostaGravacao.getStatus());

			urlClienteCriado = respostaGravacao.getHeaderString("Location");
		}

		Carrinho livroGravado;
		{
			Client webClientLeitura = createClient();

			WebTarget destinoLeitura = webClientLeitura
					.target(urlClienteCriado);

			Response respostaLeitura = destinoLeitura
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).get();

			livroGravado = respostaLeitura.readEntity(Carrinho.class);

			respostaLeitura.getHeaders().entrySet().stream().forEach(t -> {
				System.out.println(t.getKey());
				t.getValue().forEach(e -> System.out.println("\t" + e));
			});
		}

		assertNotNull(livroGravado.getId());
		assertNotNull(livroGravado.getVersion());

		assertEquals(pedidos.getTitulo(), livroGravado.getTitulo());
		assertEquals(pedidos.getCategoria(), livroGravado.getCategoria());
		assertEquals(pedidos.getAutor(), livroGravado.getAutor());
		assertEquals(pedidos.getEditora(), livroGravado.getEditora());
		assertEquals(pedidos.getISBN(), livroGravado.getISBN());
		assertEquals(pedidos.getImagem(), livroGravado.getImagem());
		assertEquals(pedidos.getPaginas(), livroGravado.getPaginas());
		assertEquals(pedidos.getPreco(), livroGravado.getPreco());
		assertEquals(pedidos.getQuantidade(), livroGravado.getQuantidade());
		
		
		
		
		
		

	}
}



