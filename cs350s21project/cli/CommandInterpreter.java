// Team 6
// Michael Kieburtz
// Spencer Metz
// Logan Roylance

package cs350s21project.cli;

public class CommandInterpreter {
	public void evaluate(String command) {
		if(command.isEmpty()) 
			throw new Exception("Error: Command is empty; " + command);
		
		command = command.trim();
		
		if(command.contains("//")) {
			String commandBeforeComment = subString(0, command.indexOf("//"));
		}
		
		String[] commandArray = command2.split(";");
		for(String i : commandArray) {
			String[] commStr = i.split(" +");
			if (commStr[0].equalsIgnoreCase("set")) {
				new SetCommandHandler().handleSetCommand(command, commStr);
			} else if (commStr[0].equalsIgnoreCase("create")) {
				new CreateCommandHandler().handleCreateCommand(command, commStr);
			} else if (commStr[0].equalsIgnoreCase("delete")) {
				new DeleteCommandHandler().handleDeleteCommand(command, commStr);
			} else if (commStr[0].equalsIgnoreCase("define")) {
				new DefineCommandHandler().handleDefineCommand(command, commStr);
			} else if(commStr[0].charAt(0) == '@') {
				new MiscCommandHandler().handleMiscCommand(command, commStr);
			}
		}
	}
}
