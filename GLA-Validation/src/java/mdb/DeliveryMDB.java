/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import entity.Order;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import bean.OrderHandlerBean2;

/**
 *
 * @author Nihad
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/glaRequest"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class DeliveryMDB implements MessageListener {
    
    @EJB
    private OrderHandlerBean2 handlerBean;
    
    public DeliveryMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            Order order = message.getBody(Order.class);
            if (order != null) {
                handlerBean.addOrder(order);
            }
        } catch (JMSException ex) {}
    }
    
}
