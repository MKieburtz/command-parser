// Te   am 6
// Michael Kieburtz
// Spencer Metz
// Logan Roylance

package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.view.CommandViewDeleteWindow;
import cs350s21project.datatype.AgentID;

public class DeleteCommandHandler {
    public void handleDeleteCommand(String command, String[] parts) {
        if (parts[1].equalsIgnoreCase("window")) {
            deleteWindow(command, parts);
        }
    }

    private void deleteWindow(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 3);
        String idString = parts[2];
        Verifier.verifyID(idString);
        AgentID id = new AgentID(idString);
        CommandViewDeleteWindow deleteCommand = new CommandViewDeleteWindow(CommandManagers.getInstance(), command, id);
        CommandManagers.getInstance().schedule(deleteCommand);
    }
}
