/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import db.dao.OrderDAO;
import entity.Order;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Nihad
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/glaResponse")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class OrderMDB implements MessageListener {

    @EJB
    OrderDAO orderDAO;

    public OrderMDB() {
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {
                String text = tm.getText();
                Long id = Long.valueOf(text);
                Order o = orderDAO.findById(id);
                o.setOrderState(Order.OrderState.SENT);
            } catch (JMSException e) {
            }
        }
    }

}
