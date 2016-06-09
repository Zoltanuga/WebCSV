package by.persons.service;

import by.persons.database.dao.PersonDao;
import by.persons.database.dao.impl.PersonDaoImpl;
import by.persons.database.SortMode;
import by.persons.entities.Person;

import java.util.List;

public class PersonService {
    private PersonDao personDao = new PersonDaoImpl();

    public void addPersons(List<Person> persons) {
        personDao.addPersons(persons);
    }

    public List<Person> obtainPersons() {
        return personDao.obtainPersons();
    }

    public List<Person> obtainPersons(SortMode sortMode, int limit, int offset) {
        return personDao.obtainPersons(sortMode, limit, offset);
    }

    public void updatePersons(List<Person> persons) {
        personDao.updatePersons(persons);
    }
}
