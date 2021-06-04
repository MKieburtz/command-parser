package cs350s21project.cli;

public class CommandInterpreter {

	public void evaluate(String command) {
		try {
			//String commandCommentsArray[] = command.split(Pattern.quote("[//]"));
			
			String commandArray[] = command.split(";");
			for(String i : commandArray) {
				String commStr[] = i.split(" +");			
				if (commStr[0].equalsIgnoreCase("set")) {
					new SetCommandHandler().handleSetCommand(command, commStr[]);
				} else if (commStr[0].equalsIgnoreCase("create")) {
					new CreateCommandHandler().handleCreateCommand(command, commStr[]);
				} else if (commStr[0].equalsIgnoreCase("delete")) {
					new DeleteCommandHandler().handleDeleteCommand(command, commStr[]);
				} else if (commStr[0].equalsIgnoreCase("define")) {
					new DefineCommandHandler().handleDefineCommand(command, commStr[]);
				} else if (commStr[0].equalsIgnoreCase("@load") || commStr[0].equalsIgnoreCase("@pause") || commStr[0].equalsIgnoreCase("@resume") || commStr[0].equalsIgnoreCase("@set")
														 || commStr[0].equalsIgnoreCase("@wait") || commStr[0].equalsIgnoreCase("@force") || commStr[0].equalsIgnoreCase("@exit")) {
					new MiscCommandHandler().handleMiscCommand(command, commStr[]);
				}
			}
		} catch (RuntimeException e) { // throws RuntimeException for invalid commands
			System.out.println("Error in evaluate" + e); // TODO work on this once i get methods
		}
	}
}

/* TO BE REMOVED
 * Whitespace, except in literals, does not matter. All text except identifiers
 * is case insensitive.
 * 
 * Commands may appear on the same line if they are separated by a semicolon.
 * 
 * A comment prefixed with // may follow a command or be on its own line.
 */

//"submits it to the schedule method in CommandManagers." We do this in the methods of the commands themselves.​