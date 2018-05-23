package com.taxi.client.view.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Label;

public class OrderDialog extends PopupPanel {

    private VerticalPanel contentPanel;
    private HorizontalPanel fromToPanel;
    private VerticalPanel fromPanel;
    private VerticalPanel toPanel;
    private TextBox from;
    private Label fromLabel;
    private TextBox to;
    private Label toLabel;


    public OrderDialog() {
        createMarkUp();
    }

    private void createBorder(Widget widget, String color) {
        widget.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        widget.getElement().getStyle().setBorderColor(color);
        widget.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
    }

    private void createMarkUp() {
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setHeight(Window.getClientHeight() / 3, Style.Unit.PX);
        this.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.getElement().getStyle().setMarginTop(Window.getClientHeight() / 13, Style.Unit.PX);
        this.getElement().getStyle().setMarginLeft(2.0 / 3.2 * Window.getClientWidth(), Style.Unit.PX);
        createBorder(this, "#a3a3a3");

        contentPanel = new VerticalPanel();
        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 3, Style.Unit.PX);
        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.add(contentPanel);

        fromToPanel = new HorizontalPanel();
        fromToPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 3, Style.Unit.PX);
        fromToPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        contentPanel.add(fromToPanel);

        fromPanel = new VerticalPanel();
        fromPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 6, Style.Unit.PX);
        fromPanel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);

        fromLabel = new Label();
        fromLabel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        fromLabel.getElement().getStyle().setColor("#757575");
        fromLabel.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        fromLabel.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        fromLabel.setText("From");
        fromPanel.add(fromLabel);

        from = new TextBox();
        from.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        from.getElement().getStyle().setWidth(Window.getClientWidth() / 6.2, Style.Unit.PX);
        from.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        from.getElement().getStyle().setMarginTop(10, Style.Unit.PX);
        createBorder(from, "#a3a3a3");
        fromPanel.add(from);

        fromToPanel.add(fromPanel);

        toPanel = new VerticalPanel();
        toPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 6, Style.Unit.PX);
        toPanel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);

        toLabel = new Label();
        toLabel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        toLabel.getElement().getStyle().setColor("#757575");
        toLabel.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        toLabel.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        toLabel.setText("To");
        toPanel.add(toLabel);

        to = new TextBox();
        to.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        to.getElement().getStyle().setWidth(Window.getClientWidth() / 6.2, Style.Unit.PX);
        to.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        to.getElement().getStyle().setMarginTop(10, Style.Unit.PX);
        createBorder(to, "#a3a3a3");
        toPanel.add(to);

        fromToPanel.add(toPanel);
    }
}
