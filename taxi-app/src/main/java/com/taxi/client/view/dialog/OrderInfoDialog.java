package com.taxi.client.view.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class OrderInfoDialog extends PopupPanel {
    private VerticalPanel contentPanel;
    private HorizontalPanel infoPanel;
    private Label order;
    private Label duration;
    private Label distance;
    private Label durationLabel;
    private Label distanceLabel;

    private void createBorder(Widget widget, String color) {
        widget.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        widget.getElement().getStyle().setBorderColor(color);
        widget.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
    }

    public OrderInfoDialog() {
        contentPanel = new VerticalPanel();
        infoPanel = new HorizontalPanel();
        duration = new Label();
        distance = new Label();
        durationLabel = new Label();
        distanceLabel = new Label();
        create();
    }

    public void setDuration(String durationText) {
        duration.setText(durationText);
    }

    public void setDistance(String distanceText) {
        distance.setText(distanceText);
    }

    public void create() {
        this.getElement().getStyle().setBackgroundColor("White");
        this.getElement().getStyle().setHeight(Window.getClientHeight() / 7, Style.Unit.PX);
        this.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.getElement().getStyle().setMarginTop(Window.getClientHeight() * 0.5353, Style.Unit.PX);
        this.getElement().getStyle().setMarginLeft(2.0 / 3.2 * Window.getClientWidth(), Style.Unit.PX);
        createBorder(this, "#a3a3a3");

        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 7, Style.Unit.PX);
        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 2.7, Style.Unit.PX);
        this.add(contentPanel);

        order = new Label();
        order.getElement().getStyle().setMarginLeft(Window.getClientWidth() / 6.7, Style.Unit.PX);
        order.getElement().getStyle().setMarginTop(Window.getClientHeight() / 40, Style.Unit.PX);
        order.getElement().getStyle().setFontSize(Window.getClientHeight() / 30, Style.Unit.PX);
        order.setText("Order info");
        contentPanel.add(order);

        infoPanel = new HorizontalPanel();
        durationLabel.getElement().getStyle().setMarginLeft(Window.getClientWidth() / 16, Style.Unit.PX);
        durationLabel.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        durationLabel.setText("Journey duration:");
        distanceLabel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        distanceLabel.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        distanceLabel.setText("Distance:");
        duration.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        duration.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        distance.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        distance.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        infoPanel.add(durationLabel);
        infoPanel.add(duration);
        infoPanel.add(distanceLabel);
        infoPanel.add(distance);

        contentPanel.add(infoPanel);

    }

}
