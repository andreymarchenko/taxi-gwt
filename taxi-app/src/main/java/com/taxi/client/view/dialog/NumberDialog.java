package com.taxi.client.view.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class NumberDialog extends PopupPanel {

    private HorizontalPanel contentPanel;

    public NumberDialog() {
        createMarkUp();
    }

    private void createBorder(Widget widget, String color) {
        widget.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        widget.getElement().getStyle().setBorderColor(color);
        widget.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
    }

    public void createMarkUp() {
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setHeight(Window.getClientHeight() / 8, Style.Unit.PX);
        this.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.getElement().getStyle().setMarginTop(Window.getClientHeight() / 2.4, Style.Unit.PX);
        this.getElement().getStyle().setMarginLeft(2.0 / 3.2 * Window.getClientWidth(), Style.Unit.PX);
        createBorder(this, "#a3a3a3");

        contentPanel = new HorizontalPanel();
        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 8, Style.Unit.PX);
        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.add(contentPanel);
    }
}
