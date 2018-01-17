/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import entity.User;
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
public class UserDAO extends AbstractDAO<User> {

    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    public UserDAO() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Boolean findByEmail(String mail) {
        TypedQuery<User> query = getEntityManager().createNamedQuery("User.findByEmail", User.class);
        List<User> u = query.setParameter("email", mail).getResultList();
        if(u.size() > 0)
            return true;
        return false;
    }
      
    public Boolean testCo(String mail, String password) {
        TypedQuery<User> query = getEntityManager().createNamedQuery("User.findByEmailAndPassword", User.class);
        query.setParameter("email", mail);
        List<User> u = query.setParameter("password", password).getResultList();
        if(u.size() > 0)
            return true;
        return false;
    }
}
