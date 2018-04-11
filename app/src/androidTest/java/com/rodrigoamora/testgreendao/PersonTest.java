package com.rodrigoamora.testgreendao;

import android.test.AndroidTestCase;

import com.rodrigoamora.testgreendao.entity.Person;
import com.rodrigoamora.testgreendao.service.PersonService;

import org.junit.Test;

import java.util.List;

public class PersonTest extends AndroidTestCase {

    public void testeSavePersonAndFindByName() throws Exception {
        String namePerson = "Person 1";

        Person person = new Person();
        person.setEmail("person1@email.com");
        person.setName(namePerson);
        person.setPhone("788787878");

        PersonService.savePerson(getContext(), person);

        assertEquals(person.getName(), PersonService.findByName(getContext(), namePerson).getName());
    }

    @Test
    public void testegetAllPeople() throws Exception {
        List<Person> people = PersonService.getAllPeolplo(getContext());
        assertTrue(people.isEmpty());
    }

}
