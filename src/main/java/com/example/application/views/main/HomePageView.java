package com.example.application.views.main;


import com.example.application.views.Personal.PersonalView;
import com.example.application.views.Personal.FilmView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("Biografen")
public class HomePageView extends VerticalLayout {


    private H1 viewTitle;

    Button button = new Button("Personal");
    Button button1 = new Button("Kund");
    Image image = new Image("images/javabio.png", "My App Logo");

    public HomePageView (){

        HorizontalLayout layout = new HorizontalLayout();
        VerticalLayout vlay = new VerticalLayout();

        viewTitle = new H1("Javagruppens Biograf");
        add(viewTitle);
        add(image);

        layout.setId("logo");
        layout.setAlignItems(Alignment.CENTER);
        layout.setVerticalComponentAlignment(Alignment.CENTER, viewTitle);
        layout.setVerticalComponentAlignment(Alignment.CENTER, image);



        button.addClickListener( e -> newOverlay());
        button1.addClickListener( e -> UI.getCurrent().navigate(FilmView.class));
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
            header.setTitle("Employee Login");
            i18n.setHeader(header);
            overlay.setI18n(i18n);
            overlay.setAction("login");
            overlay.setOpened(true);
            overlay.addLoginListener(e -> {
                overlay.close();
                UI.getCurrent().navigate(PersonalView.class);
            });

            }
}


