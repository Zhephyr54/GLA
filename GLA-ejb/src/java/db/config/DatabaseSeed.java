/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.config;

import entity.Bidding;
import entity.Category;
import entity.Item;
import entity.Offer;
import entity.Subcategory;
import entity.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        User user2 = new User("test@mail.fr", "test", "Nihad", "JeVeux5utilisateurs");
        
        Item item1 = new Item("Iphone 12", "Le tout nouvel iphone quasi neuf !", BigDecimal.valueOf(499.99), LocalDateTime.now().plusDays(5));
        item1.setUser(user1);
        
        // item for checking out of date biddings
        Item item2 = new Item("Iphone périmé", "Périmé depuis 10 ans", BigDecimal.valueOf(1), LocalDateTime.now().minusYears(10));
        item2.setUser(user1);
        
        Item item3 = new Item("Samsung s8", "neuf bien evidemment !", BigDecimal.valueOf(299.99), LocalDateTime.now().plusDays(5));
        item3.setUser(user1);
        Item item4 = new Item("Tv monitor", "belle image!", BigDecimal.valueOf(2099.99), LocalDateTime.now().plusDays(5));
        item4.setUser(user2);
        Item item5 = new Item("Tv 4k", "Incroyable du pure 4K", BigDecimal.valueOf(10099.99), LocalDateTime.now().plusDays(5));
        item5.setUser(user2);
        
        Category c1 = new Category("Multimedia"); 
        Category c2 = new Category("Loisirs"); 
        
        Subcategory s1 = new Subcategory("Informatique");
        Subcategory s2 = new Subcategory("Jeux");
        Subcategory s3 = new Subcategory("Livres");
        Subcategory s4 = new Subcategory("Velos");
        
        s1.setCategory(c1);
        s2.setCategory(c1);
        s3.setCategory(c2);
        s4.setCategory(c2);
        
        
        item1.setSubcategory(s1);
        item2.setSubcategory(s1);
        item3.setSubcategory(s1);
        item4.setSubcategory(s1);
        item5.setSubcategory(s1);

        Bidding bidding1 = new Bidding(BigDecimal.valueOf(500), user2, item1);
        item1.setCurrentMaxBid(bidding1);
        
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now().plusDays(1);
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        
        Offer o = new Offer(todayMidnight,s1);
        
        em.persist(user1);
        em.persist(user2);
        em.persist(item1);
        em.persist(item2);
        em.persist(item3);
        em.persist(item4);
        em.persist(item5);
        em.persist(bidding1);
        em.persist(c1);
        em.persist(c2);
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(s4); 
        em.persist(o);
    }   

}
