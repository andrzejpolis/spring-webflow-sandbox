package sws;

import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

/**
 * User: Andrzej Polis
 * Date: 9/26/13
 * Time: 12:00 PM
 */
public class CustomFlowHandlerMapping extends FlowHandlerMapping {

    private String pageNotFoundFlow;

    @Override
    public Object getDefaultHandler() {
        return createDefaultFlowHandler(pageNotFoundFlow);
    }

    public String getPageNotFoundFlow() {
        return pageNotFoundFlow;
    }

    public void setPageNotFoundFlow(String pageNotFoundFlow) {
        this.pageNotFoundFlow = pageNotFoundFlow;
    }
}
