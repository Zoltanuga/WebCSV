package by.persons.navigation.impl;

import by.persons.entities.Person;
import by.persons.navigation.Command;
import by.persons.navigation.PageSplitter;
import by.persons.navigation.QueryManager;

import static by.persons.resources.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListPersonsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        QueryManager queryManager = new QueryManager(PARAM_SORT, QUERY_LIMIT_VALUE);
        queryManager.obtainParametersFromSession(session);
        PageSplitter<Person> pageSplitter = new PageSplitter<>(PARAM_PERSONS, PARAM_LINES_PER_PAGE, PARAM_CURRENT_PAGE);
        pageSplitter.obtainParametersFromSession(session);
        if (pageSplitter.isLastPage()) {
            queryManager.incrementCurrentQueryNumber();
            queryManager.importCurrentQueryNumberToSession(session);
            pageSplitter.importInitialCurrentSubPageToSession(session, 1);
            List<Person> persons = queryManager.performQuery();
            if (persons.isEmpty()) {
                queryManager.resetQueryCounter(session);
            }
            pageSplitter.setItems(persons);
            pageSplitter.importItemsToSession(session);
            pageSplitter.importSubPageQuantityToSession(session);
            pageSplitter.importFirstItemsSubListToRequest(request, session);
        } else {
            pageSplitter.importItemsSubListToRequest(request, session);
        }
        return LIST_PERSONS_PAGE;
    }
}
