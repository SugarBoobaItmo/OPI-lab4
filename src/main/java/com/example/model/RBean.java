package com.example.model;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import lombok.Data;

import java.io.Serializable;

@Data
public class RBean implements Serializable {
    private Double value = 1.0;

    public void validate(FacesContext facesContext,
            UIComponent uiComponent, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Input R!");
            throw new ValidatorException(message);
        } else if (Double.parseDouble(o.toString()) > 3 || Double.parseDouble(o.toString()) < 1
                || Double.parseDouble(o.toString()) % 0.5 != 0) {
            FacesMessage message = new FacesMessage("R must be in range [1;3]! with step 0.5!");
            throw new ValidatorException(message);
        }
    }
}