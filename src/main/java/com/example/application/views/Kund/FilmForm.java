package com.example.application.views.Kund;

import com.example.application.Backend.Film.Film;
import com.example.application.Backend.Film.FilmService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.awt.*;

public class FilmForm extends FormLayout {
    FilmService filmService;
    FilmView filmView;
    TextField titel = new TextField("Titel");
    TextField sprak = new TextField("Språk");
    TextField aldersgrans = new TextField("Åldersgräns");
    TextField genre = new TextField("Genre");
    IntegerField langd = new IntegerField("Längd");
    private Binder<Film> binder = new Binder<>(Film.class);

    //ComboBox<Bokning.Betalning> betalning = new ComboBox<>("Betalningsalternativ");


    Button add = new Button("Lägg till film");
    Button cancel = new Button("Avbryt");
    Button delete = new Button("Ta bort");
    Film film = new Film("", "", "", "", 0);


    public FilmForm(FilmService filmService, FilmView filmView)
    {
        this.filmService = filmService;
        this.filmView = filmView;
        binder.forField(titel).bind(Film::getTitel,Film::setTitel);
        binder.forField(sprak).bind(Film::getSprak,Film::setSprak);
        binder.forField(aldersgrans).bind(Film::getAldergrans,Film::setAldergrans);
        binder.forField(genre).bind(Film::getGenre, Film::setGenre);
        binder.forField(langd).bind(Film::getLangd, Film::setLangd);
        binder.setBean(film);
        add.addClickListener(event -> addAndUpdate());
        delete.addClickListener(event -> deleteAndUpdate());
        addClassName("boknings-form");
        add(
                titel,
                sprak,
                aldersgrans,
                genre,
                langd,
                createButtonLayout()
        );


    }
    private void addAndUpdate()
    {
        filmService.addFilm(film);
        filmView.updateList();
    }
    private void deleteAndUpdate()
    {
        filmService.delete(filmView.selection.getValue().getTitel());
        filmView.updateList();
    }


    private HorizontalLayout createButtonLayout()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(add, delete, cancel);
    }

}
