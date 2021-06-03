package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;

public class MiscCommandHandler {
	public void handleMiscCommand(String command, String[] parts) {
        if (parts[0].equalsIgnoreCase("@load")) {
        	load(command, parts);
        } else if (parts[0].equalsIgnoreCase("@wait")) {
        	wait(command, parts);
        } else if (parts[0].equalsIgnoreCase("@pause")) {
        	pause(command);
        } else if (parts[0].equalsIgnoreCase("@exit")) {
        	exit(command);
        }
    }
	
	//  0       1
	//@load filename
	private void load(String command, String[] parts) {
    	String filename = parts[1];
    	Verifier.verifyFilename(filename);
    	//CommandMiscLoad​(CommandManagers managers, java.lang.String text, java.lang.String filename)
    	CommandMiscLoad loadCommand = new CommandMiscLoad(CommandManagers.getInstance(), command, filename);
        CommandManagers.getInstance().schedule(loadCommand);
    }
    
    private void exit(String command) {
    	CommandMiscExit exitCommand = new CommandMiscExit(CommandManagers.getInstance(), command);
        CommandManagers.getInstance().schedule(exitCommand);
        System.exit(0);
    }
}
