package jsf_bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.dao.ItemDAO;
import entity.Item;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alexis
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexManagedBean {

    @EJB
    ItemDAO itemDAO;
        
    /**
     * Creates a new instance of IndexManagedBean
     */
    public IndexManagedBean() {
    }
    
    public List<Item> getItems() {
        return itemDAO.findAll();
    }
    
}
