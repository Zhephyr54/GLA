/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author yasar
 */
@Entity
@Table(name = "Offers")
@NamedQueries({
    @NamedQuery(
            name = "Offer.findOffer",
            query = "SELECT o FROM Offer o WHERE o.endOfferDate > :currentDate")
})
public class Offer implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "end_offer_date")
    private LocalDateTime endOfferDate;
    
    @ManyToOne
    private Subcategory subcategory;
    
    public Offer() {
    }

    public Offer(LocalDateTime endOfferDate, Subcategory subcategory) {
        this.endOfferDate = endOfferDate;
        this.subcategory = subcategory;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEndOfferDate() {
        return endOfferDate;
    }

    public void setEndOfferDate(LocalDateTime endOfferDate) {
        this.endOfferDate = endOfferDate;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
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
        if (!(object instanceof Offer)) {
            return false;
        }
        Offer other = (Offer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + id + ", endOfferDate=" + endOfferDate + '}';
    }
}
