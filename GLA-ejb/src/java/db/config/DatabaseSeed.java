/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.config;

import entity.Item;
import entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Singleton to feed the database at startup.
 * 
 * @author alexis
 */
@Singleton
@LocalBean
@Startup
public class DatabaseSeed {

    @PersistenceContext(unitName = "glaPU")
    private EntityManager em;

    @PostConstruct
    public void seed() {
        User user1 = new User("jean.valjean@mail.fr", "password", "Jean", "Valjean");
        
        Item item1 = new Item("Iphone 12", "Le tout nouvel iphone quasi neuf !", 499.99, LocalDateTime.now().plusDays(5));
        item1.setUser(user1);

        em.persist(user1);
        em.persist(item1);    
    }   

}
