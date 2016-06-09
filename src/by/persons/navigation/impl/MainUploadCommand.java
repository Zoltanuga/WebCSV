package by.persons.navigation.impl;

import by.persons.navigation.Command;

import static by.persons.resources.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainUploadCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return UPLOAD_PAGE;
    }
}
