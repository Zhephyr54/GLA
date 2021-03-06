/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.config;

import entity.Address;
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
        User user2 = new User("test@mail.fr", "123456", "Nihad", "JeVeux5utilisateurs");
        User user3 = new User("aa@yopmail.fr", "123456", "demir", "yasar");

        Item item1 = new Item("Iphone 12", "Le tout nouvel iphone quasi neuf !", BigDecimal.valueOf(499.99), LocalDateTime.now().plusDays(5));
        item1.setUser(user1);

        // item for checking out of date biddings
        Item item2 = new Item("Iphone périmé", "Périmé depuis 10 ans", BigDecimal.valueOf(1), LocalDateTime.now().minusYears(10));
        item2.setUser(user1);

        Item item3 = new Item("Samsung s8", "neuf bien evidemment !", BigDecimal.valueOf(299.99), LocalDateTime.now().plusDays(5));
        item3.setUser(user1);
        Item item4 = new Item("Tv monitor", "belle image!", BigDecimal.valueOf(2099.99), LocalDateTime.now().plusMinutes(1));
        item4.setUser(user2);
        Item item5 = new Item("Tv 4k", "Incroyable du pure 4K", BigDecimal.valueOf(10099.99), LocalDateTime.now());
        item5.setUser(user2);
        Item item6 = new Item("BMX super cool", "Super BMX trop cool ! En très bon état. A ne pas louper !", BigDecimal.valueOf(50.60), LocalDateTime.now().plusDays(3));
        item6.setUser(user3);
        
        Category c1 = new Category("MULTIMEDIA");

        Subcategory s1 = new Subcategory("Informatique");
        Subcategory s2 = new Subcategory("Jeux vidéos");

        Category c2 = new Category("LOISIRS");

        Subcategory s3 = new Subcategory("Livres");
        Subcategory s4 = new Subcategory("Vélos");
        
        Category c3 = new Category("VEHICULES");
        
        Subcategory s5 = new Subcategory("Voitures");
        Subcategory s6 = new Subcategory("Motos");

        
        s1.setCategory(c1);
        s2.setCategory(c1);
        s3.setCategory(c2);
        s4.setCategory(c2);
        s5.setCategory(c3);
        s6.setCategory(c3);
        
        item1.setSubcategory(s1);
        item2.setSubcategory(s1);
        item3.setSubcategory(s1);
        item4.setSubcategory(s1);
        item5.setSubcategory(s1);
        item6.setSubcategory(s4);
        
        Address adr = new Address("10 rue des aaa");
        adr.setUser(user1);
        Address adr2 = new Address("11 rue des aaa");
        adr2.setUser(user1);

        Bidding bidding1 = new Bidding(BigDecimal.valueOf(500), user2, item1);
        Bidding bidding2 = new Bidding(BigDecimal.valueOf(10100), user3, item5);
        Bidding bidding3 = new Bidding(BigDecimal.valueOf(10101), user1, item5);
        Bidding bidding4 = new Bidding(BigDecimal.valueOf(10102), user1, item5);
        Bidding bidding5 = new Bidding(BigDecimal.valueOf(10103), user1, item5);
        Bidding bidding6 = new Bidding(BigDecimal.valueOf(10104), user1, item5);
        Bidding bidding7 = new Bidding(BigDecimal.valueOf(10105), user1, item5);

        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now().plusDays(1);
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);

        Offer o = new Offer(todayMidnight, s1);

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(item1);
        em.persist(item2);
        em.persist(item3);
        em.persist(item4);
        em.persist(item5);
        em.persist(item6);
        em.persist(bidding1);
        em.persist(bidding2);
        em.persist(bidding3);
        em.persist(bidding4);
        em.persist(bidding5);
        em.persist(bidding6);
        em.persist(bidding7);
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(s4);
        em.persist(s5);
        em.persist(s6);
        em.persist(adr);
        em.persist(adr2);
        em.persist(o);
    }

}
