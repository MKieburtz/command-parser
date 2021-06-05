// Team 6
// Michael Kieburtz
// Spencer Metz
// Logan Roylance

package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorCreateActor;
import cs350s21project.controller.command.view.CommandViewCreateWindowTop;
import cs350s21project.datatype.*;

public class CreateCommandHandler {
	public void handleCreateCommand(String command, String parts[]) {
		if(parts[1].equalsIgnoreCase("window")) {
			handleCreateWindowCommand(command, parts);
		} else if(parts[1].equalsIgnoreCase("actor")) {
			createActor(command, parts);
		}
	}

    private void handleCreateWindowCommand(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 13);
		if(parts[3].equalsIgnoreCase("top")) {
			createWindowTop(command, parts);
		}
	}
	
	private void createWindowTop(String command, String[] parts) {
		System.out.println("COMMAND " + command);
		//id
		Verifier.verifyID(parts[2]);
		AgentID id = new AgentID(parts[2]);
		if(!parts[4].equalsIgnoreCase("view") || !parts[5].equalsIgnoreCase("with")) {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (must have \"view with\")");
		}
		//size
		Verifier.verifySize(parts[6]);
		int size = Integer.parseInt(parts[6]);

		//latitude1
		if(parts[7].charAt(0) != '(') {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing '(' )");
		}
		String latString = parts[7].replaceAll("\\(", "");
		Verifier.verifyLatitude(latString);
		Latitude lat1 = getLat(latString);

		//latitude2
		latString = parts[8];
		Verifier.verifyLatitude(latString);
		Latitude lat2 = getLat(latString);

		//latitude3
		if(parts[9].indexOf(')') == -1) {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing ')' )");
		}
		latString = parts[9].replaceAll("\\)", "");
		Verifier.verifyLatitude(latString);
		Latitude lat3 = getLat(latString);

		//longitude1
		if(parts[10].charAt(0) != '(') {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing '(' )");
		}
		String longString = parts[10].replaceAll("\\(", "");
		Verifier.verifyLongitude(longString);
		Longitude lon1 = getLong(longString);

		//longitude2
		longString = parts[11];
		Verifier.verifyLongitude(longString);
		Longitude lon2 = getLong(longString);

		//longitude3
		if(parts[12].indexOf(')') == -1) {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing ')' )");
		}
		longString = parts[12].replaceAll("\\)", "");
		Verifier.verifyLongitude(longString);
		Longitude lon3 = getLong(longString);
		
		//create Window
		CommandViewCreateWindowTop window = new CommandViewCreateWindowTop(CommandManagers.getInstance(), command, id, size, lat1, lat2, lat3, lon1, lon2, lon3);
		CommandManagers.getInstance().schedule(window);
	}

	public void createActor(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 12);
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

		Verifier.verifyCoordinates(pos);
		CoordinateWorld3D position = new CoordinateWorld3D(latitude, longitude, altitude);

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
	// helper methods
	private Latitude getLat(String part) {
		int latDegree = getDegreesFromLatLong(part);
		int latMinute = getMinutesFromLatLong(part);
		double latSecond = getSecondsFromLatLong(part);
		return new Latitude(latDegree, latMinute, latSecond);
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
