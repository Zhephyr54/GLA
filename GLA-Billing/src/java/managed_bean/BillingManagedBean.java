/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

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
import bean.OrderHandlerBean2;

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
        
    @EJB
    OrderHandlerBean2 hanldlerBean; 
    
    public BillingManagedBean() {
    }

    public List<Order> getOrders() {
        return hanldlerBean.getOrders();
    }
    
    public void validate(Order order) {
        hanldlerBean.removerOrder(order);
        this.sendGlaResponse(order.getId()+"");
    }  

    @Asynchronous
    private void sendGlaResponse(String messageData) {
        context.createProducer().send(orderQueue, messageData);
    }
    
}