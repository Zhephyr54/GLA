/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Bidding;
import entity.Item;
import java.math.BigDecimal;
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
     * Return searched items corresponding to this title
     * for which the biddings aren't over.
     * 
     * @param title the title
     * @return List of items
     */
    public List<Item> findByTitle(String title) {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.findByTitle", Item.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("currentDate", LocalDateTime.now());
        return query.getResultList();
    }
    
    /**
     * Return searched items corresponding to this subcategory and this title
     * for which the biddings aren't over.
     * 
     * @param title the title
     * @param subcategoryId the subcategory id
     * @return List of items
     */
    public List<Item> findByTitleAndSubcategory(String title, Long subcategoryId) {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.findByTitleAndSubcategory", Item.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("subcategoryId", subcategoryId);
        query.setParameter("currentDate", LocalDateTime.now());
        return query.getResultList();
    }
    
    /**
     * Return searched items corresponding to this category and this title
     * for which the biddings aren't over.
     * 
     * @param title the title
     * @param categoryId the category id
     * @return List of items
     */
    public List<Item> findByTitleAndCategory(String title, Long categoryId) {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.findByTitleAndCategory", Item.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("categoryId", categoryId);
        query.setParameter("currentDate", LocalDateTime.now());
        return query.getResultList();
    }
    
    
    
    /**
     * Return the max bid value if this item has any biddings
     * or the starting bid value otherwise.
     * 
     * @param itemId the item id
     * @return the current price for this item.
     */
    public BigDecimal getCurrentPrice(Long itemId) {
        Bidding currentMaxBid = getCurrentMaxBid(itemId);
        if (currentMaxBid != null) {
            return currentMaxBid.getPrice();
        }
        return findById(itemId).getStartingBid();
    }
    
    /**
     * Return the current max bidding for this item.
     * 
     * @param itemId the item id
     * @return Bidding
     */
    public Bidding getCurrentMaxBid(Long itemId) {
        TypedQuery<Bidding> query = getEntityManager().createNamedQuery("Item.getCurrentMaxBid", Bidding.class);
        query.setParameter("itemId", itemId);
        List<Bidding> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    /**
     * Return the number of biggings for this item using
     * a count query.
     * 
     * @param itemId the item id
     * @return The number of biddings for this item
     */
    public long getNumberOfBiddingsById(Long itemId) {
        TypedQuery<Long> query = getEntityManager().createNamedQuery("Item.getNumberOfBiddings", Long.class);
        query.setParameter("itemId", itemId);
        return query.getSingleResult();
    }
    
    public List<Item> getUserItems(Long userId) {
        TypedQuery<Item> query = getEntityManager().createNamedQuery("Item.getUserItemsInProgress", Item.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }   
}
