package com.example.application.forms;

import com.example.application.Backend.model.Movie;
import com.example.application.Backend.service.MovieService;
import com.example.application.views.Staff.MovieView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class FilmForm extends FormLayout {
    MovieService movieService;
    MovieView movieView;
    TextField titel = new TextField("Titel");
    TextField sprak = new TextField("Språk");
    TextField aldersgrans = new TextField("Åldersgräns");
    TextField genre = new TextField("Genre");
    IntegerField langd = new IntegerField("Längd(Minuter)");
    private Binder<Movie> binder = new Binder<>(Movie.class);

    //ComboBox<Bokning.Betalning> betalning = new ComboBox<>("Betalningsalternativ");


    Button add = new Button("Lägg till Film");
    Button delete = new Button("Ta bort");
    Button edit = new Button("Redigera");
    Button save = new Button("Spara");
    Movie movie = new Movie("", "", "", "", 0);


    public FilmForm(MovieService movieService, MovieView movieView)
    {
        this.movieService = movieService;
        this.movieView = movieView;
        binder.forField(titel).bind(Movie::getTitel, Movie::setTitel);
        binder.forField(sprak).bind(Movie::getSprak, Movie::setSprak);
        binder.forField(aldersgrans).bind(Movie::getAldergrans, Movie::setAldergrans);
        binder.forField(genre).bind(Movie::getGenre, Movie::setGenre);
        binder.forField(langd).bind(Movie::getLangd, Movie::setLangd);
        binder.setBean(movie);
        add.addClickListener(event -> addAndUpdate());
        delete.addClickListener(event -> deleteAndUpdate());
        edit.addClickListener(event -> editMovie());
        save.addClickListener(event -> saveMovie());
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
        movieService.addMovie(movie);
        movieView.updateList();
    }
    private void deleteAndUpdate()
    {
        movieService.delete(movieView.getSelection().getTitel());
        movieView.updateList();
    }
    private void editMovie()
    {
        movie = movieView.getSelection();
        binder.setBean(movie);
    }
    private void saveMovie()
    {
        movieService.saveMovie(movie);
        movieView.updateList();
    }


    private HorizontalLayout createButtonLayout()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        edit.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        save.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        add.addClickShortcut(Key.ENTER);
        delete.addClickShortcut(Key.DELETE);

        return new HorizontalLayout(add, edit, save, delete);
    }

}
