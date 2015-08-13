package ch.tiim.sco.gui.lenex;

import ch.tiim.sco.gui.Page;

import java.io.InputStream;

public class LenexPresenter extends Page {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public InputStream getIcon() {
        return LenexPresenter.class.getResourceAsStream("icon3.png");
    }

    @Override
    public void opened() {
    }
}
