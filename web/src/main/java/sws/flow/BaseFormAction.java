package sws.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.action.FormAction;
import sws.dialog.DialogBean;

/**
 * User: Andrzej Polis
 * Date: 9/26/13
 * Time: 2:39 PM
 */
public class BaseFormAction extends FormAction {

    @Autowired
    protected DialogBean dialogBean;

    @Override
    public Class<?> getFormObjectClass() {
        Class<?> formObjectClass = super.getFormObjectClass();
        if (formObjectClass != null) {
            return formObjectClass;
        }
        String className = getClass().getCanonicalName().replace("Action", "Object");
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return super.getFormObjectClass();
        }
    }
}
