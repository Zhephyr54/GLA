/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf_bean;

import entity.Bidding;
import entity.Item;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alexis
 */
@Named(value = "itemBean")
@RequestScoped
public class ItemManagedBean {

    private Item item;
    
    /**
     * Creates a new instance of ItemManagedBean
     */
    public ItemManagedBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        item = (Item) request.getAttribute("item");
    }

    public Item getItem() {
        return item;
    }
    
    public double getItemCurrentPrice() {
        Bidding maxBid = item.getCurrentMaxBid();
        if (maxBid != null) {
            return maxBid.getPrice();
        }
        return item.getStartingBid();
    }
    
}
