package by.persons.navigation.impl;

import by.persons.entities.Person;
import by.persons.navigation.Command;
import by.persons.navigation.CommandFactory;
import by.persons.navigation.PageSplitter;
import by.persons.navigation.QueryManager;

import static by.persons.resources.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class SortPersonsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        QueryManager queryManager = new QueryManager(PARAM_SORT, QUERY_LIMIT_VALUE);
        queryManager.obtainSortModeFromRequest(request);
        queryManager.importSortModeToSession(session);
        session.setAttribute(PageSplitter.PARAM_IS_LAST_PAGE, false);
        queryManager.resetQueryCounter(session);
        List<Person> persons = queryManager.performQuery();
        PageSplitter<Person> pageSplitter = new PageSplitter<>(PARAM_PERSONS, PARAM_LINES_PER_PAGE, PARAM_CURRENT_PAGE);
        pageSplitter.obtainQuantityLinesPerPageFromSession(session);
        pageSplitter.setItems(persons);
        pageSplitter.importCurrentParametersToSession(session);
        Command command = CommandFactory.getCommand(PARAM_LIST_PERSONS);
        command.execute(request, response);
        return LIST_PERSONS_PAGE;
    }
}
