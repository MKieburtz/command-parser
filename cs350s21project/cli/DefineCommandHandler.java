package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.datatype.*;

public class DefineCommandHandler {
    public void handleDefineCommand(String command) {
        String[] parts = command.split(" +");

        if (parts[1].equalsIgnoreCase("ship")) {
            defineShip(command, parts);
        } else if (parts[1].equalsIgnoreCase("munition")) {
            handleDefineMunitionCommand(command, parts);
        } else if (parts[1].equalsIgnoreCase("sensor")) {
            handleDefineSensorCommand(command, parts);
        }
    }

    private void handleDefineMunitionCommand(String command, String[] parts) {
        Verifier.verifyCommandHasAtLeastNumArguments(command, 3);
        if (parts[2].equalsIgnoreCase("bomb")) {

        } else if (parts[2].equalsIgnoreCase("shell")) {

        } else if (parts[2].equalsIgnoreCase("depth_charge") ) {
  			defineDepthCharge(command, parts);
  	    }  else if (parts[2].equalsIgnoreCase("torpedo")) {
            defineTorpedo(command, parts);
        } else if (parts[2].equalsIgnoreCase("missile")) {
    	    defineMissile(command, parts);
  	    }
    }

    private void defineTorpedo(String command, String[] parts) {
        // define the torpedo
    }

    public void defineDepthCharge(String command, String[] parts) {
      String idDC = parts[3]; // id1
      Verifier.verifyID(idDC);
      AgentID idMunition = new AgentID(idDC);

      String idF = parts[6]; // id2
      Verifier.verifyID(idF);
      AgentID idFuze = new AgentID(idF);

      //CommandMunitionDefineDepthCharge(CommandManagers managers, java.lang.String text, AgentID idMunition, AgentID idFuze)
      CommandMunitionDefineDepthCharge defineDepthChargeCommand = new CommandMunitionDefineDepthCharge(CommandManagers.getInstance(), command, idMunition, idFuze);
      CommandManagers.getInstance().schedule(defineDepthChargeCommand);
    }


    public void defineMissile(String command, String[] parts) {
      String idMunition = parts[3]; // id1
      Verifier.verifyID(idMunition);
      AgentID id1 = new AgentID(idMunition);

      String idSensor = parts[6]; // id2
      Verifier.verifyID(idSensor);
      AgentID id2 = new AgentID(idSensor);

      String idFuze = parts[8]; // id3
      Verifier.verifyID(idFuze);
      AgentID id3 = new AgentID(idFuze);

      String distance = parts[11];
      Verifier.verifyDistance(distance);
      DistanceNauticalMiles distance = new DistanceNauticalMiles(Double.parseDouble(distance));

      //CommandMunitionDefineMissile(CommandManagers managers, java.lang.String text, AgentID idMunition, AgentID idSensor, AgentID idFuze, DistanceNauticalMiles distance)
      CommandMunitionDefineMissile munitionDefineMissile = new CommandMunitionDefineMissile(CommandManagers.getInstance(), command, id1, id2, id3, distance);
      CommandManagers.getInstance().schedule(munitionDefineMissile);
    }

    private void handleDefineSensorCommand(String command, String[] parts) {
        Verifier.verifyCommandHasAtLeastNumArguments(command, 3);
        if (parts[2].equalsIgnoreCase("radar")) {
            defineRadarSensor(command, parts);
        } else if (parts[2].equalsIgnoreCase("thermal")) {
            defineThermalSensor(command, parts);
        } else if (parts[2].equalsIgnoreCase("acoustic")) {
            defineAcousticSensor(command, parts);
        } else if (parts[2].equalsIgnoreCase("sonar")) {

        } else if (parts[2].equalsIgnoreCase("depth")) {
            defineDepth(command, parts);
        } else if (parts[2].equalsIgnoreCase("distance")) {
            defineDistance(command, parts);
        } else if (parts[2].equalsIgnoreCase("time")) {
            defineTime(command, parts);
        }
    }

    private void defineRadarSensor(String command, String[] parts) {

    }

    private void defineThermalSensor(String command, String[] parts) {

    }

    private void defineAcousticSensor(String command, String[] parts) {

    }

    public void defineDepth(String command, String[] parts) {
    	String id1 = parts[3];
    	Verifier.verifyID(id1);
    	AgentID idSensor = new AgentID(id1);
    	
    	String altitude = parts[7];
    	Verifier.verifyAltitude(altitude);
    	Altitude altitude = new Altitude(Double.parseDouble(altitude));

    	//CommandSensorDefineDepth​(CommandManagers managers, java.lang.String text, AgentID idSensor, Altitude depth)
    	CommandSensorDefineDepth defineDeptheCommand = new CommandSensorDefineDepth(CommandManagers.getInstance(), command, idSensor, altitude);
    	CommandManagers.getInstance().schedule(defineDeptheCommand);
    }


    public void defineDistance(String command, String[] parts) {
    	String id1 = parts[3];
    	Verifier.verifyID(id1);
    	AgentID idSensor = new AgentID(id1);
    	
    	String distance = parts[7];
    	Verifier.verifyDistance(distance);
    	DistanceNauticalMiles distance = new DistanceNauticalMiles(Double.parseDouble(distance));

    	//CommandSensorDefineDistance​(CommandManagers managers, java.lang.String text, AgentID idSensor, DistanceNauticalMiles distance)
    	CommandSensorDefineDistance defineDistanceCommand = new CommandSensorDefineDistance(CommandManagers.getInstance(), command, idSensor, distance);
    	CommandManagers.getInstance().schedule(defineDistanceCommand);
    }

    public void defineTime(String command, String[] parts) {
    	String idSensor = parts[3];
    	Verifier.verifyID(idSensor);
    	AgentID idSensor = new AgentID(idSensor);
    	
    	String time = parts[7];
    	Verifier.verifyTime(time);
    	Time time = new Time(Double.parseDouble(time));

      //CommandSensorDefineTime​(CommandManagers managers, java.lang.String text, AgentID idSensor, Time time)
      CommandSensorDefineTime defineTimeCommand = new CommandSensorDefineTime(CommandManagers.getInstance(), command, idSensor, time));
      CommandManagers.getInstance().schedule(defineTimeCommand);
    }
}
