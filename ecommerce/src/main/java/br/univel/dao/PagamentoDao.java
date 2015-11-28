package br.univel.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.univel.ecommerce.Pagamento;

/**
 *  DAO for Pagamento
 */
@Stateless
public class PagamentoDao
{
   @PersistenceContext(unitName = "ecommerce-persistence-unit")
   private EntityManager em;

   public void create(Pagamento entity)
   {
      em.persist(entity);
   }

   public void deleteById(Long id)
   {
      Pagamento entity = em.find(Pagamento.class, id);
      if (entity != null)
      {
         em.remove(entity);
      }
   }

   public Pagamento findById(Long id)
   {
      return em.find(Pagamento.class, id);
   }

   public Pagamento update(Pagamento entity)
   {
      return em.merge(entity);
   }

   public List<Pagamento> listAll(Integer startPosition, Integer maxResult)
   {
      TypedQuery<Pagamento> findAllQuery = em.createQuery("SELECT DISTINCT p FROM Pagamento p ORDER BY p.id", Pagamento.class);
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
