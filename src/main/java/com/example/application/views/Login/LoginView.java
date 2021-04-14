package com.example.application.views.Login;

import com.example.application.views.Customer.BookingView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.vaadin.flow.component.UI.getCurrent;

@Route("login")
@PageTitle("Personal - Javabiografen")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm loginForm = new LoginForm();
    HorizontalLayout layout = new HorizontalLayout();
    Button button = new Button("Tillbaka");

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("Fel användarnamn eller lösenord"));
        add(button);
        button.setHeight(2, Unit.CM);
        button.setWidth(6,Unit.CM);
        button.addClickListener( e -> getCurrent().navigate(HomePageView.class));
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            loginForm.setEnabled(true);
        }

    }
}
