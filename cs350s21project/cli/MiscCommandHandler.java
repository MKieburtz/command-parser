package cs350s21project.cli;

public class MiscCommandHandler {
    public void handleMiscCommand(String command) {
        String[] parts = command.split(" +");

        if (parts[0].equalsIgnoreCase("@load")) {

        } else if (parts[0].equalsIgnoreCase("@pause")) {
            pause(command);
        } else if (parts[0].equalsIgnoreCase("@resume")) {

        } else if (parts[0].equalsIgnoreCase("@set")) {

        } else if (parts[0].equalsIgnoreCase("@wait")) {
            wait(command, parts);
        } else if (parts[0].equalsIgnoreCase("@force")) {

        } else if (parts[0].equalsIgnoreCase("@exit")) {

        }
    }

    private void pause(String command) {

    }

    private void wait(String command, String[] parts) {

    }
}
