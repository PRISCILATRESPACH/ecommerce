package br.univel.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({TesteUsuario.class, TesteCategoria.class, TesteLivro.class, TesteCarrinho.class})
public class SuiteTeste {

}
