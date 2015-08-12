package ch.tiim.sco.print;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;

public class PrintUtils {

    private final PageLayout layout;

    public PrintUtils(Printer printer) {
        layout = printer.createPageLayout(
                Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT
        );
    }

    public int getWidth() {
        return (int) layout.getPrintableWidth();
    }

    public int getHeight() {
        return (int) layout.getPrintableHeight();
    }

    public double getMargTop() {
        return layout.getTopMargin();
    }

    public double getMargBottom() {
        return layout.getBottomMargin();
    }

    public double getMargLeft() {
        return layout.getLeftMargin();
    }

    public double getMargRight() {
        return layout.getRightMargin();
    }
}
