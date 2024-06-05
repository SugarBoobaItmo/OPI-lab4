package com.example.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("doubleConverter")
public class DoubleConverter implements Converter<Double> {
    @Override
    public Double getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Double.parseDouble(value.replace(',', '.'));
            } catch (NumberFormatException e) {
                throw new ConverterException("Invalid double value: " + value, e);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Double Double) {
        if (Double != null) {
            if (Double instanceof Double) {
                return String.format("%.2f", (Double) Double).replace('.', ',');
            } else {
                throw new ConverterException("Invalid type: " + Double.getClass().getName()
                        + ". DoubleConverter can only convert Double values.");
            }
        }
        return null;
    }
}