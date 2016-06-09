package by.persons.database.dao;

import by.persons.database.SortMode;
import by.persons.entities.Person;

import java.util.List;

public interface PersonDao {
    void addPersons(List<Person> persons);

    void addPerson(Person person);

    List<Person> obtainPersons();

    List<Person> obtainPersons(SortMode sortMode, int limit, int offset);

    void updatePersons(List<Person> persons);
}
