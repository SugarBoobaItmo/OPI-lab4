package com.example.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Named
@ApplicationScoped
public class XBean implements Serializable {
    private Double value = 0.0;
    private List<Double> XValues = Arrays.asList(-5.0, -4.0, -3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0);

    public void validate(FacesContext facesContext,
            UIComponent uiComponent, Object o) {
        if (o == null) {
            FacesMessage message = new FacesMessage("Input X!");
            throw new ValidatorException(message);
        } else if (!XValues.contains(Double.parseDouble(o.toString()))) {
            FacesMessage message = new FacesMessage("X must be in range [-5;5] with step 1!");
            throw new ValidatorException(message);
        }
    }
}
