package by.persons.navigation;


import by.persons.database.SortMode;
import by.persons.entities.Person;
import by.persons.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class QueryManager {
    private PersonService personService = new PersonService();
    private SortMode sortMode;
    private int queryOffset;
    private int currentQueryNumber;
    private int queryLimit;
    public final String PARAM_SORT;
    public static final String PARAM_CURRENT_QUERY_NUMBER = "currentQueryNumber";


    public QueryManager(String paramSort, int queryLimit) {
        sortMode = SortMode.SORT_BY_NAME;
        this.PARAM_SORT = paramSort;
        this.queryLimit = queryLimit;
    }

    public QueryManager(String paramSort, int queryLimit, SortMode sortMode) {
        this.queryLimit = queryLimit;
        this.sortMode = sortMode;
        this.PARAM_SORT = paramSort;
        queryOffset = this.queryLimit * currentQueryNumber;
    }

    public void importParametersToSession(HttpSession session) {
        session.setAttribute(PARAM_CURRENT_QUERY_NUMBER, currentQueryNumber);
        session.setAttribute(PARAM_SORT, sortMode);
    }

    public void obtainParametersFromSession(HttpSession session) {
        String sortMode = session.getAttribute(PARAM_SORT).toString();
        this.sortMode = SortMode.valueOf(sortMode);
        currentQueryNumber = (int) session.getAttribute(PARAM_CURRENT_QUERY_NUMBER);
        queryOffset = this.queryLimit * currentQueryNumber;
    }

    public void importInitialParametersToSession(HttpSession session) {
        session.setAttribute(PARAM_CURRENT_QUERY_NUMBER, 0);
    }

    public void resetQueryCounter(HttpSession session) {
        currentQueryNumber = 0;
        session.setAttribute(PARAM_CURRENT_QUERY_NUMBER, currentQueryNumber);
    }

    public List<Person> performQuery() {
        return personService.obtainPersons(sortMode, queryLimit, queryOffset);
    }

    public void incrementCurrentQueryNumber() {
        currentQueryNumber++;
        queryOffset = this.queryLimit * currentQueryNumber;
    }

    public void importCurrentQueryNumberToSession(HttpSession session) {
        session.setAttribute(PARAM_CURRENT_QUERY_NUMBER, currentQueryNumber);
    }

    public void obtainCurrentQueryNumberFromSession(HttpSession session) {
        currentQueryNumber = (int) session.getAttribute(PARAM_CURRENT_QUERY_NUMBER);
    }

    public void importSortModeToSession(HttpSession session) {
        session.setAttribute(PARAM_SORT, sortMode);
    }

    public void obtainSortModeFromRequest(HttpServletRequest request) {
        String sortMode = request.getParameter(PARAM_SORT).toUpperCase();
        this.sortMode = SortMode.valueOf(sortMode);
    }

    public void obtainSortModeFromSession(HttpSession session) {
        String sortMode = session.getAttribute(PARAM_SORT).toString();
        this.sortMode = SortMode.valueOf(sortMode);
    }

    public int getCurrentQueryNumber() {
        return currentQueryNumber;
    }
}
