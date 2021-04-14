package com.example.application.forms;

import com.example.application.Backend.model.Cinema;
import com.example.application.Backend.model.Lounge;
import com.example.application.Backend.model.Movie;
import com.example.application.Backend.model.ShowObject;
import com.example.application.Backend.service.MovieService;
import com.example.application.Backend.service.ShowService;
import com.example.application.views.Staff.ShowsView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDate;

public class ShowForm extends FormLayout {

    protected Button add = new Button("Lägg till");
    protected Button clear = new Button("Rensa Fält");
    protected Button cancel = new Button("Avbryt");
    protected Button save = new Button("Spara");

    protected Binder<ShowObject> binder = new Binder<>(ShowObject.class);

    protected ComboBox<Cinema> cinemaPicker = new ComboBox<>("Biograf");
    protected ComboBox<Lounge> loungePicker = new ComboBox<>("Salong");
    protected ComboBox<Movie> moviePicker = new ComboBox<>("Film");
    protected DatePicker datePicker = new DatePicker("Datum");
    protected TimePicker timePicker = new TimePicker("Tid");
    protected ShowObject show = new ShowObject(null,null,null,null,null);

    protected HorizontalLayout buttonLayout = new HorizontalLayout();

    protected ShowService showService;
    protected MovieService movieService;
    protected ShowsView showView;
    public ShowForm(ShowService showService, MovieService movieService, ShowsView showView)
    {
        this.showView = showView;
        this.showService = showService;
        this.movieService = movieService;
        configureBinder();
        configureButtons();
        configureButtonListener();
        configureComboBoxes();

        add(moviePicker, cinemaPicker, loungePicker, timePicker, datePicker, buttonLayout);

    }
    private void configureButtons()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clear.addThemeVariants(ButtonVariant.LUMO_ERROR);
    }
    public void configureButtonListener()
    {
        //add.addClickListener(event -> addShow());
        // save.addClickListener(event -> saveShow());
        clear.addClickListener(event -> clearShow());
        cancel.addClickListener(event -> closeForm());
    }
    private void closeForm()
    {
        this.setVisible(false);
    }
    public void configureForm(formState state, ShowForm form)
    {
        form.remove(moviePicker, cinemaPicker, loungePicker, timePicker, datePicker, buttonLayout);
        if(state == formState.editing)
        {
            buttonLayout = buttonsEditing();
            // schedule = scheduleView.getSelection();
            binder.setBean(show);
            form.add(
                    moviePicker, cinemaPicker, loungePicker, timePicker, datePicker, buttonLayout
            );
        }
        else if(state == formState.adding)
        {
            clearShow();
            buttonLayout = buttonsAdding();
            form.add(
                    moviePicker, cinemaPicker, loungePicker, timePicker, datePicker, buttonLayout
            );
        }
        else
        {
            form.setVisible(false);
        }

    }
    private HorizontalLayout buttonsAdding()
    {
        return new HorizontalLayout(add, clear, cancel);
    }
    private HorizontalLayout buttonsEditing()
    {
        return new HorizontalLayout(save, clear);
    }
    public void configureBinder()
    {
        binder.forField(moviePicker).bind(ShowObject::getMovie, ShowObject::setMovie);
        binder.forField(cinemaPicker).bind(ShowObject::getCinema, ShowObject::setCinema);
        binder.forField(loungePicker).bind(ShowObject::getLounge, ShowObject::setLounge);
        binder.forField(timePicker).bind(ShowObject::getTime, ShowObject::setTime);
        binder.forField(datePicker).bind(ShowObject::getDate, ShowObject::setDate);
    }
    public void clearShow()
    {
        show = new ShowObject(null,null,null,null,null);
        binder.setBean(show);
    }
    public void deleteShow()
    {
       showService.deleteShow(showView.getSelection().getId());
       showView.populateGrid();
    }
    public void addShow()
    {
       // showService.addToShow();
        showService.addToShow();



        showView.populateGrid();
    }
    private void configureComboBoxes()
    {
        moviePicker.setItemLabelGenerator(Movie::getTitel);
        moviePicker.setItems(movieService.findAll());

        cinemaPicker.setItemLabelGenerator(Cinema::getNamn);
        cinemaPicker.setItems(showService.findCinemas());

        loungePicker.setItemLabelGenerator(Lounge::toString);
        loungePicker.setItems(showService.findLounges());
    }

}
