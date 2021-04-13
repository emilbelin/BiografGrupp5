package com.example.application.views.Kund;

import com.example.application.views.main.HomePage;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Controller;


@Route(value = "/film", layout = HomePage.class)
@PageTitle("Välj en film")
@CssImport("./views/helloworld/hello-world-view.css")
@Controller

public class FilmView extends Div {

    public FilmView(){
        addClassName("film-view");
        add(new Text("HÄR SKA ALLT KUNDRELATERAT SKE"));


    }

}


