package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.*;
import cs350s21project.datatype.*;

public class SetCommandHandler {
    public void handleSetCommand(String command, String[] parts) {
        Verifier.verifyCommandHasAtLeastNumArguments(command, 4);
		if(parts[2].equalsIgnoreCase("course")) {
			setCourse(command, parts);
		} else if(parts[2].equalsIgnoreCase("speed")) {
			setSpeed(command, parts);
		} else if(parts[2].equalsIgnoreCase("altitude") || parts[2].equalsIgnoreCase("depth")) {
            setAltitudeDepth(command, parts);
        } else if(parts[2].equalsIgnoreCase("deploy") && parts[3].equalsIgnoreCase("munition")) {
            setDeployMunition(command, parts);
        } else if(parts[6].equalsIgnoreCase("azimuth")) {
            setDeployMunitionShell(command, parts);
        } else if(parts[1].equalsIgnoreCase("load")) {
            load(command, parts);
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

    public void setAltitudeDepth(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 4);
        String idActor = parts[1]; // id1
        Verifier.verifyID(idActor);
        AgentID id1 = new AgentID(idActor);

        String altDep;
        Altitude altitude;
        if(parts[2].equalsIgnoreCase("altitude")) {
            altDep = parts[2];
            Verifier.verifyAltitude(altDep);
            altitude = new Altitude(Double.parseDouble(altDep));
        } else {
            altDep = parts[2];
            Verifier.verifyDepth(altDep);
            altitude = new Altitude(Double.parseDouble(altDep));
        }


        //CommandActorSetAltitudeDepth(CommandManagers managers, java.lang.String text, AgentID idActor, Altitude altitude)
        CommandActorSetAltitudeDepth setAltitudeDepthCommand = new CommandActorSetAltitudeDepth(CommandManagers.getInstance(), command, id1, altitude);
        CommandManagers.getInstance().schedule(setAltitudeDepthCommand);
    }

    public void setDeployMunition(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 4);
        String idActor = parts[1]; // id1
        Verifier.verifyID(idActor);
        AgentID id1 = new AgentID(idActor);

        String idMunition = parts[4]; //id2
        Verifier.verifyID(idMunition);
        AgentID id2 = new AgentID(idMunition);

        //CommandActorDeployMunition(CommandManagers managers, java.lang.String text, AgentID idActor, AgentID idMunition)
        CommandActorDeployMunition setDeployMunitionCommand = new CommandActorDeployMunition(CommandManagers.getInstance(), command, id1, id2);
        CommandManagers.getInstance().schedule(setDeployMunitionCommand);
    }

    public void setDeployMunitionShell(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 9);
        String idActor = parts[1]; // id1
        Verifier.verifyID(idActor);
        AgentID id1 = new AgentID(idActor);

        String idMunition = parts[4]; //id2
        Verifier.verifyID(idMunition);
        AgentID id2 = new AgentID(idMunition);

        String azi = parts[7];
        Verifier.verifyAltitude(azi);
        AttitudeYaw azimuth = new AttitudeYaw(Double.parseDouble(azi));

        String ele = parts[9];
        Verifier.verifyElevation(ele);
        AttitudePitch elevation = new AttitudePitch(Double.parseDouble(ele));

        //CommandActorDeployMunitionShell(CommandManagers managers, java.lang.String text, AgentID idActor, AgentID idMunition, AttitudeYaw azimuth, AttitudePitch elevation)
        CommandActorDeployMunitionShell setDeployMunitionShellCommand = new CommandActorDeployMunitionShell(CommandManagers.getInstance(), command, id1, id2, azimuth, elevation);
        CommandManagers.getInstance().schedule(setDeployMunitionShellCommand);
    }
}

