package com.example.application.forms;

import com.example.application.Backend.model.Movie;
import com.example.application.Backend.service.MovieService;
import com.example.application.forms.MovieForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ScheduleFormTest {
    private List<Movie> movies;
    private Movie movie1;
    private Movie movie2;



    @Before
    public void setupData() {
        movies = new ArrayList<>();
        movie1 = new Movie("Vaadin Ltd", "Svenska", "16", "Action", 180);
        movie2 = new Movie("Harry Potter", "English", "16", "Äventyr", 200);
        movies.add(movie1);
        movies.add(movie2);

    }


        @Test
        public void formFieldsPopulated() {
            MovieForm form = new MovieForm("movieService","movieView");


            Assert.assertEquals("Marc", form.firstName.getValue());
            Assert.assertEquals("Usher", form.lastName.getValue());
            Assert.assertEquals("marc@usher.com", form.email.getValue());
            Assert.assertEquals(movie2,form.company.getValue());
            Assert.assertEquals(Contact.Status.NotContacted, form.status.getValue());
        }

        @Test
        public void saveEventHasCorrectValues() {
            ContactForm form = new ContactForm(companies);
            Contact contact = new Contact();
            form.setContact(contact);
        }
        @Test
        public void saveEventHasCorrectValues() {
            ContactForm form = new ContactForm(companies);
            Contact contact = new Contact();
            form.setContact(contact);
        }
    AtomicReference<Contact> savedContactRef = new AtomicReference<>(null);
     form.addListener(ContactForm.SaveEvent.class, e -> {
        savedContactRef.set(e.getContact());
    });
      form.save.click();
      Contact savedContact = savedContactRef.get();

Assert.assertEquals("John", savedContact.getFirstName());
Assert.assertEquals("Doe", savedContact.getLastName());
Assert.assertEquals("john@doe.com", savedContact.getEmail());
Assert.assertEquals(company1, savedContact.getCompany());
Assert.assertEquals(Contact.Status.Customer, savedContact.getStatus());

}


