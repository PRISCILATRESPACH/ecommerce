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

import br.univel.ecommerce.Usuario;


public class TesteUsuario {
	
	private Client createClient() {
		return ClientBuilder.newBuilder()
				.register(JacksonJaxbJsonProvider.class).build();
	}

	@Test
	public void testeGravacaoLeituraCliente() {

		Usuario usuario = new Usuario();
		usuario.setNome("Priscila");
		usuario.setTelefone("99457829");
		usuario.setEmail("priscila@gmail.com");
		usuario.setEndereco("Rua Centro, 7845");

		String urlClienteCriado;
		{
			Entity<Usuario> clienteJson = Entity.json(usuario);

			Client webClientGravacao = createClient();

			WebTarget destinoGravacao = webClientGravacao
					.target("http://localhost:8080/ecommerce/rest/usuarios");

			Response respostaGravacao = destinoGravacao
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).post(clienteJson);

			assertEquals(Status.CREATED.getStatusCode(),
					respostaGravacao.getStatus());

			urlClienteCriado = respostaGravacao.getHeaderString("Location");
		}

		Usuario clienteGravado;
		{
			Client webClientLeitura = createClient();

			WebTarget destinoLeitura = webClientLeitura
					.target(urlClienteCriado);

			Response respostaLeitura = destinoLeitura
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).get();

			clienteGravado = respostaLeitura.readEntity(Usuario.class);

			respostaLeitura.getHeaders().entrySet().stream().forEach(t -> {
				System.out.println(t.getKey());
				t.getValue().forEach(e -> System.out.println("\t" + e));
			});
		}

		assertNotNull(clienteGravado.getId());
		assertNotNull(clienteGravado.getVersion());

		assertEquals(usuario.getNome(), clienteGravado.getNome());
		assertEquals(usuario.getTelefone(), clienteGravado.getTelefone());

	}
}



