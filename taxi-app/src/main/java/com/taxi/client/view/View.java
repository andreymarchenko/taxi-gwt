package com.taxi.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.EventBus;
import com.taxi.client.presenter.Presenter;
import com.taxi.client.view.dialog.NumberDialog;
import com.taxi.client.view.dialog.OrderDialog;
import com.taxi.client.view.map.Map;
import com.vaadin.polymer.paper.widget.PaperButton;

import javax.inject.Inject;
import java.util.ArrayList;

public class View extends Composite {

    interface ViewUiBinder extends UiBinder<Widget, View> {
    }

    private static ViewUiBinder ourUiBinder = GWT.create(ViewUiBinder.class);

    @UiField
    HTMLPanel root;

    @UiField
    HorizontalPanel header;

    @UiField
    HorizontalPanel mapPanel;

    private Map map;
    private Presenter presenter;
    private EventBus eventBus;
    private OrderDialog orderDialog;
    private NumberDialog numberDialog;

/*    void createHeader() {
        header = new HorizontalPanel();
        header.getElement().getStyle().setBackgroundColor("Blue");
        header.getElement().getStyle().setHeight(100, Style.Unit.PX);
        header.getElement().getStyle().setWidth(Window.getClientWidth(), Style.Unit.PX);
        root.add(header);
    }*/

    @Inject
    public View(EventBus eventBus) {
        this.eventBus = eventBus;
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

        Runnable onLoad = new Runnable() {
            @Override
            public void run() {
                map = new Map();
                map.setPresenter(presenter);
                //createHeader();
                mapPanel.add(map);
                prepareMap();
            }
        };
        LoadApi.go(onLoad, loadLibraries, sensor);
    }

    public void createUi() {
        initWidget(ourUiBinder.createAndBindUi(this));
        loadMapApi();
        root.getElement().getStyle().setMarginLeft(-8, Style.Unit.PX);
        root.getElement().getStyle().setMarginTop(-8, Style.Unit.PX);
        root.getElement().getStyle().setMarginBottom(8, Style.Unit.PX);
        root.getElement().getStyle().setMarginRight(-8, Style.Unit.PX);

        header.getElement().getStyle().setWidth(Window.getClientWidth(), Style.Unit.PX);
        header.getElement().getStyle().setHeight(Window.getClientHeight() / 15, Style.Unit.PX);
        orderDialog = new OrderDialog();
        orderDialog.show();
        numberDialog = new NumberDialog();
        numberDialog.show();

        RootPanel.get("root").add(this);
    }

    private void prepareMap() {
        if (map != null) {
            map.getMapWidget().addClickHandler(new ClickMapHandler() {
                @Override
                public void onEvent(ClickMapEvent clickMapEvent) {

                }
            });
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}