package com.taxi.client.view.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class Login extends PopupPanel {
    private VerticalPanel contentPanel;

    private VerticalPanel loginPanel;
    private VerticalPanel registrationPanel;

    private Label header;
    private TextBox login;
    private Label loginLabel;
    private PasswordTextBox password;
    private Label passwordLabel;
    private Button loginButton;
    private Button registrationButton;

    public Login() {
        loginPanel = new VerticalPanel();
        header = new Label();
        loginLabel = new Label();
        login = new TextBox();
        passwordLabel = new Label();
        password = new PasswordTextBox();
        loginButton = new Button();
        registrationButton = new Button();
        registrationPanel = new VerticalPanel();
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

        loginPanel.getElement().getStyle().setBackgroundColor("White");
        loginPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 5, Style.Unit.PX);
        loginPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 2.5, Style.Unit.PX);

        registrationPanel.getElement().getStyle().setBackgroundColor("White");
        registrationPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 5, Style.Unit.PX);
        registrationPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 10, Style.Unit.PX);

        createBorder(loginPanel, "#a3a3a3");
        createBorder(registrationPanel, "#a3a3a3");

        header.getElement().getStyle().setColor("#545454");
        header.getElement().getStyle().setFontSize(Window.getClientHeight() / 25, Style.Unit.PX);
        header.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        header.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        header.setText("Your New Taxi");
        loginPanel.add(header);

        loginLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        loginLabel.getElement().getStyle().setColor("#545454");
        loginLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginLabel.setText("Логин");
        loginPanel.add(loginLabel);

        login.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        login.getElement().getStyle().setFontSize(Window.getClientHeight() / 40, Style.Unit.PX);
        login.getElement().getStyle().setColor("#c9c9c9");
        login.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginPanel.add(login);

        passwordLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        passwordLabel.getElement().getStyle().setColor("#545454");
        passwordLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        passwordLabel.setText("Пароль");
        loginPanel.add(passwordLabel);

        password.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        password.getElement().getStyle().setFontSize(Window.getClientHeight() / 40, Style.Unit.PX);
        password.getElement().getStyle().setColor("#c9c9c9");
        password.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginPanel.add(password);

        loginButton.setText("Вход");
        loginButton.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        loginButton.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginButton.getElement().getStyle().setFontSize(Window.getClientHeight() / 30, Style.Unit.PX);

        loginPanel.add(loginButton);

        registrationButton.setText("Регистрация");
        registrationButton.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        registrationButton.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        registrationButton.getElement().getStyle().setFontSize(Window.getClientHeight() / 30, Style.Unit.PX);
        registrationButton.getElement().getStyle().setMarginTop(Window.getClientHeight() / 45, Style.Unit.PX);

        registrationPanel.add(registrationButton);

        contentPanel.getElement().getStyle().setWidth(Window.getClientWidth() / 5, Style.Unit.PX);
        contentPanel.getElement().getStyle().setHeight(Window.getClientHeight() / 1.9, Style.Unit.PX);

        contentPanel.getElement().getStyle().setMarginLeft(0.4 * Window.getClientWidth(), Style.Unit.PX);
        contentPanel.getElement().getStyle().setMarginTop(Window.getClientHeight()  / 6, Style.Unit.PX);

        contentPanel.add(loginPanel);
        contentPanel.add(registrationPanel);

        this.add(contentPanel);
    }

    public Button getLoginButton() {
        return this.loginButton;
    }

    public Button getRegistrationButton() {
        return this.registrationButton;
    }

    public TextBox getLogin() {
        return login;
    }

    public TextBox getPassword() {
        return password;
    }
}