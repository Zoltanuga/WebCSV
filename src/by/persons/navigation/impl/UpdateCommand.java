package by.persons.navigation.impl;

import by.persons.fileTools.CsvManager;
import by.persons.fileTools.utils.CsvChecker;
import by.persons.navigation.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.persons.resources.Constants.*;
import static by.persons.resources.Constants.ERROR_PAGE;


public class UpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CsvManager csvManager = new CsvManager(DELIMITER);
        if (!CsvChecker.isCsvFile(request.getAttribute(PARAM_FILE_NAME).toString())) {
            return ERROR_PAGE;
        }
        boolean isSuccess = true;
        try {
            isSuccess = csvManager.manageCsv(UPLOAD_DIRECTORY_PATH + request.getAttribute(PARAM_FILE_NAME), false);
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR_PAGE;
        }
        return isSuccess ? SUCCESS_PAGE : ERROR_PAGE;

    }
}
