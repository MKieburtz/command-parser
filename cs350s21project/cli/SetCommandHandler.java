package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.controller.command.actor.CommandActorSetAltitudeDepth;
import cs350s21project.datatype.*;

public class SetCommandHandler {

	public void handleSetCommand(String command, String parts[]) {
        if(parts[2].equalsIgnoreCase("altitude") || parts[2].equalsIgnoreCase("depth")) {
        	setAltitudeDepth(command, parts);
        }
        if(parts[2].equalsIgnoreCase("deploy") && parts[3].equalsIgnoreCase("munition")) {
        	setDeployMunition(command, parts);
        }
        if(parts[6].equalsIgnoreCase("azimuth")) {
        	setDeployMunitionShell(command, parts);
        }
        if(parts[1].equalsIgnoreCase("load")) {
        	load(command, parts);
        }
	}

	public void setAltitudeDepth(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 4);
        String idActor = parts[1]; // id1
        Verifier.verifyID(idActor);
        AgentID id1 = new AgentID(idActor);

        String altDep;
        if(parts[2].equalsIgnoreCase("altitude")) {
        	altDep = parts[2];
            Verifier.verifyAltitude(altDep);
            Altitude altitude = new Altitude(Double.parseDouble(altDep));
        } else {
        	altDep = parts[2];
        	Verifier.verifyDepth(altDep);
            Altitude altitude = new Altitude(Double.parseDouble(altDep));
        }


		//CommandActorSetAltitudeDepth(CommandManagers managers, java.lang.String text, AgentID idActor, Altitude altitude)
		CommandActorSetAltitudeDepth setAltitudeDepthCommand = new CommandActorSetAltitudeDepth(CommandManagers.getInstance(), command, id1, altDep);
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

	private void load(String command, String[] parts) {

	}
}
