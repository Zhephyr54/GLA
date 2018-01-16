/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Category;
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
public class CategoryDAO extends AbstractDAO<Category> {
    
    @PersistenceContext(unitName = "imp-pu")
    private EntityManager em;

    public CategoryDAO() {
        super(Category.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

}
