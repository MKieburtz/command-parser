// Team 6
// Michael Kieburtz
// Spencer Metz
// Logan Roylance

package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorSetState;
import cs350s21project.controller.command.misc.*;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Altitude;
import cs350s21project.datatype.CoordinateWorld3D;
import cs350s21project.datatype.Course;
import cs350s21project.datatype.Groundspeed;
import cs350s21project.datatype.Latitude;
import cs350s21project.datatype.Longitude;
import cs350s21project.datatype.Time;

public class MiscCommandHandler {
    public void handleMiscCommand(String command, String[] parts) {

        if (parts[0].equalsIgnoreCase("@load")) {
			load(command, parts);
        } else if (parts[0].equalsIgnoreCase("@pause")) {
            pause(command);
        } else if (parts[0].equalsIgnoreCase("@resume")) {
            resume(command);
        } else if (parts[0].equalsIgnoreCase("@set")) {
            setUpdate(command, parts);
        } else if (parts[0].equalsIgnoreCase("@wait")) {
            wait(command, parts);
        } else if (parts[0].equalsIgnoreCase("@force")) {
            force(command, parts);
        } else if (parts[0].equalsIgnoreCase("@exit")) {
			exit(command);
        }
    }

    private void pause(String command) {
		Verifier.verifyCommandHasNumArguments(command, 1);
        CommandMiscPause pauseCommand = new CommandMiscPause(CommandManagers.getInstance(), command);
        CommandManagers.getInstance().schedule(pauseCommand);
    }

    private void wait(String command, String[] parts) {
    	Verifier.verifyCommandHasNumArguments(command, 2);
        String timeString = parts[1];
        Verifier.verifyTime(timeString);
        Time time = new Time(Double.parseDouble(timeString));

        CommandMiscWait waitCommand = new CommandMiscWait(CommandManagers.getInstance(), command, time);

        CommandManagers.getInstance().schedule(waitCommand);
    }
    
    private void resume(String command) {
    	Verifier.verifyCommandHasNumArguments(command, 1);
    	CommandMiscResume resume = new CommandMiscResume(CommandManagers.getInstance(), command);
    	CommandManagers.getInstance().schedule(resume);
    }
    
    private void setUpdate(String command, String[] parts) {
    	Verifier.verifyCommandHasNumArguments(command, 3);
    	if(!parts[1].equalsIgnoreCase("update")) {
    		throw new RuntimeException("Error! syntax error on @set update command (update missing or incorrect)");
    	}
    	Verifier.verifyTime(parts[2]);
    	double t = Double.parseDouble(parts[2]);
    	Time time = new Time(t);
    	CommandMiscSetUpdate setUpdate = new CommandMiscSetUpdate(CommandManagers.getInstance(), command, time);
    	CommandManagers.getInstance().schedule(setUpdate);
    }
    
    private void force(String command, String[] parts) {
    	Verifier.verifyCommandHasNumArguments(command, 10);
    	//id
    	Verifier.verifyID(parts[1]);
    	AgentID id = new AgentID(parts[1]);
    	//"state to"
    	if(!parts[2].equalsIgnoreCase("state") || !parts[3].equalsIgnoreCase("to")) {
    		throw new RuntimeException("Error! Syntax error on @force command (need \"state to\")");
    	}
    	//coordinates
    	Verifier.verifyCoordinates(parts[4]);
    	//latitude
    	String editable = parts[4];
    	int degrees = Integer.parseInt(editable.substring(0,editable.indexOf('*')));
    	editable = editable.substring(editable.indexOf('*')+1);
    	int minutes = Integer.parseInt(editable.substring(0,editable.indexOf('\'')));
    	editable = editable.substring(editable.indexOf('\'')+1);
    	double seconds = Double.parseDouble(editable.substring(0,editable.indexOf('"')));
    	editable = editable.substring(editable.indexOf('"')+2);
    	Latitude lat = new Latitude(degrees, minutes, seconds);
    	//longitude
    	degrees = Integer.parseInt(editable.substring(0,editable.indexOf('*')));
    	editable = editable.substring(editable.indexOf('*')+1);
    	minutes = Integer.parseInt(editable.substring(0,editable.indexOf('\'')));
    	editable = editable.substring(editable.indexOf('\'')+1);
    	seconds = Double.parseDouble(editable.substring(0,editable.indexOf('"')));
    	editable = editable.substring(editable.indexOf('"')+2);
    	Longitude lon = new Longitude(degrees, minutes, seconds);
    	//altitude
    	double a = Double.parseDouble(editable);
    	Altitude alt = new Altitude(a);
    	//create coordinates
    	CoordinateWorld3D coord = new CoordinateWorld3D(lat, lon, alt);
    	//"with course"
    	if(!parts[5].equalsIgnoreCase("with") || !parts[6].equalsIgnoreCase("course")) {
    		throw new RuntimeException("Error! Syntax error on @force command (need \"with course\")");
    	}
    	//course
    	Verifier.verifyCourse(parts[7]);
    	double c = Double.parseDouble(parts[7]);
    	Course course = new Course(c);
    	//"speed"
    	if(!parts[8].equalsIgnoreCase("speed")) {
    		throw new RuntimeException("Error! Syntax error on @force command (need \"speed\")");
    	}
    	//speed
    	Verifier.verifySpeed(parts[9]);
    	double s = Double.parseDouble(parts[9]);
    	Groundspeed speed = new Groundspeed(s);
    	
    	//create command
    	CommandActorSetState force = new CommandActorSetState(CommandManagers.getInstance(), command, id, coord, course, speed);
    	CommandManagers.getInstance().schedule(force);
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
