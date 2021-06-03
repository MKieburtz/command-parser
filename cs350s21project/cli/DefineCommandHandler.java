package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.datatype.*;

public class DefineCommandHandler {

	public void handleDefineCommand(String command, String[] parts) {

	    if (parts[1].equalsIgnoreCase("munition") && parts[2].equalsIgnoreCase("depth_charge") ) {
			defineDepthCharge(command, parts);
	    } else if (parts[1].equalsIgnoreCase("munition")&& parts[2].equalsIgnoreCase("missile")) {
	    	defineMissile(command, parts);
	    } else if (parts[1].equalsIgnoreCase("sensor") && parts[2].equalsIgnoreCase("depth")) {
	    	defineDepth(command, parts);
	    } else if (parts[1].equalsIgnoreCase("sensor") && parts[2].equalsIgnoreCase("distance")) {
	    	defineDistance(command, parts);
	    } else if (parts[1].equalsIgnoreCase("sensor") && parts[2].equalsIgnoreCase("time")) {
	    	defineTime(command, parts);
	    }
	}
	
	/*   0        1         2        3   4    5    6
	 * define munition depth_charge id1 with fuze id2
	 * CommandMunitionDefineDepthCharge
	 */
	public void defineDepthCharge(String command, String[] parts) {
		String idDC = parts[3]; // id1
        AgentID idMunition = new AgentID(idDC);
        
        String idF = parts[6]; // id2
        AgentID idFuze = new AgentID(idF);
        
		//CommandMunitionDefineDepthCharge​(CommandManagers managers, java.lang.String text, AgentID idMunition, AgentID idFuze)
		CommandMunitionDefineDepthCharge defineDepthChargeCommand = new CommandMunitionDefineDepthCharge(CommandManagers.getInstance(), command, idMunition, idFuze);
        CommandManagers.getInstance().schedule(defineDepthChargeCommand);
	}
	
	/*   0        1       2     3   4     5     6    7   8    9       10      11
	 * define munition missile id1 with sensor id2 fuze id3 arming distance distance
	 * CommandMunitionDefineMissile
	 */
	public void defineMissile(String command, String[] parts) {
		//CarrierMunitionMissile cMM = new CarrierMunitionMissile.createCarrier();
		String idMunition = parts[3]; // id1
        AgentID id1 = new AgentID(idMunition);
        
        String idSensor = parts[6]; // id2
        AgentID id2 = new AgentID(idSensor);
        
        String idFuze = parts[8]; // id3
        AgentID id3 = new AgentID(idFuze);
        
        String distance = parts[11];
        DistanceNauticalMiles distance = new DistanceNauticalMiles​(Double.parseDouble(parts[11]));
        
        //CommandMunitionDefineMissile​(CommandManagers managers, java.lang.String text, AgentID idMunition, AgentID idSensor, AgentID idFuze, DistanceNauticalMiles distance)
		CommandMunitionDefineMissile munitionDefineMissile = new CommandMunitionDefineMissile(CommandManagers.getInstance(), command, id1, id2, id3, distance);
        CommandManagers.getInstance().schedule(munitionDefineMissile);
	}
	
	/*   0      1      2    3   4     5      6      7
	 * define sensor depth id with trigger depth altitude
	 * CommandSensorDefineDepth
	 */
	public void defineDepth(String command, String[] parts) {
        AgentID idSensor = new AgentID(parts[3]);
        Altitude altitude = new Altitude(Double.parseDouble(parts[7]));
        
        //CommandSensorDefineDepth​(CommandManagers managers, java.lang.String text, AgentID idSensor, Altitude depth)
		CommandSensorDefineDepth defineDeptheCommand = new CommandSensorDefineDepth(CommandManagers.getInstance(), command, idSensor, altitude);
        CommandManagers.getInstance().schedule(defineDeptheCommand);
	}
	
	/*  0        1      2      3  4      5      6        7
	 * define sensor distance id with trigger distance distance
	 * CommandSensorDefineDistance
	 */
	public void defineDistance(String command, String[] parts) {
		AgentID idSensor = new AgentID(parts[3]);
		DistanceNauticalMiles​ distance = new DistanceNauticalMiles​(Double.parseDouble(parts[7]));
		
		//CommandSensorDefineDistance​(CommandManagers managers, java.lang.String text, AgentID idSensor, DistanceNauticalMiles distance)
		CommandSensorDefineDistance defineDistanceCommand = new CommandSensorDefineDistance(CommandManagers.getInstance(), command, idSensor, distance);
        CommandManagers.getInstance().schedule(defineDistanceCommand);
	}
	
	/*  0       1      2   3   4    5      6     7
	 * define sensor time id with trigger time time
	 * CommandSensorDefineTime
	 */
	public void defineTime(String command, String[] parts) {
		AgentID idSensor = new AgentID(parts[3]);
		double t = Double.parseDouble(parts[7]);
		Time time = new Time(t);
		
		//CommandSensorDefineTime​(CommandManagers managers, java.lang.String text, AgentID idSensor, Time time)
		CommandSensorDefineTime defineTimeCommand = new CommandSensorDefineTime(CommandManagers.getInstance(), command, idSensor, time));
        CommandManagers.getInstance().schedule(defineTimeCommand);
	}
}
