package by.persons.navigation.impl;

import by.persons.database.SortMode;
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

public class MainListPersonsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        QueryManager queryManager = new QueryManager(PARAM_SORT, QUERY_LIMIT_VALUE, SortMode.SORT_BY_NAME);
        List<Person> persons = queryManager.performQuery();
        session.setAttribute(PageSplitter.PARAM_IS_LAST_PAGE, false);
        queryManager.importSortModeToSession(session);
        queryManager.importCurrentQueryNumberToSession(session);
        PageSplitter<Person> pageSplitter = new PageSplitter<>(PARAM_PERSONS, PARAM_LINES_PER_PAGE, PARAM_CURRENT_PAGE);
        pageSplitter.setItems(persons);
        pageSplitter.importInitialParametersToSession(session);
        Command command = CommandFactory.getCommand(PARAM_LIST_PERSONS);
        command.execute(request, response);
        return LIST_PERSONS_PAGE;
    }
}
