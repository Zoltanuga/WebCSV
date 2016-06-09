package by.persons.navigation.impl;


import by.persons.navigation.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.persons.resources.Constants.ERROR_PAGE;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ERROR_PAGE;
    }
}
