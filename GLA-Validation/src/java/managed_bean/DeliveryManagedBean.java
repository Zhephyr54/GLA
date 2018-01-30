/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import bean.OrderHandlerBean;
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
@Named(value = "deliveryBean")
@SessionScoped
public class DeliveryManagedBean implements Serializable {

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/glaResponse")
    Destination orderQueue;

    @EJB
    OrderHandlerBean hanldlerBean;     

    public DeliveryManagedBean() {
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
