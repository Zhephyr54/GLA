/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import db.dao.OrderDAO;
import entity.Order;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;

/**
 *
 * @author Nihad
 */
@Named(value = "billingBean")
@SessionScoped
public class BillingManagedBean implements Serializable{

     @Inject
    private JMSContext context;

    @Resource(lookup = "jms/glaResponseB")
    Destination orderQueue;
    
    private Order order;
    private boolean verif = false;
    
    @EJB
    OrderDAO orderDAO;

    public BillingManagedBean() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public List<Order> getOrders() {
        return orderDAO.findAll();
    } 
    
    public boolean isVerif(){
        return this.verif;
    }
    
    public void verification(long id){
        this.verif = true;
        this.sendGlaResponse(id+"");
    }  

    @Asynchronous
    private void sendGlaResponse(String messageData) {
        context.createProducer().send(orderQueue, messageData);
    }
    
}
