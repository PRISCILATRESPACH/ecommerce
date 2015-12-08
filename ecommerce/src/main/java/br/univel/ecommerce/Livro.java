package br.univel.ecommerce;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import br.univel.ecommerce.Categoria;

import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Livro implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @ManyToOne
   private Categoria categoria;

   @Column
   private String titulo;

   @Column
   private int paginas;

   @Column
   private String ISBN;

   @Column
   private String autor;

   @Column
   private String editora;

   @Column
   private String quantidade;

   @Column
   private double preco;

   @Column
   private String imagem;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Livro))
      {
         return false;
      }
      Livro other = (Livro) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public Categoria getCategoria()
   {
      return this.categoria;
   }

   public void setCategoria(final Categoria CadCategoria)
   {
      this.categoria = CadCategoria;
   }

   public String getTitulo()
   {
      return titulo;
   }

   public void setTitulo(String titulo)
   {
      this.titulo = titulo;
   }

   public int getPaginas()
   {
      return paginas;
   }

   public void setPaginas(int paginas)
   {
      this.paginas = paginas;
   }

   public String getISBN()
   {
      return ISBN;
   }

   public void setISBN(String ISBN)
   {
      this.ISBN = ISBN;
   }

   public String getAutor()
   {
      return autor;
   }

   public void setAutor(String autor)
   {
      this.autor = autor;
   }

   public String getEditora()
   {
      return editora;
   }

   public void setEditora(String editora)
   {
      this.editora = editora;
   }

   public String getQuantidade()
   {
      return quantidade;
   }

   public void setQuantidade(String quantidade)
   {
      this.quantidade = quantidade;
   }

   public double getPreco()
   {
      return preco;
   }

   public void setPreco(double preco)
   {
      this.preco = preco;
   }

   public String getImagem()
   {
      return imagem;
   }

   public void setImagem(String imagem)
   {
      this.imagem = imagem;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (titulo != null && !titulo.trim().isEmpty())
         result += "titulo: " + titulo;
      result += ", paginas: " + paginas;
      if (ISBN != null && !ISBN.trim().isEmpty())
         result += ", ISBN: " + ISBN;
      if (autor != null && !autor.trim().isEmpty())
         result += ", autor: " + autor;
      if (editora != null && !editora.trim().isEmpty())
         result += ", editora: " + editora;
      if (quantidade != null && !quantidade.trim().isEmpty())
         result += ", quantidade: " + quantidade;
      result += ", preco: " + preco;
      if (imagem != null && !imagem.trim().isEmpty())
         result += ", imagem: " + imagem;
      return result;
   }


}