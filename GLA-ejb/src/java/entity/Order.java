/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
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
@NamedQueries({
    @NamedQuery(
            name = "Order.findOrderByUserId", 
            query = "SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.orderDate DESC"),
    @NamedQuery(
            name = "Order.findOrderByItemId",
            query = "SELECT i.order FROM Item i WHERE  i.id = :itemId"
    )
})
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    public enum OrderState { IN_PROCESS, SENT }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "order_date")
    private LocalDateTime orderDate = LocalDateTime.now();
    
    @Column(name = "total_price", precision = 9, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "order_state")
    private OrderState orderState = OrderState.IN_PROCESS;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Address address;
    
    @ManyToOne
    private CreditCard creditCard;
    
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<Item> items;
    
    public Order() {
    }

    public Order(User user, Address address, CreditCard creditCard, List<Item> items, BigDecimal totalPrice) {
        this.user = user;
        this.address = address;
        this.creditCard = creditCard;
        this.items = items;
        this.totalPrice = totalPrice;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        item.setOrder(this);
        this.items.add(item);
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
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + orderDate + ", totalPrice=" + totalPrice + '}';
    }   
}
