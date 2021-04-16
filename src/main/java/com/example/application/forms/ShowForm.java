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

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

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

    /**
     * @param showService
     * @param movieService
     * @param showView
     * The form for allowing editing/adding/deleting of shows in the showView.
     */
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

    /**
     * Sets up the visuals of the buttons.
     */
    private void configureButtons()
    {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clear.addThemeVariants(ButtonVariant.LUMO_ERROR);
    }

    /**
     * Sets up the click functionality for buttons
     */
    public void configureButtonListener()
    {
        add.addClickListener(event -> addShow());
        // save.addClickListener(event -> saveShow());
        clear.addClickListener(event -> clearShow());
        cancel.addClickListener(event -> closeForm());
    }

    private void closeForm()
    {
        this.setVisible(false);
    }

    /**
     * @param state
     * @param form
     * Depending on the ENUM state value(adding, editing, none) ->
     * Configure the form to allow either adding, editing shows
     * or hide the form if state.none.
     */
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

    /**
     * Bind form fields to object variables
     */
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
        loungePicker.setEnabled(false);
    }

    public void deleteShow()
    {
       showService.deleteShow(showView.getSelection().getId());
       showView.populateGrid();
    }

    /**
     * Calls a stored procedure in the database which takes in parameters, in this case the form field values.
     */
    public void addShow()
    {
        showService.addToShow(moviePicker.getValue().getId(), loungePicker.getValue().getId(), timePicker.getValue().toString(), datePicker.getValue());
        showView.populateGrid();
    }

    /**
     * Fills comboboxes with data and change what is displayed in the combobox
     */
    private void configureComboBoxes()
    {
        timePicker.setLocale(Locale.GERMAN);

        moviePicker.setItemLabelGenerator(Movie::getTitel);
        moviePicker.setItems(movieService.findAll());

        cinemaPicker.setItemLabelGenerator(Cinema::getNamn);
        cinemaPicker.setItems(showService.findCinemas());

        // Everytime you select a new cinema, set up the loungePicker field with ->
        // -> lounges related to the selected cinema
        cinemaPicker.addValueChangeListener(event ->
        {
            if (event.getValue() == null)
            {
                System.out.println("Error");
            }
            else
            {
                configureLoungePicker();
            }
        });
        loungePicker.setEnabled(false);

    }

    private void configureLoungePicker()
    {
        loungePicker.setEnabled(true);
        loungePicker.setItemLabelGenerator(Lounge::toString);
        loungePicker.setItems(showService.findLoungeForCinema(cinemaPicker.getValue().getId()));
    }

}
