package fr.pinguet62.jsfring.gui.htmlunit.jasperreport;

import java.awt.Panel;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import fr.pinguet62.jsfring.gui.htmlunit.AbstractPage;
import fr.pinguet62.jsfring.gui.htmlunit.NavigatorException;

/** @see AbstractJasperReportBean */
public abstract class AbstractJasperReportPage extends AbstractPage {

    public AbstractJasperReportPage(HtmlPage page) {
        super(page);
    }

    /**
     * Into the {@link Panel} footer, find the {@link HtmlButton} from title.
     *
     * @return The {@link InputStream} returned after export button click.
     */
    protected InputStream export(String buttonTitle) {
        @SuppressWarnings("unchecked")
        List<HtmlButton> buttons = (List<HtmlButton>) page.getByXPath(
                "//div[contains(@class, 'ui-layout-center')]//form//div[contains(@class, 'ui-panel-footer')]//button");
        HtmlButton exporter = buttons.stream()
                .filter(button -> ((HtmlSpan) button.getByXPath("./span").get(0)).getTextContent().equals(buttonTitle))
                .findAny().get();
        try {
            return exporter.click().getWebResponse().getContentAsStream();
        } catch (IOException e) {
            throw new NavigatorException(e);
        }
    }

    public InputStream exportCSV() {
        return export("CSV");
    }

    public InputStream exportDOCX() {
        return export("DOCX");
    }

    public InputStream exportGraphics2D() {
        return export("Graph 2D");
    }

    public InputStream exportHTML() {
        return export("HTML");
    }

    public InputStream exportODS() {
        return export("ODS");
    }

    public InputStream exportODT() {
        return export("ODT");
    }

    public InputStream exportPDF() {
        return export("PDF");
    }

    public InputStream exportPPTX() {
        return export("PPTX");
    }

    public InputStream exportRTF() {
        return export("RTF");
    }

    public InputStream exportTEXT() {
        return export("TEXT");
    }

    public InputStream exportXLS() {
        return export("XLS");
    }

    public InputStream exportXLSX() {
        return export("XLSX");
    }

    public InputStream exportXML() {
        return export("XML");
    }

}