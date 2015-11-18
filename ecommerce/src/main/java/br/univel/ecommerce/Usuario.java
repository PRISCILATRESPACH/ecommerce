package br.univel.ecommerce;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import br.univel.ecommerce.Carrinho;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Usuario implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String nome;

   @Column
   private String endereco;

   @Column
   private String bairro;

   @Column
   private String CEP;

   @Column
   private String cidade;

   @Column
   private String estado;

   @Column
   private String senha;

   @Column
   private String confirmarsenha;

   @Column
   private String login;

   @Column
   private String telefone;

   @Column
   private String email;

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
      if (!(obj instanceof Usuario))
      {
         return false;
      }
      Usuario other = (Usuario) obj;
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

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getEndereco()
   {
      return endereco;
   }

   public void setEndereco(String endereco)
   {
      this.endereco = endereco;
   }

   public String getBairro()
   {
      return bairro;
   }

   public void setBairro(String bairro)
   {
      this.bairro = bairro;
   }

   public String getCEP()
   {
      return CEP;
   }

   public void setCEP(String CEP)
   {
      this.CEP = CEP;
   }

   public String getCidade()
   {
      return cidade;
   }

   public void setCidade(String cidade)
   {
      this.cidade = cidade;
   }

   public String getEstado()
   {
      return estado;
   }

   public void setEstado(String estado)
   {
      this.estado = estado;
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }

   public String getConfirmarsenha()
   {
      return confirmarsenha;
   }

   public void setConfirmarsenha(String confirmarsenha)
   {
      this.confirmarsenha = confirmarsenha;
   }

   public String getLogin()
   {
      return login;
   }

   public void setLogin(String login)
   {
      this.login = login;
   }

   public String getTelefone()
   {
      return telefone;
   }

   public void setTelefone(String telefone)
   {
      this.telefone = telefone;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nome != null && !nome.trim().isEmpty())
         result += "nome: " + nome;
      if (endereco != null && !endereco.trim().isEmpty())
         result += ", endereco: " + endereco;
      if (bairro != null && !bairro.trim().isEmpty())
         result += ", bairro: " + bairro;
      if (CEP != null && !CEP.trim().isEmpty())
         result += ", CEP: " + CEP;
      if (cidade != null && !cidade.trim().isEmpty())
         result += ", cidade: " + cidade;
      if (estado != null && !estado.trim().isEmpty())
         result += ", estado: " + estado;
      if (senha != null && !senha.trim().isEmpty())
         result += ", senha: " + senha;
      if (confirmarsenha != null && !confirmarsenha.trim().isEmpty())
         result += ", confirmarsenha: " + confirmarsenha;
      if (login != null && !login.trim().isEmpty())
         result += ", login: " + login;
      if (telefone != null && !telefone.trim().isEmpty())
         result += ", telefone: " + telefone;
      if (email != null && !email.trim().isEmpty())
         result += ", email: " + email;
      return result;
   }
}