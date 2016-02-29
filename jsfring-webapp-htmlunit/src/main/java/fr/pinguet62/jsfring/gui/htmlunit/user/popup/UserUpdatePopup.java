package fr.pinguet62.jsfring.gui.htmlunit.user.popup;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import fr.pinguet62.jsfring.gui.htmlunit.datatable.popup.UpdatePopup;
import fr.pinguet62.jsfring.gui.htmlunit.field.CheckBox;
import fr.pinguet62.jsfring.gui.htmlunit.field.Field;
import fr.pinguet62.jsfring.gui.htmlunit.field.InputText;
import fr.pinguet62.jsfring.gui.htmlunit.field.PickList;
import fr.pinguet62.jsfring.gui.htmlunit.field.ReadWriteField;
import fr.pinguet62.jsfring.gui.htmlunit.field.StringOutputText;

public final class UserUpdatePopup extends AbstractUserPopup implements UpdatePopup {

    public UserUpdatePopup(HtmlPage page) {
        super(page);
    }

    public ReadWriteField<?, String> getEmail() {
        return new InputText(
                (HtmlInput) getFieldTableCell(1).getByXPath("./input[contains(@class, 'ui-inputtext')]").get(0));
    }

    public Field<?, String> getLogin() {
        return new StringOutputText((HtmlSpan) getFieldTableCell(0).getByXPath("./span").get(0));
    }

    public Field<?, ?> getProfiles() {
        return new PickList((HtmlDivision) getFieldTableCell(4).getByXPath("./div").get(0));
    }

    public Field<?, ?> isActive() {
        return new CheckBox(
                (HtmlDivision) getFieldTableCell(2).getByXPath("./div[contains(@class, 'ui-chkbox')]").get(0));
    }

}