package com.taxi.client.view.dialog;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class Login extends PopupPanel {
    private VerticalPanel contentPanel;

    private VerticalPanel loginPanel;
    private HorizontalPanel registrationPanel;

    private Label header;
    private TextBox login;
    private Label loginLabel;
    private TextBox password;
    private Label passwordLabel;
    private Button loginButton;
    private Label registration;

    public Login() {
        loginPanel = new VerticalPanel();
        registrationPanel = new HorizontalPanel();
        registration = new Label();
        header = new Label();
        loginLabel = new Label();
        login = new TextBox();
        passwordLabel = new Label();
        password = new TextBox();
        loginButton = new Button();
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

        registration.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        registration.getElement().getStyle().setColor("#545454");
        registration.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        registration.getElement().getStyle().setMarginTop(Window.getClientHeight() / 30, Style.Unit.PX);
        registration.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 9, Style.Unit.PX);
        registration.setText("Create an account");

        registrationPanel.add(registration);

        createBorder(registrationPanel, "#a3a3a3");
        createBorder(loginPanel, "#a3a3a3");

        header.getElement().getStyle().setColor("#545454");
        header.getElement().getStyle().setFontSize(Window.getClientHeight() / 25, Style.Unit.PX);
        header.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        header.getElement().getStyle().setMarginTop(Window.getClientHeight() / 50, Style.Unit.PX);
        header.setText("Login");
        loginPanel.add(header);

        loginLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        loginLabel.getElement().getStyle().setColor("#545454");
        loginLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginLabel.setText("Username");
        loginPanel.add(loginLabel);

        login.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        login.getElement().getStyle().setFontSize(Window.getClientHeight() / 55, Style.Unit.PX);
        login.getElement().getStyle().setColor("#c9c9c9");
        login.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginPanel.add(login);

        passwordLabel.getElement().getStyle().setFontSize(Window.getClientHeight() / 50, Style.Unit.PX);
        passwordLabel.getElement().getStyle().setColor("#545454");
        passwordLabel.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        passwordLabel.setText("Password");
        loginPanel.add(passwordLabel);

        password.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        password.getElement().getStyle().setFontSize(Window.getClientHeight() / 55, Style.Unit.PX);
        password.getElement().getStyle().setColor("#c9c9c9");
        password.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginPanel.add(password);

        loginButton.setText("Sign in");
        loginButton.getElement().getStyle().setWidth(Window.getClientWidth() / 5.6, Style.Unit.PX);
        loginButton.getElement().getStyle().setMarginLeft(Window.getClientHeight() / 50, Style.Unit.PX);
        loginButton.getElement().getStyle().setFontSize(Window.getClientHeight() / 22, Style.Unit.PX);

        loginPanel.add(loginButton);

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

    public Label getRegistration() {
        return this.registration;
    }
}