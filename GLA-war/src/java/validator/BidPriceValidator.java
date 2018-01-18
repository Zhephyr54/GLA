/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author alexis
 */
@FacesValidator("BidPriceValidator")
public class BidPriceValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        BigDecimal minPrice = (BigDecimal) component.getAttributes().get("minPrice");
        boolean isFirstBid = (boolean) component.getAttributes().get("isFirstBid");
        
        if (((BigDecimal) value).compareTo(minPrice) == -1 || !isFirstBid && ((BigDecimal) value).compareTo(minPrice) == 0) {
            String msg = isFirstBid ? "Vous devez entrer un montant supérieur ou égal au montant initial de l'enchère " + minPrice
                    : "Vous devez entrer un montant supérieur au montant de la meilleure enchère " + minPrice;
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
    }
    
}
