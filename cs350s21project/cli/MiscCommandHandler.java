package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.misc.CommandMiscPause;
import cs350s21project.controller.command.misc.CommandMiscWait;
import cs350s21project.datatype.Time;

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
        CommandMiscPause pauseCommand = new CommandMiscPause(CommandManagers.getInstance(), command);
        CommandManagers.getInstance().schedule(pauseCommand);
    }

    private void wait(String command, String[] parts) {
        String timeString = parts[1];
        Verifier.verifyTime(timeString);
        Time time = new Time(Double.parseDouble(timeString));

        CommandMiscWait waitCommand = new CommandMiscWait(CommandManagers.getInstance(), command, time);

        CommandManagers.getInstance().schedule(waitCommand);
    }
}
