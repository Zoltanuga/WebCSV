package by.persons.navigation;

public class CommandFactory {
    public static Command getCommand(String command) {
        CommandEnum commandEnum = CommandEnum.valueOf(command.toUpperCase());
        return commandEnum.createCommand();
    }
}
