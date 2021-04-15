package com.example.application.views.Login;
import java.awt.Image;
import com.example.application.views.Customer.BookingView;
import com.example.application.views.Staff.MovieView;
import com.example.application.views.Staff.ScheduleView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import com.vaadin.flow.component.button.Button;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.vaadin.flow.component.UI.getCurrent;

@Route(value = "")
@PageTitle("Biografen")
@VaadinSessionScope

public class HomePageView extends VerticalLayout {


    private H1 viewTitle;

    Button button = new Button("Personal");
    Button button1 = new Button("Kund");

    public HomePageView(){

        HorizontalLayout layout = new HorizontalLayout();
        VerticalLayout vlay = new VerticalLayout();

        viewTitle = new H1("Javagruppens Biograf");

        add(viewTitle);

        layout.setId("logo");
        layout.setAlignItems(Alignment.CENTER);
        layout.setVerticalComponentAlignment(Alignment.CENTER, viewTitle);


        button.addClickListener( e -> newOverlay());
        button1.addClickListener( e -> getCurrent().navigate(BookingView.class));
        layout.setVerticalComponentAlignment(Alignment.CENTER, button);
        layout.setVerticalComponentAlignment(Alignment.CENTER, button1);
        vlay.setHorizontalComponentAlignment(Alignment.CENTER,button);
        vlay.setHorizontalComponentAlignment(Alignment.CENTER,button1);

        button.setHeight(2, Unit.CM);
        button.setWidth(6,Unit.CM);
        button1.setHeight(2, Unit.CM);
        button1.setWidth(6,Unit.CM);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(button);
        add(button1);

        }


    public void newOverlay()
        {
            LoginOverlay overlay = new LoginOverlay();
            LoginI18n i18n = LoginI18n.createDefault();
            LoginI18n.Header header = new LoginI18n.Header();
            header.setTitle("Vänligen logga in");
            i18n.setHeader(header);
            overlay.setI18n(i18n);
            overlay.setAction("login");
            overlay.setOpened(true);
            overlay.addLoginListener(e -> UI.getCurrent().navigate(MovieView.class));

        }


}

