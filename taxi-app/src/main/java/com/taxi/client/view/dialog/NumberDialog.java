package com.taxi.client.view.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class NumberDialog extends PopupPanel {

    private HorizontalPanel contentPanel;
    private Label number;

    public NumberDialog() {
        contentPanel = new HorizontalPanel();
        number = new Label();
        createMarkUp();
    }

    private void createBorder(Widget widget, String color) {
        widget.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        widget.getElement().getStyle().setBorderColor(color);
        widget.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
    }

    public void createMarkUp() {
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setHeight(Window.getClientHeight() / 9, Style.Unit.PX);
        this.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.getElement().getStyle().setMarginTop(Window.getClientHeight() / 2.4, Style.Unit.PX);
        this.getElement().getStyle().setMarginLeft(2.0 / 3.2 * Window.getClientWidth(), Style.Unit.PX);
        createBorder(this, "#a3a3a3");

        number.getElement().getStyle().setFontSize(Window.getClientHeight() / 32, Style.Unit.PX);
        number.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        number.getElement().getStyle().setMarginTop(Window.getClientHeight() / 28, Style.Unit.PX);
        number.setText("Заказ такси по телефону: +7 831 00 00 00");
        contentPanel.add(number);

        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 9, Style.Unit.PX);
        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.add(contentPanel);
    }
}
