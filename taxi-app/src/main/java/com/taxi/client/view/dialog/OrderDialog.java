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
    private Label hintFrom;
    private Label hintTo;
    private TextBox orderTime;
    private Label orderTimeLabel;
    private Label hintTime;
    private ListBox paymentType;
    private Label paymentTypeLabel;
    private Button calculateRouteButton;
    private Button makeOrderButton;

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

        hintFrom = new Label();
        hintFrom.setText("Example: 56.238502, 43.861445");
        hintFrom.getElement().getStyle().setColor("#757575");
        hintFrom.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        hintFrom.getElement().getStyle().setMarginTop(5, Style.Unit.PX);
        hintFrom.getElement().getStyle().setFontSize(15, Style.Unit.PX);

        orderTimeLabel = new Label();
        orderTimeLabel.setText("Order time");
        orderTimeLabel.getElement().getStyle().setColor("#757575");
        orderTimeLabel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        orderTimeLabel.getElement().getStyle().setMarginTop(20, Style.Unit.PX);
        orderTimeLabel.getElement().getStyle().setFontSize(15, Style.Unit.PX);

        orderTime = new TextBox();
        orderTime.getElement().getStyle().setFontSize(20, Style.Unit.PX);
        orderTime.getElement().getStyle().setWidth(Window.getClientWidth() / 6.2, Style.Unit.PX);
        orderTime.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        orderTime.getElement().getStyle().setMarginTop(10, Style.Unit.PX);

        hintTime = new Label();
        hintTime.setText("Example: 10:25");
        hintTime.getElement().getStyle().setColor("#757575");
        hintTime.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        hintTime.getElement().getStyle().setMarginTop(5, Style.Unit.PX);
        hintTime.getElement().getStyle().setFontSize(15, Style.Unit.PX);

        calculateRouteButton = new Button();
        calculateRouteButton.setText("Calculate route");
        calculateRouteButton.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        calculateRouteButton.getElement().getStyle().setWidth(Window.getClientWidth() / 6.2, Style.Unit.PX);
        calculateRouteButton.getElement().getStyle().setHeight(Window.getClientHeight() / 12, Style.Unit.PX);
        calculateRouteButton.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        calculateRouteButton.getElement().getStyle().setMarginTop(30, Style.Unit.PX);

        makeOrderButton = new Button();

        fromPanel.add(from);
        fromPanel.add(hintFrom);
        fromPanel.add(orderTimeLabel);
        fromPanel.add(orderTime);
        fromPanel.add(hintTime);
        fromPanel.add(calculateRouteButton);

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

        hintTo = new Label();
        hintTo.setText("Example: 56.2331, 43.56");
        hintTo.getElement().getStyle().setColor("#757575");
        hintTo.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        hintTo.getElement().getStyle().setMarginTop(5, Style.Unit.PX);
        hintTo.getElement().getStyle().setFontSize(15, Style.Unit.PX);

        paymentTypeLabel = new Label();
        paymentTypeLabel.setText("Select payment type");
        paymentTypeLabel.getElement().getStyle().setColor("#757575");
        paymentTypeLabel.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        paymentTypeLabel.getElement().getStyle().setMarginTop(20, Style.Unit.PX);
        paymentTypeLabel.getElement().getStyle().setFontSize(15, Style.Unit.PX);

        paymentType = new ListBox();
        paymentType.getElement().getStyle().setColor("#757575");
        paymentType.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        paymentType.getElement().getStyle().setMarginTop(10, Style.Unit.PX);
        paymentType.getElement().getStyle().setFontSize(15, Style.Unit.PX);
        paymentType.addItem("Card");
        paymentType.addItem("Cash");

        makeOrderButton = new Button();
        makeOrderButton.setText("Make an order");
        makeOrderButton.getElement().getStyle().setFontSize(25, Style.Unit.PX);
        makeOrderButton.getElement().getStyle().setWidth(Window.getClientWidth() / 6.2, Style.Unit.PX);
        makeOrderButton.getElement().getStyle().setHeight(Window.getClientHeight() / 12, Style.Unit.PX);
        makeOrderButton.getElement().getStyle().setMarginLeft(10, Style.Unit.PX);
        makeOrderButton.getElement().getStyle().setMarginTop(60, Style.Unit.PX);

        toPanel.add(to);
        toPanel.add(hintTo);
        toPanel.add(paymentTypeLabel);
        toPanel.add(paymentType);
        toPanel.add(makeOrderButton);

        fromToPanel.add(toPanel);
    }
}
