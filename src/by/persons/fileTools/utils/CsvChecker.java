package by.persons.fileTools.utils;

import static by.persons.resources.Constants.REGEX_CSV;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvChecker {

    public static boolean isCsvFile(String fileName) {
        Pattern pattern = Pattern.compile(REGEX_CSV);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }
}
