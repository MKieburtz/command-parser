package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.datatype.*;

public class CreateCommandHandler {

	public void handleCreateCommand(String command, String parts[]) {

        if(parts[1].equalsIgnoreCase("window")) {
        	// logan's code
        } else if(parts[1].equalsIgnoreCase("actor")) {
        	createActor(command, parts);
        }
    }

	public void createActor(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 11);
		String idString = parts[2]; // id1
		Verifier.verifyID(idString);
		AgentID id1 = new AgentID(idString);
		
        String idActor = parts[4]; // id2
        Verifier.verifyID(idActor);
        AgentID id2 = new AgentID(idActor);
        
        String pos = parts[6]; // position
        
        //latitude/longitude/altitude
        String posStr[] = pos.split("/");
        
        Verifier.verifyLatitude(posStr[0]);
        Latitude latitude = getLat(posStr[0]);
        
        Verifier.verifyLongitude(posStr[1]);
        Longitude longitude = getLong(posStr[1]);
        
        Verifier.verifyAltitude(posStr[2]);
        Altitude altitude = new Altitude(Double.parseDouble(posStr[2]));
        
        Veifier.verifyCoordinates(pos);
        CoordinateWorld3D position = new CoordinateWorld3D(latitude, longitude, altitude); //TODO finish this

        String cour = parts[9]; // course
        Verifier.verifyCourse(cour);
		Course course = new Course(Double.parseDouble(cour));

		String gSp = parts[11]; // Speed
		Verifier.verifySpeed(gSp);
		Groundspeed speed = new Groundspeed(Double.parseDouble(gSp));

		//CommandActorCreateActor(CommandManagers managers, java.lang.String text, AgentID idActor, AgentID idFamily, CoordinateWorld3D position, Course course, Groundspeed speed)
		CommandActorCreateActor createCommand = new CommandActorCreateActor(CommandManagers.getInstance(), command, id1, id2, position, course, speed);
        CommandManagers.getInstance().schedule(createCommand);
	}

	private Latitude getLat(String part) {
		int latDegree = getDegreesFromLatLong(part);
        int latMinute = getMinutesFromLatLong(part);
        double latSecond = getSecondsFromLatLong(part);
        return new Latitude​(latDegree, latMinute, latSecond);
	}
	private Longitude getLong(String part) {
        int longDegree = getDegreesFromLatLong(part);
        int longMinute = getMinutesFromLatLong(part);
        double longSecond = getSecondsFromLatLong(part);
        return new Longitude(longDegree, longMinute, longSecond);
	}

    private static int getDegreesFromLatLong(String latLong) {
        int indexOfDegreesDelimiter = latLong.indexOf("*");
        return Integer.parseInt(latLong.substring(0, indexOfDegreesDelimiter));
    }

    private static int getMinutesFromLatLong(String latLong) {
        int indexOfDegreesDelimiter = latLong.indexOf("*");
        int indexOfMinutesDelimiter = latLong.indexOf("'");
        return Integer.parseInt(latLong.substring(indexOfDegreesDelimiter + 1, indexOfMinutesDelimiter));
    }

    private static double getSecondsFromLatLong(String latLong) {
        int indexOfMinutesDelimiter = latLong.indexOf("'");
        return Double.parseDouble(latLong.substring(indexOfMinutesDelimiter + 1, latLong.length() - 1));
    }
}
