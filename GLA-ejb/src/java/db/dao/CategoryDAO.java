/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Category;
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
public class CategoryDAO extends AbstractDAO<Category> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public CategoryDAO() {
        super(Category.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

    public List<Category> findall() {
        TypedQuery<Category> query = getEntityManager().createNamedQuery("Category.findAll", Category.class);
        List<Category> u = query.getResultList();
        return u;
    }

}
