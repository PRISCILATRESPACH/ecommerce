package br.univel.ecommerce;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import java.lang.Override;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import br.univel.ecommerce.PedidoLivros;

import javax.xml.bind.annotation.XmlRootElement;

import br.univel.ecommerce.Usuario;

import javax.persistence.FetchType;

@Entity
@XmlRootElement
public class Pedidos implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @ManyToMany
   private Set<PedidoLivros> pedidoslivros = new HashSet<PedidoLivros>();

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Usuario usuario;

   @Column
   private double totalVenda;

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
      if (!(obj instanceof Pedidos))
      {
         return false;
      }
      Pedidos other = (Pedidos) obj;
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

   public Set<PedidoLivros> getPedidoslivros()
   {
      return this.pedidoslivros;
   }

   public void setPedidoslivros(final Set<PedidoLivros> pedidoslivros)
   {
      this.pedidoslivros = pedidoslivros;
   }

   public Usuario getUsuario()
   {
      return this.usuario;
   }

   public void setUsuario(final Usuario usuario)
   {
      this.usuario = usuario;
   }

   public double getTotalVenda()
   {
      return totalVenda;
   }

   public void setTotalVenda(double totalVenda)
   {
      this.totalVenda = totalVenda;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      result += "totalVenda: " + totalVenda;
      return result;
   }

}