package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;

public class MiscCommandHandler {
    public void handleMiscCommand(String command) {
        String[] parts = command.split(" +");

        if (parts[0].equalsIgnoreCase("@load")) {
			load(command, parts);
        } else if (parts[0].equalsIgnoreCase("@pause")) {
            
        } else if (parts[0].equalsIgnoreCase("@resume")) {

        } else if (parts[0].equalsIgnoreCase("@set")) {

        } else if (parts[0].equalsIgnoreCase("@wait")) {
            
        } else if (parts[0].equalsIgnoreCase("@force")) {

        } else if (parts[0].equalsIgnoreCase("@exit")) {
  			exit(command);
        }
    }

	private void load(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 2);
		String filename = parts[1];
		Verifier.verifyFilename(filename);

		CommandMiscLoad loadCommand = new CommandMiscLoad(CommandManagers.getInstance(), command, filename);
		CommandManagers.getInstance().schedule(loadCommand);
	}

	private void exit(String command) {
		Verifier.verifyCommandHasNumArguments(command, 1);
		CommandMiscExit exitCommand = new CommandMiscExit(CommandManagers.getInstance(), command);
		CommandManagers.getInstance().schedule(exitCommand);
	}
}