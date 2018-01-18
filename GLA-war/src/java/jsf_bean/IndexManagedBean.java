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
    
    public Long getItemBiddingsNumber(Item item) {
        return itemDAO.getNumberOfBiddingsById(item.getId());
    }
    
    public String getEndBidTime(LocalDateTime endBidDate) {
        long days = ChronoUnit.DAYS.between(LocalDateTime.now(), endBidDate);
        endBidDate = endBidDate.minusDays(days);
        long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), endBidDate);
        endBidDate = endBidDate.minusHours(hours);
        long minutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), endBidDate);
        endBidDate = endBidDate.minusHours(hours);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), endBidDate);
        
        String result = days > 0 ? days + " j " : "";
        result += hours > 0 ? hours + " h " : "";
        result += days <= 0 && minutes > 0 ? minutes + " m " : "";
        result += days <= 0 && hours <= 0 && seconds > 0 ? seconds + " s " : "";
        return result;
    }
    
    public String formatEndBidDate(LocalDateTime endBidDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd, HH:mm");
        return endBidDate.format(formatter);
    }
    
}
