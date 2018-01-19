/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import db.dao.ItemDAO;
import entity.Item;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author alexis
 */
@Named(value = "itemUtilsBean")
@RequestScoped
public class ItemUtilsManagedBean {

    @EJB
    ItemDAO itemDAO;
    
    /**
     * Creates a new instance of ItemUtilsManagedBean
     */
    public ItemUtilsManagedBean() {
    }
    
    public Long getItemBiddingsNumber(Item item) {
        return itemDAO.getNumberOfBiddingsById(item.getId());
    }
    
    /**
     * Return true if the bidding is over, else otherwise.
     * 
     * @param endBidDate The end bidding date for an item
     * 
     * @return boolean
     */
    public boolean isBidOver(LocalDateTime endBidDate) {
        System.out.println("LOL");
        System.out.println(endBidDate);
        return endBidDate.isBefore(LocalDateTime.now());
    }
    
    /**
     * Return the biddings remaining time formatted as "1j 4h"
     * or "5h 42m" or "10m 50s".
     * 
     * @param endBidDate The end bidding date for an item
     * 
     * @return String describing the remaining time
     */
    public String getBidRemainingTime(LocalDateTime endBidDate) {
        long days = ChronoUnit.DAYS.between(LocalDateTime.now(), endBidDate);
        endBidDate = endBidDate.minusDays(days);
        long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), endBidDate);
        endBidDate = endBidDate.minusHours(hours);
        long minutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), endBidDate);
        endBidDate = endBidDate.minusHours(hours);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), endBidDate);
        
        String result = days > 0 ? days + "j " : "";
        result += hours > 0 ? hours + "h " : "";
        result += days <= 0 && minutes > 0 ? minutes + "m " : "";
        result += days <= 0 && hours <= 0 && seconds > 0 ? seconds + "s " : "";
        return result;
    }
    
    /**
     * Format the end bidding date.
     * 
     * @param endBidDate The end bidding date for an item
     * @param pattern String format
     * 
     * @return String 
     */
    public String formatEndBidDate(LocalDateTime endBidDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return endBidDate.format(formatter);
    }
    
}
