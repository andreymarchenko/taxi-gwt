package com.taxi.client.view.map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.adsense.AdFormat;
import com.google.gwt.maps.client.adsense.AdUnitOptions;
import com.google.gwt.maps.client.adsense.AdUnitWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.controls.ControlPosition;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapEvent;
import com.google.gwt.maps.client.events.channelnumber.ChannelNumberChangeMapHandler;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.dblclick.DblClickMapEvent;
import com.google.gwt.maps.client.events.dblclick.DblClickMapHandler;
import com.google.gwt.maps.client.events.format.FormatChangeMapEvent;
import com.google.gwt.maps.client.events.format.FormatChangeMapHandler;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapEvent;
import com.google.gwt.maps.client.events.mapchange.MapChangeMapHandler;
import com.google.gwt.maps.client.events.position.PositionChangeMapEvent;
import com.google.gwt.maps.client.events.position.PositionChangeMapHandler;
import com.google.gwt.maps.client.overlays.Animation;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.taxi.client.presenter.Presenter;
import com.taxi.client.service.EndPoint;
import com.taxi.client.view.dialog.OrderDialog;
import com.taxi.shared.Login;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

public class Map extends Composite {

    private VerticalPanel panel;
    private List<Marker> markers;
    private Presenter presenter;
    private MapWidget mapWidget;

    private final EndPoint endPoint = GWT.create(EndPoint.class);

    public Map() {
        this.panel = new VerticalPanel();
        this.markers = new ArrayList<>();
        initWidget(panel);
        drawMap();
        drawMapAds();
        bind();
    }

    public void bind() {
        mapWidget.addDblClickHandler(new DblClickMapHandler() {
            @Override
            public void onEvent(final DblClickMapEvent dblClickMapEvent) {
                endPoint.login(new MethodCallback() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Method method, Object o) {
                        GWT.log("Привет");
                    }
                });
            }
        });
    }

    private MapOptions createDefaultMapOptions() {
        MapOptions options = MapOptions.newInstance();
        options.setZoom(11);
        options.setDisableDoubleClickZoom(true);
        options.setCenter(LatLng.newInstance(56.1937, 44.0027));
        options.setMapTypeId(MapTypeId.ROADMAP);
        return options;
    }

    private void drawMap() {
        mapWidget = new MapWidget(createDefaultMapOptions());
        panel.add(mapWidget);
        mapWidget.setSize(Double.toString(Window.getClientWidth()), Double.toString(14.0/15 * Window.getClientHeight()-8));
    }

    private void drawMapAds() {
        AdUnitOptions options = AdUnitOptions.newInstance();
        options.setFormat(AdFormat.HALF_BANNER);
        options.setPosition(ControlPosition.RIGHT_CENTER);
        options.setMap(mapWidget);
        options.setPublisherId("pub-0032065764310410");
        options.setChannelNumber("4000893900");

        AdUnitWidget adUnit = new AdUnitWidget(options);

        adUnit.addChannelNumberChangeHandler(new ChannelNumberChangeMapHandler() {
            @Override
            public void onEvent(ChannelNumberChangeMapEvent event) {
            }
        });

        adUnit.addFormatChangeHandler(new FormatChangeMapHandler() {
            @Override
            public void onEvent(FormatChangeMapEvent event) {
            }
        });

        adUnit.addMapChangeHandler(new MapChangeMapHandler() {
            @Override
            public void onEvent(MapChangeMapEvent event) {
            }
        });

        adUnit.addPositionChangeHandler(new PositionChangeMapHandler() {
            @Override
            public void onEvent(PositionChangeMapEvent event) {
            }
        });
    }

    public void launchApp(/*List of markers*/) {
        //It's necessary to draw all markers
    }

    private void drawMarkerWithDropAnimation(double latitude, double longitude) {
        MarkerOptions options = MarkerOptions.newInstance();
        options.setPosition(LatLng.newInstance(latitude, longitude));

        Marker marker = Marker.newInstance(options);
        marker.setMap(mapWidget);
        markers.add(marker);

        marker.addClickHandler(new ClickMapHandler() {
            @Override
            public void onEvent(ClickMapEvent event) {

            }
        });
    }

    public void stopAnimation() {
        for (Marker m : markers) {
            m.setAnimation(Animation.STOPANIMATION);
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void clearMap() {
        for (int i = 0; i < markers.size(); i++) {
            markers.get(i).clear();
        }
    }

    public MapWidget getMapWidget() {
        return this.mapWidget;
    }
}
