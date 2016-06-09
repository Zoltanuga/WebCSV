package by.persons.fileTools;


import by.persons.entities.Person;
import by.persons.service.PersonService;

import static by.persons.resources.Constants.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvManager {
    private String delimiter;

    public boolean manageCsv(String path, boolean uploadMode) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        long splitLimit = calculateEstimatedLineLimit(path);
        int lineCounter = 0;
        if (!isValidMapping(reader)) {
            throw new IOException("Mapping Exception");
        }
        StringBuilder currentLine = new StringBuilder(reader.readLine());
        List<Person> persons = new ArrayList<>();
        int endFileSymptom = END_FILE_VALUE;
        while (currentLine.length() != endFileSymptom) {
            if (lineCounter == splitLimit) {
                lineCounter = 0;
                if (!persons.isEmpty()) {
                    performActionWithDatabase(uploadMode, persons);
                    persons.clear();
                } else {
                    return false;
                }
            }
            String[] words = currentLine.toString().split(delimiter);
            Person person = new Person(words[0], words[1], words[2], words[3], Long.parseLong(words[4]));
            persons.add(person);
            currentLine.delete(0, currentLine.length());
            currentLine.append(reader.readLine());
            lineCounter++;
        }
        if (!persons.isEmpty()) {
            performActionWithDatabase(uploadMode, persons);
            persons.clear();
        }
        return true;
    }

    private void performActionWithDatabase(boolean uploadMode, List<Person> persons) {
        PersonService personService = new PersonService();
        if (uploadMode) {
            personService.addPersons(persons);
        } else {
            personService.updatePersons(persons);
        }
    }

    private boolean isValidMapping(BufferedReader reader) throws IOException {
        String[] columns = {PARAM_NAME, PARAM_SURNAME, PARAM_LOGIN, PARAM_EMAIL, PARAM_PHONE_NUMBER};
        String headerString = reader.readLine();
        boolean isValid = true;
        String[] headers = headerString.split(delimiter);
        for (int i = 0; i < headers.length; i++) {
            if (!headers[i].equals(columns[i])) {
                isValid = false;
            }
        }
        return isValid;
    }

    private long calculateEstimatedLineLimit(String path) {
        File file = new File(path);
        return file.length() > LENGTH_LIMIT ? (LENGTH_LIMIT / APPROXIMATED_QUANTITY_BYTES_PER_LINE) : -1;
    }

    public CsvManager(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
