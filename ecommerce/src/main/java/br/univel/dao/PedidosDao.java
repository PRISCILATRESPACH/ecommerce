package br.univel.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.univel.ecommerce.Pedidos;

/**
 *  DAO for Pedidos
 */
@Stateless
public class PedidosDao
{
   @PersistenceContext(unitName = "ecommerce-persistence-unit")
   private EntityManager em;

   public void create(Pedidos entity)
   {
      em.persist(entity);
   }

   public void deleteById(Long id)
   {
      Pedidos entity = em.find(Pedidos.class, id);
      if (entity != null)
      {
         em.remove(entity);
      }
   }

   public Pedidos findById(Long id)
   {
      return em.find(Pedidos.class, id);
   }

   public Pedidos update(Pedidos entity)
   {
      return em.merge(entity);
   }

   public List<Pedidos> listAll(Integer startPosition, Integer maxResult)
   {
      TypedQuery<Pedidos> findAllQuery = em.createQuery("SELECT DISTINCT p FROM Pedidos p LEFT JOIN FETCH p.pedidoslivros LEFT JOIN FETCH p.usuario ORDER BY p.id", Pedidos.class);
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
