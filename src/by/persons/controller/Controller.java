package by.persons.controller;

import by.persons.fileTools.utils.CsvChecker;
import by.persons.navigation.Command;
import by.persons.navigation.CommandFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import static by.persons.resources.Constants.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performAction(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performAction(request, response);
    }

    private void performAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandType;
        if (ServletFileUpload.isMultipartContent(request)) {
            commandType = uploadHandler(request);
        } else {
            commandType = request.getParameter(PARAM_COMMAND);
        }
        Command command = CommandFactory.getCommand(commandType);
        String nextPage = command.execute(request, response);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
        requestDispatcher.forward(request, response);
    }

    private String uploadHandler(HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = new ArrayList<>();
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        String paramCommand = null;
        for (FileItem item : items) {
            if (!item.isFormField()) {
                String name = item.getName();
                File serverFile = new File(UPLOAD_DIRECTORY_PATH, name);
                request.setAttribute(PARAM_FILE_NAME, name);
                try {
                    item.write(serverFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (item.getFieldName().equals(PARAM_COMMAND)) {
                paramCommand = item.getString();
            }
        }
        return paramCommand;
    }
}
