package sws.dialog;

import org.springframework.util.Assert;

import java.io.Serializable;

public class DialogData implements Serializable {
    private static final long serialVersionUID = 6317115735431565798L;

    private DialogData(DialogData.DialogType type) {
        this.type = type;
    }

    private DialogData.DialogType type;
    private String title;
    private String message;
    private Object[] messageArgs;
    private DialogData.DialogButton leftButton;
    private DialogData.DialogButton rightButton;
    private boolean definedContent;
    private String include;

    public DialogData.DialogType getType() {
        return type;
    }

    public void setType(DialogData.DialogType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public void setMessageArgs(Object[] messageArgs) {
        this.messageArgs = messageArgs;
    }

    public DialogData.DialogButton getLeftButton() {
        return leftButton;
    }

    public void setLeftButton(DialogData.DialogButton leftButton) {
        this.leftButton = leftButton;
    }

    public DialogData.DialogButton getRightButton() {
        return rightButton;
    }

    public void setRightButton(DialogData.DialogButton rightButton) {
        this.rightButton = rightButton;
    }

    public boolean isDefinedContent() {
        return definedContent;
    }

    public void setDefinedContent(boolean definedContent) {
        this.definedContent = definedContent;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public static DialogData createDefaultConfirmationDialog(String message, String action) {
        DialogData confirmationDialog = createDefaultConfirmation(action);
        confirmationDialog.setMessage(message);
        return confirmationDialog;
    }

    public static DialogData createDefaultConfirmationWithDefinedBody(String action) {
        DialogData confirmationDialog = createDefaultConfirmation(action);
        confirmationDialog.setDefinedContent(true);
        return confirmationDialog;
    }

    public static DialogData createWithIncludeAndAction(String action, String include) {
        DialogData confirmationDialog = createDefaultConfirmation(action);
        confirmationDialog.setInclude(include);
        return confirmationDialog;
    }

    private static DialogData createDefaultConfirmation(String action) {
        DialogData confirmationDialog = new DialogData(DialogType.CONFIRMATION);
        confirmationDialog.setTitle("generic.confirmation.title");
        confirmationDialog.setLeftButton(DialogButton.closeButton("generic.confirmation.button.back"));
        confirmationDialog.setRightButton(DialogButton.proceedButton("generic.confirmation.button.next", action));
        return confirmationDialog;
    }


    public static DialogData createDefaultSummaryDialog(String message) {
        return createDefaultSummaryDialog(message, null);
    }

    public static DialogData createDefaultSummaryDialog(String message, Object[] arguments) {
        DialogData summaryDialog = new DialogData(DialogType.SUMMARY);
        summaryDialog.setTitle("generic.summary.title");
        summaryDialog.setMessage(message);
        summaryDialog.setMessageArgs(arguments);
        summaryDialog.setRightButton(DialogButton.closeButton("generic.confirmation.button.ok"));
        return summaryDialog;
    }

    public static DialogData createDefaultSummaryDialog(String title, String message, Object[] arguments) {
        Assert.notNull(title);
        DialogData dd = createDefaultSummaryDialog(message, arguments);
        dd.setTitle(title);
        return dd;
    }


    /**
     * Creates dialog box with inner content defined somewhere in xhtml. Content must be defined by:
     * <ui:define name="dialogBody">
     * </ui:define>
     * @return
     */
    public static DialogData createDefaultSummaryWithDefinedBody() {
        DialogData summaryDialog = new DialogData(DialogType.SUMMARY);
        summaryDialog.setTitle("generic.summary.title");
        summaryDialog.setDefinedContent(true);
        summaryDialog.setRightButton(DialogButton.closeButton("generic.confirmation.button.next"));
        return summaryDialog;
    }

    public static class DialogButton implements Serializable {
        private static final long serialVersionUID = 2484502868086390447L;

        private static enum DialogButtonType {
            CLOSE, PROCEED
        }

        private DialogButton.DialogButtonType type;
        private String label;
        private String action;

        public DialogButton.DialogButtonType getType() {
            return type;
        }

        public void setType(DialogButton.DialogButtonType type) {
            this.type = type;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public static DialogData.DialogButton closeButton(String label) {
            DialogData.DialogButton dialogButton = new DialogButton();
            dialogButton.setType(DialogButtonType.CLOSE);
            dialogButton.setLabel(label);
            return dialogButton;
        }

        public static DialogData.DialogButton proceedButton(String label, String action) {
            DialogData.DialogButton dialogButton = new DialogButton();
            dialogButton.setType(DialogButtonType.PROCEED);
            dialogButton.setLabel(label);
            dialogButton.setAction(action);
            return dialogButton;
        }
    }

    private static enum DialogType {
        CONFIRMATION, SUMMARY
    };
}