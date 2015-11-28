package br.univel.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.univel.ecommerce.PedidoLivros;

/**
 *  DAO for PedidoLivros
 */
@Stateless
public class PedidoLivrosDao
{
   @PersistenceContext(unitName = "ecommerce-persistence-unit")
   private EntityManager em;

   public void create(PedidoLivros entity)
   {
      em.persist(entity);
   }

   public void deleteById(Long id)
   {
      PedidoLivros entity = em.find(PedidoLivros.class, id);
      if (entity != null)
      {
         em.remove(entity);
      }
   }

   public PedidoLivros findById(Long id)
   {
      return em.find(PedidoLivros.class, id);
   }

   public PedidoLivros update(PedidoLivros entity)
   {
      return em.merge(entity);
   }

   public List<PedidoLivros> listAll(Integer startPosition, Integer maxResult)
   {
      TypedQuery<PedidoLivros> findAllQuery = em.createQuery("SELECT DISTINCT p FROM PedidoLivros p ORDER BY p.id", PedidoLivros.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      return findAllQuery.getResultList();
   }
}
