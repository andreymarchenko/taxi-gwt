package com.taxi.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.EventBus;
import com.taxi.client.presenter.Presenter;
import com.taxi.client.view.dialog.*;
import com.taxi.client.view.map.Map;
import com.taxi.shared.dto.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;

public class View extends Composite {

    interface ViewUiBinder extends UiBinder<Widget, View> {
    }

    private static ViewUiBinder ourUiBinder = GWT.create(ViewUiBinder.class);

    @UiField
    HTMLPanel root;

    @UiField
    HorizontalPanel header;

    @UiField
    HorizontalPanel contentPanel;

    private Map map;
    private Presenter presenter;
    private EventBus eventBus;
    private OrderDialog orderDialog;
    private NumberDialog numberDialog;
    private Login login;
    private Registration registration;
    private Boolean isLogged;
    private Storage stockStore;
    private Button logOutButton;

    @Inject
    public View(EventBus eventBus) {
        this.eventBus = eventBus;
        login = new Login();
        numberDialog = new NumberDialog();
        stockStore = Storage.getLocalStorageIfSupported();
        logOutButton = new Button("Log out");
        orderDialog = new OrderDialog();
        bind();
    }

    private void loadMapApi() {
        boolean sensor = true;

        ArrayList<LoadApi.LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
        loadLibraries.add(LoadApi.LoadLibrary.ADSENSE);
        loadLibraries.add(LoadApi.LoadLibrary.DRAWING);
        loadLibraries.add(LoadApi.LoadLibrary.GEOMETRY);
        loadLibraries.add(LoadApi.LoadLibrary.PANORAMIO);
        loadLibraries.add(LoadApi.LoadLibrary.PLACES);
        loadLibraries.add(LoadApi.LoadLibrary.WEATHER);
        loadLibraries.add(LoadApi.LoadLibrary.VISUALIZATION);

        Runnable onLoad = () -> {
            map = new Map();
            map.setPresenter(presenter);
            contentPanel.add(map);
            orderDialog.show();
            map.setOrderDialog(orderDialog);
            map.bind();
        };

        LoadApi.go(onLoad, loadLibraries, sensor);
    }

    public void createUi() {
        initWidget(ourUiBinder.createAndBindUi(this));
        root.getElement().getStyle().setMarginLeft(-8, Style.Unit.PX);
        root.getElement().getStyle().setMarginTop(-8, Style.Unit.PX);
        root.getElement().getStyle().setMarginBottom(8, Style.Unit.PX);
        root.getElement().getStyle().setMarginRight(-8, Style.Unit.PX);

        header.getElement().getStyle().setWidth(Window.getClientWidth(), Style.Unit.PX);
        header.getElement().getStyle().setHeight(Window.getClientHeight() / 15, Style.Unit.PX);

        header.add(logOutButton);

        if (stockStore.getItem("login") != null) {
            login.hide();
            loadMapApi();
            numberDialog.show();
        }
        else {
            login.show();
        }
        RootPanel.get("root").add(this);
    }

    private void bind() {
       login.getLoginButton().addClickHandler(event -> {
            if (login.getLogin().getText() != "" && login.getPassword().getText() != "") {
                presenter.login(
                        new LoginDto(
                        login.getLogin().getText(),
                        login.getPassword().getText()));
                if (isLogged) {
                    stockStore.setItem("login", login.getLogin().getText());
                    stockStore.setItem("password", login.getPassword().getText());
                    login.hide();
                    loadMapApi();
                    numberDialog = new NumberDialog();
                    numberDialog.show();
                }
            }
        });

        login.getRegistrationButton().addClickHandler(event -> {
            login.hide();
            registration = new Registration();
            registration.show();
        });

        orderDialog.getMakeOrderButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (orderDialog.isMakeOrderButtonClickable()) {
                    map.getOrderInfoDialog().show();
                    map.getDistance();
                    Date currentDate = new Date();
                    ClientDto clientDto = presenter.getActiveClient();
                    presenter.createOrder(
                            new OrderDto(
                                    0,
                                    new DriverDto (2, "ALEXANDER", "1234", "Good", "TOYOTA", StatusDto.FREE),
                                    new ClientDto(1, "test", "test", ""),
                                    map.getOrigin().getToString(),
                                    map.getDestination().getToString(),
                                    currentDate.toString(),
                                    150.00,
                                    "CARD"
                            ));
                }
            }
        });

        logOutButton.addClickHandler(event -> {
            stockStore.clear();
            isLogged = false;
            login.show();
        });

/*        registration.getUserType().addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                boolean isVisible = registration.getUserType().getSelectedItemText().equals("Водитель");
                registration.getCarNumberLabel().setVisible(isVisible);
                registration.getCarNumber().setVisible(isVisible);
            }
        });

        registration.getRegistrationButton().addClickHandler(event -> {
            if (login.getLogin().getText() != "" && login.getPassword().getText() != "") {
                presenter.login(
                        new LoginDto(
                                login.getLogin().getText(),
                                login.getPassword().getText()));
                login.hide();
                loadMapApi();
                orderDialog = new OrderDialog();
                orderDialog.show();
                numberDialog = new NumberDialog();
                numberDialog.show();
            }
        });*/
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }
}