package cs350s21project.cli;

public class CommandInterpreter {
	public void evaluate(String command) {
		if(command.isEmpty()) {
			System.out.println("Command is missing");
		}
		String[] commandArray = command.split(";"); // Commands may appear on the same line if they are separated by a semicolon.
		for(String i : commandArray) {
			String[] commStr = i.split(" +"); // Whitespace, except in literals, does not matter.
			if (commStr[0].equalsIgnoreCase("set")) {
				new SetCommandHandler().handleSetCommand(command, commStr);
			} else if (commStr[0].equalsIgnoreCase("create")) {
				new CreateCommandHandler().handleCreateCommand(command, commStr);
			} else if (commStr[0].equalsIgnoreCase("delete")) {
				new DeleteCommandHandler().handleDeleteCommand(command, commStr);
			} else if (commStr[0].equalsIgnoreCase("define")) {
				new DefineCommandHandler().handleDefineCommand(command, commStr);
			} else if (commStr[0].equalsIgnoreCase("@load") || commStr[0].equalsIgnoreCase("@pause") || commStr[0].equalsIgnoreCase("@resume") || commStr[0].equalsIgnoreCase("@set")
													 || commStr[0].equalsIgnoreCase("@wait") || commStr[0].equalsIgnoreCase("@force") || commStr[0].equalsIgnoreCase("@exit")) {
				new MiscCommandHandler().handleMiscCommand(command, commStr);
			} else if(commStr[0].equals("//")) { // A comment prefixed with // may follow a command or be on its own line.
				System.out.println("\n" + commStr[0] + "\n");
			}
		}
	}
}