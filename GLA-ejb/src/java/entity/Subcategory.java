/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author yasar
 */
@Entity
@Table(name = "Subcategories")
@NamedQueries({
    @NamedQuery(
            name = "Subcategory.findByCategory",
            query = "SELECT s FROM Subcategory s WHERE s.category.id = :id ")
    ,
     @NamedQuery(
            name = "Subcategory.findAll",
            query = "SELECT s FROM Subcategory s ")
    ,
     @NamedQuery(
            name = "Subcategory.findById",
            query = "SELECT s FROM Subcategory s WHERE s.id = :id")
})
public class Subcategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    private List<Item> items = new ArrayList<Item>();
    
    @OneToMany(mappedBy="subcategory")
    private List<Offer> offers;
    

    public Subcategory() {
    }

    public Subcategory(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItems(Item item) {
        item.setSubcategory(this);
        this.items.add(item);
    }
    
        public List<Offer> getOffers() {
        return offers;
    }

    public void addOffer(Offer offer) {
        offer.setSubcategory(this);
        this.offers.add(offer);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcategory)) {
            return false;
        }
        Subcategory other = (Subcategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subcategory{" + "id=" + id + ", title=" + title + '}';
    }
}
