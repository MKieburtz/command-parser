package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorLoadMunition;
import cs350s21project.controller.command.actor.CommandActorSetCourse;
import cs350s21project.controller.command.actor.CommandActorSetSpeed;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Course;
import cs350s21project.datatype.Groundspeed;

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
        String id = parts[1];
        Verifier.verifyID(id);
        AgentID agentID = new AgentID(id);

        String munitionID = parts[4];
        Verifier.verifyID(munitionID);
        AgentID agentIDMunition = new AgentID(munitionID);

        CommandActorLoadMunition loadCommand = new CommandActorLoadMunition(CommandManagers.getInstance(), command, agentID, agentIDMunition);

        CommandManagers.getInstance().schedule(loadCommand);
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
