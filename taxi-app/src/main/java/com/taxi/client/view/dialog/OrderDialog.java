package com.taxi.client.view.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class OrderDialog extends PopupPanel {

    interface OrderDialogUiBinder extends UiBinder<Widget, OrderDialog> {
    }

    private static OrderDialogUiBinder ourUiBinder = GWT.create(OrderDialogUiBinder.class);

    @UiField
    Button button;

    public OrderDialog() {
        this.getElement().getStyle().setWidth(100, Style.Unit.PX);
        this.getElement().getStyle().setHeight(100, Style.Unit.PX);
        this.getElement().getStyle().setBackgroundColor("black");
        this.show();
    }
}