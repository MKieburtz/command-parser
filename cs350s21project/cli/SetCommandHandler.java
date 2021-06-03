package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.controller.command.actor.CommandActorSetAltitudeDepth;
import cs350s21project.datatype.*;

public class SetCommandHandler {

	public void handleSetCommand(String command, String parts[]) {
		int partSize = parts.length();
        if(parts[2].equalsIgnoreCase("altitude") || parts[2].equalsIgnoreCase("depth") && partSize > 4) {
        	setAltitudeDepth(command, parts);
        }
        if(parts[2].equalsIgnoreCase("deploy") && parts[3].equalsIgnoreCase("munition") && partSize > 5) {
        	setDeployMunition(command, parts);
        }
        if(parts[6].equalsIgnoreCase("azimuth")) {
        	setDeployMunitionShell(command, parts);
        }
        if(parts[1].equalsIgnoreCase("load")) {
        	load(command, parts);
        }
	}

	/* 0    1       2         3
	 * set id altitude|depth altitude
	 */
	public void setAltitudeDepth(String command, String[] parts) { //CommandActorSetAltitudeDepth

        String idActor = parts[1]; // id1
        Verifier.verifyID(idActor);
        AgentID id1 = new AgentID(idActor);

        String altDep;
        if(parts[2].equalsIgnoreCase("altitude")) {
        	altDep = parts[3];
            Verifier.verifyAltitude(alt);
            Altitude altitude = new Altitude(Double.parseDouble(alt));
        } else {
        	altDep = parts[3];
        	Verifier.verifyDepth(depth);
            Altitude altitude = new Altitude(Double.parseDouble(depth));
        }


		//CommandActorSetAltitudeDepth​(CommandManagers managers, java.lang.String text, AgentID idActor, Altitude altitude)
		CommandActorSetAltitudeDepth setAltitudeDepthCommand = new CommandActorSetAltitudeDepth(CommandManagers.getInstance(), command, id1, altDep);
        CommandManagers.getInstance().schedule(setAltitudeDepthCommand);
	}

	/* 0    1    2        3     4
	 * set id1 deploy munition id2
	 */
	public void setDeployMunition(String command, String[] parts) { // CommandActorDeployMunition
        String idActor = parts[1]; // id1
        Verifier.verifyID(idActor);
        AgentID id1 = new AgentID(idActor);

        String idMunition = parts[4]; //id2
        Verifier.verifyID(idMunition);
        AgentID id2 = new AgentID(idMunition);

		//CommandActorDeployMunition​(CommandManagers managers, java.lang.String text, AgentID idActor, AgentID idMunition)
        CommandActorDeployMunition setDeployMunitionCommand = new CommandActorDeployMunition(CommandManagers.getInstance(), command, id1, id2);
        CommandManagers.getInstance().schedule(setDeployMunitionCommand);
	}

	/*  0   1    2     3        4   5    6      7       8          9
	 * set id1 deploy munition id2 at azimuth azimuth elevation elevation
	 */
	public void setDeployMunitionShell(String command, String[] parts) { // CommandActorDeployMunitionShell
        String idActor = parts[1]; // id1
        Verifier.verifyID(idActor);
        AgentID id1 = new AgentID(idActor);

        String idMunition = parts[4]; //id2
        Verifier.verifyID(idMunition);
        AgentID id2 = new AgentID(idMunition);

        AttitudeYaw azimuth = new AttitudeYaw(Double.parseDouble(parts[7]));

        AttitudePitch elevation = new AttitudePitch(Double.parseDouble(parts[9]));

		//CommandActorDeployMunitionShell​(CommandManagers managers, java.lang.String text, AgentID idActor, AgentID idMunition, AttitudeYaw azimuth, AttitudePitch elevation)
		CommandActorDeployMunitionShell​ setDeployMunitionShellCommand = new CommandActorDeployMunitionShell​(CommandManagers.getInstance(), command, id1, id2, azimuth, elevation);
        CommandManagers.getInstance().schedule(setDeployMunitionShellCommand);
	}

	private void load(String command, String[] parts) {

	}
}
