package br.univel.ecommerce;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@ApplicationScoped
public class Carrinho implements Serializable
{

   
   List<Livro> livros = new ArrayList<Livro>();

   


public void limpar() {
	livros.clear();
	
}

public List<Livro> getLivros() {
	
	return livros;
}

public void addLivro(Livro l) {
	livros.add(l);
	
}
}