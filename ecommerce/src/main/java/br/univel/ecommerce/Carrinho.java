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
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@ApplicationScoped
public class Carrinho implements Serializable
{

	HashMap<Livro, Integer> livros = new HashMap<Livro, Integer>();

	public void limpar() {
		livros.clear();
	}

	public List<Livro> getLivros() {
		List<Livro> ls = new ArrayList<Livro>();
		while(livros.keySet().iterator().hasNext()){
			Livro l = livros.keySet().iterator().next();
			ls.add(l);
		}
		return ls;
	}

	public void addLivro(Livro l, int quantidade) {
		if(livros.containsKey(l))
			livros.replace(l, quantidade);
		else
			livros.put(l, quantidade);
	}
	
	public HashMap<Livro,Integer> getMap(){
		return livros;
	}
	
	public double getTotal(){
		double total = 0;
		while(livros.keySet().iterator().hasNext()){
			Livro l = livros.keySet().iterator().next();
			total += l.getPreco() * livros.get(l);
		}
		return total;
	}
}