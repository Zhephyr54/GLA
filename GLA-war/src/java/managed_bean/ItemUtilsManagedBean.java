/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import db.dao.ItemDAO;
import db.dao.OfferDAO;
import db.dao.OrderDAO;
import entity.Item;
import entity.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author alexis
 */
@Named(value = "itemUtilsBean")
@RequestScoped
public class ItemUtilsManagedBean implements Serializable {

    @EJB
    ItemDAO itemDAO;
    
    @EJB
    OfferDAO offerDAO;
    
    @EJB
    OrderDAO orderDAO;

    /**
     * Creates a new instance of ItemUtilsManagedBean
     */
    public ItemUtilsManagedBean() {
    }
    
    public boolean hasBid(Item item) {
        return getItemBiddingsNumber(item) != 0l;
    }
    
    public long getItemBiddingsNumber(Item item) {
        return itemDAO.getNumberOfBiddingsById(item.getId());
    }
    
    public BigDecimal getCurrentPrice(Item item) {
        return itemDAO.getCurrentPrice(item.getId());
    }
    
    /**
     * Return true if the bidding is over, else otherwise.
     * 
     * @param item The item
     * 
     * @return boolean
     */
    public boolean isBidOver(Item item) {
        return item.getEndBidDate().isBefore(LocalDateTime.now());
    }
    
    /**
     * Return the biddings remaining time formatted as "1j 4h"
     * or "5h 42m" or "10m 50s".
     * 
     * @param item The item
     * 
     * @return String describing the remaining time
     */
    public String getBidRemainingTime(Item item) {
        LocalDateTime endBidDate = item.getEndBidDate();
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
     * @param item The item
     * @param pattern String format
     * 
     * @return String 
     */
    public String formatEndBidDate(Item item, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return item.getEndBidDate().format(formatter);
    }
    
    public boolean winner(Item item) {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        // if bidding is over and the user won this item biddings
        return item.getEndBidDate().isBefore(LocalDateTime.now()) && user != null
                && itemDAO.getCurrentMaxBid(item.getId()).getUser().getId().equals(user.getId());
    }
    
    public boolean isItemOrdered(Item item) {
        return item.getOrder() != null; 
    }
    
}
