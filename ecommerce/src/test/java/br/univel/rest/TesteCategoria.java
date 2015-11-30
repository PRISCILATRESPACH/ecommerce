package br.univel.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.Test;

import br.univel.ecommerce.Categoria;



public class TesteCategoria {
	
	private Client createClient() {
		return ClientBuilder.newBuilder()
				.register(JacksonJaxbJsonProvider.class).build();
	}

	@Test
	public void testeGravacaoLeituraCategoria() {

		Categoria categoria = new Categoria();
		categoria.setNome("Terror");
		
		String urlClienteCriado;
		{
			Entity<Categoria> clienteJson = Entity.json(categoria);

			Client webClientGravacao = createClient();

			WebTarget destinoGravacao = webClientGravacao
					.target("http://localhost:8080/ecommerce/rest/categoria");

			Response respostaGravacao = destinoGravacao
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).post(clienteJson);

			assertEquals(Status.CREATED.getStatusCode(),
					respostaGravacao.getStatus());

			urlClienteCriado = respostaGravacao.getHeaderString("Location");
		}

		Categoria clienteGravado;
		{
			Client webClientLeitura = createClient();

			WebTarget destinoLeitura = webClientLeitura
					.target(urlClienteCriado);

			Response respostaLeitura = destinoLeitura
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).get();

			clienteGravado = respostaLeitura.readEntity(Categoria.class);

			respostaLeitura.getHeaders().entrySet().stream().forEach(t -> {
				System.out.println(t.getKey());
				t.getValue().forEach(e -> System.out.println("\t" + e));
			});
		}

		assertNotNull(clienteGravado.getId());
		assertNotNull(clienteGravado.getVersion());

		assertEquals(categoria.getNome(), clienteGravado.getNome());
	

	}
}



