/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import db.dao.OrderDAO;
import entity.Order;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author NIhad
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/glaResponseB")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BillingMDB implements MessageListener {

    @EJB
    OrderDAO orderDAO;

    @Inject
    private JMSContext billingContext;

    @Resource(lookup = "jms/glaRequest")
    Destination deliveryQueue;

    public BillingMDB() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {
                String text = tm.getText();
                Long id = Long.valueOf(text);
                Order o = orderDAO.findById(id);
                o.setOrderState(Order.OrderState.IN_PROCESS);
                sendGlaRequest(o);
            } catch (JMSException e) {
            }
        }
    }
    
    @Asynchronous
    private void sendGlaRequest(Order order) {
        billingContext.createProducer().send(deliveryQueue, order);
    }

}
