package com.taxi.client.view.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class Registration extends PopupPanel {
    private VerticalPanel contentPanel;

    private VerticalPanel registrationPanel;

    private Label header;
    private TextBox login;
    private Label loginLabel;
    private PasswordTextBox password;
    private Label passwordLabel;
    private ListBox userType;
    private Label userTypeLabel;
    private TextBox carNumber;
    private Label carNumberLabel;
    private Button registrationButton;

    public Registration() {
        registrationPanel = new VerticalPanel();
        header = new Label();
        loginLabel = new Label();
        login = new TextBox();
        passwordLabel = new Label();
        password = new PasswordTextBox();
        userType = new ListBox();
        userTypeLabel = new Label();
        carNumberLabel = new Label();
        carNumber = new TextBox();
        registrationButton = new Button();
        contentPanel = new VerticalPanel();
        create();
    }

    private void createBorder(Widget widget, String color) {
        widget.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        widget.getElement().getStyle().setBorderColor(color);
        widget.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
    }

    public void create() {
        this.getElement().getStyle().setBackgroundColor("#e3e3e3");
        this.getElement().getStyle().setHeight(Window.getClientHeight(), Style.Unit.PX);
        this.getElement().getStyle().setWidth(Window.getClientWidth(), Style.Unit.PX);

        registrationPanel.getElement().getStyle().setBackgroundColor("White");
        registrationPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 5, Style.Unit.PX);
        registrationPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 2.5, Style.Unit.PX);

        createBorder(registrationPanel, "#a3a3a3");

        header.getElement().getStyle().setColor("#545454");
        header.getElement().getStyle().setFontSize(Window.getClientHeight() / 25, Style.Unit.PX);
        header.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        header.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        header.setText("Your New Taxi");
        registrationPanel.add(header);

        loginLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        loginLabel.getElement().getStyle().setColor("#545454");
        loginLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginLabel.setText("Логин");
        registrationPanel.add(loginLabel);

        login.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        login.getElement().getStyle().setFontSize(Window.getClientHeight() / 55, Style.Unit.PX);
        login.getElement().getStyle().setColor("#c9c9c9");
        login.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        registrationPanel.add(login);

        passwordLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        passwordLabel.getElement().getStyle().setColor("#545454");
        passwordLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        passwordLabel.setText("Пароль");
        registrationPanel.add(passwordLabel);

        password.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        password.getElement().getStyle().setFontSize(Window.getClientHeight() / 55, Style.Unit.PX);
        password.getElement().getStyle().setColor("#c9c9c9");
        password.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        registrationPanel.add(password);

        userTypeLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        userTypeLabel.getElement().getStyle().setColor("#545454");
        userTypeLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        userTypeLabel.setText("Тип пользователя");
        registrationPanel.add(userTypeLabel);

        userType.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        userType.getElement().getStyle().setFontSize(Window.getClientHeight() / 55, Style.Unit.PX);
        userType.getElement().getStyle().setColor("#c9c9c9");
        userType.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        userType.addItem("Клиент");
        userType.addItem("Водитель");
        registrationPanel.add(userType);

        carNumberLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        carNumberLabel.getElement().getStyle().setColor("#545454");
        carNumberLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        carNumberLabel.setText("Номер машины");
        carNumberLabel.setVisible(false);
        registrationPanel.add(carNumberLabel);

        carNumber.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        carNumber.getElement().getStyle().setFontSize(Window.getClientHeight() / 55, Style.Unit.PX);
        carNumber.getElement().getStyle().setColor("#c9c9c9");
        carNumber.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        carNumber.setVisible(false);
        registrationPanel.add(carNumber);

        registrationButton.setText("Зарегистрироваться");
        registrationButton.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        registrationButton.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        registrationButton.getElement().getStyle().setFontSize(Window.getClientHeight() / 32, Style.Unit.PX);

        registrationPanel.add(registrationButton);

        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 5, Style.Unit.PX);
        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 1.9, Style.Unit.PX);

        contentPanel.getElement().getStyle().setMarginLeft(0.4 * Window.getClientWidth(), Style.Unit.PX);
        contentPanel.getElement().getStyle().setMarginTop(Window.getClientHeight()  / 6, Style.Unit.PX);

        contentPanel.add(registrationPanel);

        this.add(contentPanel);
    }

    public Label getCarNumberLabel() {
        return this.carNumberLabel;
    }

    public TextBox getCarNumber() {
        return this.carNumber;
    }

    public Button getRegistrationButton() {
        return this.registrationButton;
    }

    public ListBox getUserType() {
        return this.userType;
    }
}
