/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.Subcategory;
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
public class SubcategoryDAO extends AbstractDAO<Subcategory> {
    
    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public SubcategoryDAO() {
        super(Subcategory.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    } 

    public List<Subcategory> findByCategory(int id) {
        TypedQuery<Subcategory> query = getEntityManager().createNamedQuery("Subcategory.findByCategory", Subcategory.class);
        List<Subcategory> s = query.setParameter("id", id).getResultList();
        return s;
    }
    
    public List<Subcategory> findAll() {
        TypedQuery<Subcategory> query = getEntityManager().createNamedQuery("Subcategory.findAll", Subcategory.class);
        List<Subcategory> s = query.getResultList();
        return s;
    }
        
    public Subcategory findById(int id) {
        TypedQuery<Subcategory> query = getEntityManager().createNamedQuery("Subcategory.findById", Subcategory.class);
        List<Subcategory> s = query.setParameter("id", id).getResultList();
        if(s.size() > 0)
            return s.get(0);
        return null;
    }
}
