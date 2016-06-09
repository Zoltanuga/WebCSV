package by.persons.database.dao.impl;

import by.persons.database.ConnectionPool;
import by.persons.database.SortMode;
import by.persons.database.dao.PersonDao;
import by.persons.entities.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PersonDaoImpl implements PersonDao {
    private ResourceBundle queries = ResourceBundle.getBundle("by.persons.resources.DatabaseResources");

    @Override
    public void addPersons(List<Person> persons) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement prStatement = connection.prepareStatement(queries.getString("sqlInsertPerson"));
            List<String> loginList = obtainLoginList();
            for (Person person : persons) {
                initializePrepareStatement(person, prStatement);
                if (loginList.contains(person.getLogin())) {
                    continue;
                }
                prStatement.executeUpdate();
                loginList.add(person.getLogin());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<String> obtainLoginList() {
        List<String> loginList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queries.getString("sqlSelectAllLogin"));
            while (result.next()) {
                loginList.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginList;
    }

    @Override
    public void addPerson(Person person) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement prStatement = connection.prepareStatement(queries.getString("sqlInsertPerson"));
            initializePrepareStatement(person, prStatement);
            prStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializePrepareStatement(Person person, PreparedStatement prStatement) throws SQLException {
        prStatement.setString(1, person.getName());
        prStatement.setString(2, person.getSurname());
        prStatement.setString(3, person.getEmail());
        prStatement.setLong(4, person.getPhoneNumber());
        prStatement.setString(5, person.getLogin());
    }

    @Override
    public List<Person> obtainPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queries.getString("sqLSelectPersons"));
            persons = initPersons(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<Person> obtainPersons(SortMode sortMode, int limit, int offset) {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement prStatement = connection.prepareStatement(sortMode.obtainMode(queries));
            prStatement.setInt(1, limit);
            prStatement.setInt(2, offset);
            ResultSet result = prStatement.executeQuery();
            persons = initPersons(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public void updatePersons(List<Person> persons) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement prStatement = connection.prepareStatement(queries.getString("sqlUpdatePerson"));
            List<String> loginList = obtainLoginList();
            for (Person person : persons) {
                initializePrepareStatement(person, prStatement);
                prStatement.executeUpdate();
                loginList.add(person.getLogin());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Person> initPersons(ResultSet result) throws SQLException {
        List<Person> persons = new ArrayList<>();
        while (result.next()) {
            Person person = new Person();
            person.setName(result.getString("NAME"));
            person.setSurname(result.getString("SURNAME"));
            person.setLogin(result.getString("LOGIN"));
            person.setEmail(result.getString("EMAIL"));
            person.setPhoneNumber(result.getLong("PHONE_NUMBER"));
            persons.add(person);
        }
        return persons;
    }
}
