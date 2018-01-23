/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf_bean;

import db.dao.ItemDAO;
import db.dao.SubcategoryDAO;
import entity.Item;
import entity.Subcategory;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author karim
 */
@Named(value = "updateBiddingBean")
@ViewScoped
public class UpdateBiddingManagedBean implements Serializable {

    @EJB
    ItemDAO itemDAO;
    
    @EJB
    SubcategoryDAO s;
    
    private long itemId;
    private long subId;
    private Item item;
    private String time;
    private List<Subcategory> subcategory;
    private final String URL_REDIRECTION = "account.xhtml";
    

    public long getSubId() {
        return subId;
    }

    public void setSubId(long subId) {
        this.subId = subId;
    }

    
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public List<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    /**
     * Creates a new instance of UpdateBiddingManagedBean
     */
    public UpdateBiddingManagedBean() {
        
    }
    
    public void onload() {
        this.item = itemDAO.findById(itemId);
        this.subcategory = s.findAll();
    }
    
    public String update(){
        
        item.setEndBidDate(LocalDateTime.now().plusDays(Integer.parseInt(time)));
        item.setSubcategory(getSubCatById(subId));
        itemDAO.edit(item);
        
        return(URL_REDIRECTION);
    }
    
    public Subcategory getSubCatById(long id){
        for(int i=0; i<subcategory.size(); i++){
            if(subcategory.get(i).getId() == id)
                return subcategory.get(i);
        }
        return null;
    }
    
}
