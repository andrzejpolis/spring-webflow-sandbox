package sws;

import org.apache.commons.beanutils.BeanUtils;

import javax.faces.component.SelectItemsUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * User: Andrzej Polis
 * Date: 9/26/13
 * Time: 11:50 AM
 */
public class SelectItemsBaseConverter implements Converter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Object itemValueAsStringAttr = component.getAttributes().get("itemValueAsString");
        if (itemValueAsStringAttr == null) {
            throw new IllegalStateException("itemValueAsString attribute missing");
        }
        try {
            return BeanUtils.getProperty(value, itemValueAsStringAttr.toString());
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Object %s has no poperty %s", value, itemValueAsStringAttr));
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return SelectItemsUtils.findValueByStringConversion(context, component, value, this);
    }
}

