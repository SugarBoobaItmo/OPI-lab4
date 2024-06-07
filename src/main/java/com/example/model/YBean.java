package com.example.model;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import lombok.Data;

import java.io.Serializable;

@Data
public class YBean implements Serializable {
    private Double value = 0.0;

    public void validate(FacesContext facesContext,
            UIComponent uiComponent, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Input Y!");
            throw new ValidatorException(message);
        } else if (Double.parseDouble(o.toString()) > 5 || Double.parseDouble(o.toString()) < -3) {
            FacesMessage message = new FacesMessage("Y must be in range [-3;5]!");
            throw new ValidatorException(message);
        }
    }
}