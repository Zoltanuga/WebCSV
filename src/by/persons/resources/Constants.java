package by.persons.resources;

public class Constants {

    //PAGES
    public static final String ERROR_PAGE = "pages/errorPage.jsp";
    public static final String UPLOAD_PAGE = "pages/uploadPage.jsp";
    public static final String SUCCESS_PAGE = "pages/successPage.jsp";
    public static final String LIST_PERSONS_PAGE = "pages/listPersonsPage.jsp";

    //PARAMETERS
    public static final String PARAM_FILE_NAME = "fileName";
    public static final String PARAM_CURRENT_PAGE = "pageNumber";
    public static final String PARAM_LINES_PER_PAGE = "linesPerPage";
    public static final String PARAM_LIST_PERSONS = "list_persons";
    public static final String PARAM_PERSONS = "persons";
    public static final String PARAM_SORT = "sort";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_SURNAME = "surname";
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_PHONE_NUMBER = "phone number";
    public static final String PARAM_COMMAND = "command";

    //VALUES
    public static final String DELIMITER = ";";
    public static final String UPLOAD_DIRECTORY_PATH = "d:\\uploads\\";
    public static final String REGEX_CSV = "\\w+.csv\\b";
    public static final int LENGTH_LIMIT = 102400000;
    public static final int APPROXIMATED_QUANTITY_BYTES_PER_LINE = 200;
    public static final int END_FILE_VALUE = 4;
    public static final int QUERY_LIMIT_VALUE = 60;
}