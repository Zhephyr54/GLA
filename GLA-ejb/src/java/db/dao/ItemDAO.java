/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
