/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Item;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author alexis
 */
@Stateless
@LocalBean
public class ItemDAO extends AbstractDAO<Item> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public ItemDAO() {
        super(Item.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

    public List<Item> findAll() {
        return executeNamedQuery("Item.findAll");
    }
    
    /**
     * Return all the items for which the biddings aren't over.
     * 
     * @return List of items
     */
    public List<Item> findAllNotOver() {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.findAllNotOver", Item.class);
        query.setParameter("currentDate", LocalDateTime.now());
        return query.getResultList();
    }
    
    
    /**
     * Return searched items for which the biddings aren't over.
     * 
     * @param title
     * @param idCategory
     * @param idSubCategory
     * @return List of items
     */
    public List<Item> findNotOver(String title, int idCategory, int idSubCategory) {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.searchNotOver", Item.class);
        query.setParameter("title", "%"+title+"%");
        query.setParameter("cat", idCategory);
        query.setParameter("sub", idSubCategory);
        return query.getResultList();
    }
    
    public List<Item> findNotOverByCategory(String title, int idCategory) {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.searchByCategory", Item.class);
        query.setParameter("title", "%"+title+"%");
        query.setParameter("cat", idCategory);
        return query.getResultList();
    }
    
    
    
    public List<Item> findNotOverByTitle(String title) {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.searchByTitle", Item.class);
        query.setParameter("title", title);
        return query.getResultList();
    }
    
    
    /**
     * Return the number of biggings for this item using
     * a count query.
     * 
     * @param itemId the item id
     * @return The number of biddings for this item
     */
    public Long getNumberOfBiddingsById(Long itemId) {
        TypedQuery<Long> query = getEntityManager().createNamedQuery("Item.getNumberOfBiddings", Long.class);
        query.setParameter("itemId", itemId);
        return query.getSingleResult();
    }
    
}
