/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import db.dao.BiddingDAO;
import db.dao.ItemDAO;
import entity.Address;
import entity.CreditCard;
import entity.Item;
import entity.Order;
import entity.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author alexis
 */
@Named(value = "cartBean")
@SessionScoped
public class CartManagedBean implements Serializable {
    
    @EJB
    ItemDAO itemDAO;
    
    @EJB
    BiddingDAO biddingDAO;
    
    private List<Item> listItems = new ArrayList<>();

    private boolean winner(Item item) {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        // if bidding is over and the user won this item biddings
        return item.getEndBidDate().isBefore(LocalDateTime.now()) && user != null
                && itemDAO.getCurrentMaxBid(item.getId()).getUser().getId().equals(user.getId());
    }
    
    public List<Item> getListItems() {
        return listItems;
    }
    
    public BigDecimal getTotalPrice(){
        BigDecimal b = new BigDecimal(0);
                System.out.println(b + "aaaa");

        for(int i=0;i<listItems.size();i++)
           b =  b.add((BigDecimal)itemDAO.getCurrentPrice(listItems.get(i).getId()));
        return b;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }
    
    public int countItem() {
        return listItems.size();
    }
    
    public void addItem(Item item) {
        // if the user won this item biddings
        if (winner(item)) {
            listItems.add(item);
        }
    }
    
    public void removeItem(Item item) {
        listItems.remove(item);
    }
    
    public boolean isInCart(Item item) {
        System.out.println("is in cart");
        return listItems.contains(item);
    }
    
    public void validateCommand(Address address, CreditCard creditCard) {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUtilisateur");
        if (user == null)
            return;
        
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : listItems) {
            totalPrice.add(itemDAO.getCurrentMaxBid(item.getId()).getPrice());
        }
        Order order = new Order(user, address, creditCard, listItems, totalPrice);
    }
}
