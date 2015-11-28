package br.univel.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.univel.ecommerce.Livro;

/**
 *  DAO for Livro
 */
@Stateless
public class LivroDao
{
   @PersistenceContext(unitName = "ecommerce-persistence-unit")
   private EntityManager em;

   public void create(Livro entity)
   {
      em.persist(entity);
   }

   public void deleteById(Long id)
   {
      Livro entity = em.find(Livro.class, id);
      if (entity != null)
      {
         em.remove(entity);
      }
   }

   public Livro findById(Long id)
   {
      return em.find(Livro.class, id);
   }

   public Livro update(Livro entity)
   {
      return em.merge(entity);
   }

   public List<Livro> listAll(Integer startPosition, Integer maxResult)
   {
      TypedQuery<Livro> findAllQuery = em.createQuery("SELECT DISTINCT l FROM Livro l LEFT JOIN FETCH l.Categoria ORDER BY l.id", Livro.class);
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
