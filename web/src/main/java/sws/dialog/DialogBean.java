package sws.dialog;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

/**
 * User: Andrzej Polis
 * Date: 7/24/13
 * Time: 8:24 PM
 */
@Component
public class DialogBean {
    public static String RENDER_DIALOG_PROPERTY = "showDialog";

    public void registerDialogBox(DialogData dialogData) throws Exception {
        RequestContext context = RequestContextHolder.getRequestContext();
        context.getConversationScope().put(RENDER_DIALOG_PROPERTY, dialogData);
    }

    public void clearContext() {
        RequestContext context = RequestContextHolder.getRequestContext();
        context.getConversationScope().remove(RENDER_DIALOG_PROPERTY);
    }

    public void genericConfirmation() throws Exception {
        genericConfirmation("generic.confirmation.message");
    }

    public void genericConfirmation(String messageCode) throws Exception {
        genericConfirmation(messageCode, "proceed");
    }

    public void genericConfirmation(String messageCode, String action) throws Exception {
        DialogData defaultConfirmationDialog = DialogData.createDefaultConfirmationDialog(messageCode, action);
        registerDialogBox(defaultConfirmationDialog);
    }

    public void genericSummary() throws Exception {
        registerDialogBox(DialogData.createDefaultSummaryDialog("generic.summary.message"));
    }

    public void genericSummary(String messageCode) throws Exception {
        registerDialogBox(DialogData.createDefaultSummaryDialog(messageCode));
    }

    public void includeContent(String action, String include) throws Exception {
        DialogData dialog = DialogData.createWithIncludeAndAction(action, include);
        registerDialogBox(dialog);
    }
}
