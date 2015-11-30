package br.univel.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import br.univel.ecommerce.Livro;



public class TesteLivro {
	
@Inject
CategoriaDao cdao;
	
	private Client createClient() {
		return ClientBuilder.newBuilder()
				.register(JacksonJaxbJsonProvider.class).build();
	}

	@Test
	public void testeGravacaoLeituraCliente() {

		Livro livro = new Livro();
		livro.setTitulo("Jogos Vorazes");
		livro.setCadCategoria(cdao.findById(new Long (1)));
		livro.setAutor("Suzanne Collins");
		livro.setEditora("Rocco");
		livro.setISBN("978-85-7980-024-5");
		livro.setImagem("D:\\livros\\jogos vorazes.png");
		livro.setPaginas(400);
		livro.setQuantidade("1");
		livro.setPreco(30.00);
		

		String urlClienteCriado;
		{
			Entity<Livro> clienteJson = Entity.json(livro);

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

		Livro livroGravado;
		{
			Client webClientLeitura = createClient();

			WebTarget destinoLeitura = webClientLeitura
					.target(urlClienteCriado);

			Response respostaLeitura = destinoLeitura
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).get();

			livroGravado = respostaLeitura.readEntity(Livro.class);

			respostaLeitura.getHeaders().entrySet().stream().forEach(t -> {
				System.out.println(t.getKey());
				t.getValue().forEach(e -> System.out.println("\t" + e));
			});
		}

		assertNotNull(livroGravado.getId());
		assertNotNull(livroGravado.getVersion());

		assertEquals(livro.getTitulo(), livroGravado.getTitulo());
		assertEquals(livro.getCategoria(), livroGravado.getCategoria());
		assertEquals(livro.getAutor(), livroGravado.getAutor());
		assertEquals(livro.getEditora(), livroGravado.getEditora());
		assertEquals(livro.getISBN(), livroGravado.getISBN());
		assertEquals(livro.getImagem(), livroGravado.getImagem());
		assertEquals(livro.getPaginas(), livroGravado.getPaginas());
		assertEquals(livro.getPreco(), livroGravado.getPreco());
		assertEquals(livro.getQuantidade(), livroGravado.getQuantidade());
		
		
		
		
		
		

	}
}



