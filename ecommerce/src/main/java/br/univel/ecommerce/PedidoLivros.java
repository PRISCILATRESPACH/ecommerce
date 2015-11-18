package br.univel.ecommerce;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import br.univel.ecommerce.Pedidos;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class PedidoLivros implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String nomeproduto;

   @Column
   private String preco;

   @Column
   private String quantidade;

   @ManyToOne
   private Pedidos pedidoslivros;

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
      if (!(obj instanceof PedidoLivros))
      {
         return false;
      }
      PedidoLivros other = (PedidoLivros) obj;
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

   public String getNomeproduto()
   {
      return nomeproduto;
   }

   public void setNomeproduto(String nomeproduto)
   {
      this.nomeproduto = nomeproduto;
   }

   public String getPreco()
   {
      return preco;
   }

   public void setPreco(String preco)
   {
      this.preco = preco;
   }

   public String getQuantidade()
   {
      return quantidade;
   }

   public void setQuantidade(String quatidade)
   {
      this.quantidade = quatidade;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nomeproduto != null && !nomeproduto.trim().isEmpty())
         result += "nomeproduto: " + nomeproduto;
      if (preco != null && !preco.trim().isEmpty())
         result += ", preco: " + preco;
      if (quantidade != null && !quantidade.trim().isEmpty())
         result += ", quatidade: " + quantidade;
      return result;
   }

   public Pedidos getPedidoslivros()
   {
      return this.pedidoslivros;
   }

   public void setPedidoslivros(final Pedidos pedidoslivros)
   {
      this.pedidoslivros = pedidoslivros;
   }
}