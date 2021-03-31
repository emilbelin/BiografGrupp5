package com.example.application.views.Kund;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.router.Route;

import javax.persistence.Entity;


@Route(value = "/film", layout = MainView.class)
@PageTitle("Välj en film")
@CssImport("./views/helloworld/hello-world-view.css")
@Entity
public class FilmView extends HorizontalLayout {

    public FilmView(){
        addClassName("film-view");
        add(new Text("HÄR SKA ALLT KUNDRELATERAT SKE"));


    }


}


