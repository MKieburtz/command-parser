package cs350s21project.cli;

public class SetCommandHandler {
    public void handleSetCommand(String command) {
		Verifier.verifyCommandHasAtLeastNumArguments(command, 4);
		String[] parts = command.split(" +");
		if(parts[2].equalsIgnoreCase("course")) {
			setCourse(command, parts);
		}
		if(parts[2].equalsIgnoreCase("speed")) {
			setSpeed(command, parts);
		}
	}

	private void load(String command, String[] parts) {

	}
	
	private void setCourse(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 4);
		//id
		Verifier.verifyID(parts[1]);
		AgentID id = new AgentID(parts[1]);
		//course
		Verifier.verifyCourse(parts[3]);
		double c = Double.parseDouble(parts[3]);
		Course course = new Course(c);
		
		//create command
		CommandActorSetCourse setCourse = new CommandActorSetCourse(CommandManagers.getInstance(), command, id, course);
		CommandManagers.getInstance().schedule(setCourse);
	}
	
	private void setSpeed(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 4);
		//id
		Verifier.verifyID(parts[1]);
		AgentID id = new AgentID(parts[1]);
		//course
		Verifier.verifySpeed(parts[3]);
		double c = Double.parseDouble(parts[3]);
		Groundspeed speed = new Groundspeed(c);
		
		//create command
		CommandActorSetSpeed setCourse = new CommandActorSetSpeed(CommandManagers.getInstance(), command, id, speed);
		CommandManagers.getInstance().schedule(setCourse);
	}
}
