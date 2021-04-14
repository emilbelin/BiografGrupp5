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

public class MovieForm extends FormLayout {
    formState state = formState.none;
    MovieService movieService;
    MovieView movieView;

    //Form Fields
    TextField titel = new TextField("Titel");
    TextField sprak = new TextField("Språk");
    TextField aldersgrans = new TextField("Åldersgräns");
    TextField genre = new TextField("Genre");
    IntegerField langd = new IntegerField("Längd(Minuter)");

    Binder<Movie> binder = new Binder<>(Movie.class);

    //Buttons
    Button add = new Button("Lägg till ny film");
    Button delete = new Button("Ta bort");
    Button edit = new Button("Redigera");
    Button save = new Button("Spara");
    Button clear = new Button("Rensa Formulär");
    Button cancel = new Button("Avbryt");
    Movie movie = new Movie(0,"", "", "", "", 0);

    //Layouts
    HorizontalLayout buttonLayout = new HorizontalLayout();

    public MovieForm(MovieService movieService, MovieView movieView)
    {
        this.movieService = movieService;
        this.movieView = movieView;

        configureBinder();

        configureButtonListener();

        createButtonLayout();


    }
    private void addAndUpdate()
    {
        movieService.addMovie(movie);
        binder.setBean(movie);
        movieView.updateList();
    }
    public void deleteAndUpdate()
    {
        movieService.delete(movieView.getSelection().getTitel());
        movieView.updateList();
    }
    public void editMovie()
    {
        movie = movieView.getSelection();
        binder.setBean(movie);
    }
    private void saveMovie()
    {
        movieService.saveMovie(movie);
        binder.setBean(movie);
        movieView.updateList();
    }
    private void clearMovie()
    {
        movie =  new Movie(0,"","","","",0);
        binder.setBean(movie);
    }


    private void createButtonLayout()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        edit.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        clear.addThemeVariants(ButtonVariant.LUMO_ERROR);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    }
    private HorizontalLayout buttonsAdding()
    {
        return new HorizontalLayout(add, clear, cancel);
    }
    private HorizontalLayout buttonsEditing()
    {
        return new HorizontalLayout(save,clear);
    }

    private void closeForm()
    {
        this.setVisible(false);
    }

    public void configureForm(formState state, MovieForm form)
    {
        form.remove(titel,sprak,aldersgrans,genre,langd,buttonLayout);
        if(state == formState.editing)
        {
            buttonLayout = buttonsEditing();
            movie = movieView.getSelection();
            binder.setBean(movie);
            form.add(
                    titel,
                    sprak,
                    aldersgrans,
                    genre,
                    langd,
                    buttonLayout
            );
        }
        else if(state == formState.adding)
        {
            clearMovie();
            buttonLayout = buttonsAdding();
            form.add(
                titel,
                sprak,
                aldersgrans,
                genre,
                langd,
                buttonLayout
             );
        }
        else
        {
            form.setVisible(false);
        }


    }

    public void configureBinder()
    {
        binder.forField(titel).bind(Movie::getTitel, Movie::setTitel);
        binder.forField(sprak).bind(Movie::getSprak, Movie::setSprak);
        binder.forField(aldersgrans).bind(Movie::getAldergrans, Movie::setAldergrans);
        binder.forField(genre).bind(Movie::getGenre, Movie::setGenre);
        binder.forField(langd).bind(Movie::getLangd, Movie::setLangd);
        binder.setBean(movie);
    }

    public void configureButtonListener()
    {
        add.addClickListener(event -> addAndUpdate());
        delete.addClickListener(event -> deleteAndUpdate());
        edit.addClickListener(event -> editMovie());
        save.addClickListener(event -> saveMovie());
        clear.addClickListener(event -> clearMovie());
        cancel.addClickListener(event -> closeForm());
    }

    public Movie getMovie() {
        return movie;
    }
}

