/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import db.dao.ItemDAO;
import entity.Address;
import entity.CreditCard;
import entity.Item;
import entity.Order;
import entity.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexis
 */
public class CartManagedBean {
    
    @EJB
    ItemDAO itemDAO;
    
    public List<Item> listItems;
    
    public void addItem(Item item) {
        // if bidding is over and the user won this item biddings
        if (item.getEndBidDate().isBefore(LocalDateTime.now())) {
            listItems.add(item);
        }
    }
    
    public void removeItem(Item item) {
        listItems.remove(item);
    }
    
    public boolean isInCart(Item item) {
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
