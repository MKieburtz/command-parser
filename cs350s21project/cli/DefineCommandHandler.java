// Team 6
// Michael Kieburtz
// Spencer Metz
// Logan Roylance

package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;

import cs350s21project.controller.command.actor.CommandActorDefineShip;
import cs350s21project.controller.command.munition.*;
import cs350s21project.controller.command.sensor.*;
import cs350s21project.datatype.*;

import java.util.ArrayList;

public class DefineCommandHandler {
    public void handleDefineCommand(String command, String[] parts) {
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
            defineBomb(command, parts);
        } else if (parts[2].equalsIgnoreCase("shell")) {
            defineShell(command, parts);
        } else if (parts[2].equalsIgnoreCase("depth_charge") ) {
            defineDepthCharge(command, parts);
        }  else if (parts[2].equalsIgnoreCase("torpedo")) {
            defineTorpedo(command, parts);
        } else if (parts[2].equalsIgnoreCase("missile")) {
            defineMissile(command, parts);
        }
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
			if (parts[3].equalsIgnoreCase("active")) {
				defineActiveSonarSensor(command, parts);
			} else if(parts[3].equalsIgnoreCase("passive")) {
				definePassiveSonarSensor(command, parts);
			}
        } else if (parts[2].equalsIgnoreCase("depth")) {
            defineDepthSensor(command, parts);
        } else if (parts[2].equalsIgnoreCase("distance")) {
            defineDistanceSensor(command, parts);
        } else if (parts[2].equalsIgnoreCase("time")) {
            defineTimeSensor(command, parts);
        }
    }

    private void defineTorpedo(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 12);
        String torpedoID = parts[3];
        Verifier.verifyID(torpedoID);
        AgentID agentIDTorpedo = new AgentID(torpedoID);

        String sensorID = parts[6];
        Verifier.verifyID(sensorID);
        AgentID agentIDSensor = new AgentID(sensorID);

        String fuzeID = parts[8];
        Verifier.verifyID(fuzeID);
        AgentID agentIDFuze = new AgentID(fuzeID);

        String armingTimeString = parts[11];
        Verifier.verifyTime(armingTimeString);
        Time armingTime = new Time(Double.parseDouble(armingTimeString));

        CommandMunitionDefineTorpedo defineTorpedoCommand = new CommandMunitionDefineTorpedo(
                CommandManagers.getInstance(), command, agentIDTorpedo, agentIDSensor, agentIDFuze, armingTime);

        CommandManagers.getInstance().schedule(defineTorpedoCommand);
    }

    private void defineRadarSensor(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 13);
        String radarID = parts[3];
        Verifier.verifyID(radarID);
        AgentID agentIDRadar = new AgentID(radarID);

        String fovString = parts[8];
        Verifier.verifyFov(fovString);
        AngleNavigational angleNavigational = new AngleNavigational(Double.parseDouble(fovString));
        FieldOfView fov = new FieldOfView(angleNavigational);

        String powerString = parts[10];
        Verifier.verifyPower(powerString);
        Power power = new Power(Double.parseDouble(powerString));

        String sensitivityString = parts[12];
        Verifier.verifySensitivity(sensitivityString);
        Sensitivity sensitivity = new Sensitivity(Double.parseDouble(sensitivityString));

        CommandSensorDefineRadar defineRadarCommand = new CommandSensorDefineRadar(
                CommandManagers.getInstance(), command, agentIDRadar, fov, power, sensitivity);

        CommandManagers.getInstance().schedule(defineRadarCommand);
    }

    private void defineThermalSensor(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 11);
        String thermalID = parts[3];
        Verifier.verifyID(thermalID);
        AgentID agentIDThermal = new AgentID(thermalID);

        String fovString = parts[8];
        Verifier.verifyFov(fovString);
        AngleNavigational angleNavigational = new AngleNavigational(Double.parseDouble(fovString));
        FieldOfView fov = new FieldOfView(angleNavigational);

        String sensitivityString = parts[10];
        Verifier.verifySensitivity(sensitivityString);
        Sensitivity sensitivity = new Sensitivity(Double.parseDouble(sensitivityString));

        CommandSensorDefineThermal defineThermalCommand = new CommandSensorDefineThermal(
                CommandManagers.getInstance(), command, agentIDThermal, fov, sensitivity);

        CommandManagers.getInstance().schedule(defineThermalCommand);
    }

    private void defineAcousticSensor(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 7);
        String acousticID = parts[3];
        Verifier.verifyID(acousticID);
        AgentID agentIDAcoustic = new AgentID(acousticID);

        String sensitivityString = parts[6];
        Verifier.verifySensitivity(sensitivityString);
        Sensitivity sensitivity = new Sensitivity(Double.parseDouble(sensitivityString));

        CommandSensorDefineAcoustic defineAcousticCommand = new CommandSensorDefineAcoustic(
                CommandManagers.getInstance(), command, agentIDAcoustic, sensitivity);

        CommandManagers.getInstance().schedule(defineAcousticCommand);
    }

    private void defineShip(String command, String[] parts) {
        Verifier.verifyCommandHasAtLeastNumArguments(command, 6);
        String id = parts[2];
        Verifier.verifyID(id);
        AgentID agentID = new AgentID(id);

        ArrayList<AgentID> munitionIds = new ArrayList<>();
        for (int i = 5; i < parts.length; i++) {
            String part = parts[i];
            part = part.replaceAll("\\)|\\(", "");
            Verifier.verifyID(part);
            AgentID munitionID = new AgentID(part);
            munitionIds.add(munitionID);
        }
        CommandActorDefineShip defineShipCommand = new CommandActorDefineShip(CommandManagers.getInstance(), command, agentID, munitionIds);
        CommandManagers.getInstance().schedule(defineShipCommand);
    }
    
    private void defineBomb(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 4);
		//id
		Verifier.verifyID(parts[3]);
		AgentID id = new AgentID(parts[3]);
		
		//create command
		CommandMunitionDefineBomb bomb = new CommandMunitionDefineBomb(CommandManagers.getInstance(), command, id);
		CommandManagers.getInstance().schedule(bomb);
	}
	
	private void defineShell(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 4);
		//id
		Verifier.verifyID(parts[3]);
		AgentID id = new AgentID(parts[3]);
		
		//create command
		CommandMunitionDefineShell shell = new CommandMunitionDefineShell(CommandManagers.getInstance(), command, id);
		CommandManagers.getInstance().schedule(shell);
	}
	
	private void defineActiveSonarSensor(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 10);
		//id
		Verifier.verifyID(parts[4]);
		AgentID id = new AgentID(parts[4]);
		//"with power"
		if (!(parts[5].equalsIgnoreCase("with") & parts[6].equalsIgnoreCase("power"))) {
			throw new RuntimeException("Error! Syntax error on define active sonar sensor command (missing or incorrect \"with power\")");
		}
		//power
		Verifier.verifyPower(parts[7]);
		double d = Double.parseDouble(parts[7]);
		Power power = new Power(d);
		//"sensitivity"
		if(!parts[8].equalsIgnoreCase("sensitivity")) {
			throw new RuntimeException("Error! Syntax error on define active sonar sensor command (missing or incorrect \"sensitivity\")");
		}
		//sensitivity
		Verifier.verifySensitivity(parts[9]);
		d = Double.parseDouble(parts[9]);
		Sensitivity sens = new Sensitivity(d);
		
		//create command
		CommandSensorDefineSonarActive sensor = new CommandSensorDefineSonarActive(CommandManagers.getInstance(), command, id, power, sens);
		CommandManagers.getInstance().schedule(sensor);
	}
	
	private void definePassiveSonarSensor(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 8);
		//id
		Verifier.verifyID(parts[4]);
		AgentID id = new AgentID(parts[4]);
		//"with sensitivity"
		if (!(parts[5].equalsIgnoreCase("with") && parts[6].equalsIgnoreCase("sensitivity"))) {
			throw new RuntimeException("Error! Syntax error on define passive sonar sensor command (missing or incorrect \"with sensitivity\")");
		}
		//sensitivity
		Verifier.verifySensitivity(parts[7]);
		double d = Double.parseDouble(parts[7]);
		Sensitivity sens = new Sensitivity(d);

		//create command
		CommandSensorDefineSonarPassive sensor = new CommandSensorDefineSonarPassive(CommandManagers.getInstance(), command, id, sens);
		CommandManagers.getInstance().schedule(sensor);
	}

    public void defineDepthCharge(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 7);
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
        Verifier.verifyCommandHasNumArguments(command, 12);
        String idMunition = parts[3]; // id1
        Verifier.verifyID(idMunition);
        AgentID id1 = new AgentID(idMunition);

        String idSensor = parts[6]; // id2
        Verifier.verifyID(idSensor);
        AgentID id2 = new AgentID(idSensor);

        String idFuze = parts[8]; // id3
        Verifier.verifyID(idFuze);
        AgentID id3 = new AgentID(idFuze);

        String distanceString = parts[11];
        Verifier.verifyDistance(distanceString);
        DistanceNauticalMiles distance = new DistanceNauticalMiles(Double.parseDouble(distanceString));

        //CommandMunitionDefineMissile(CommandManagers managers, java.lang.String text, AgentID idMunition, AgentID idSensor, AgentID idFuze, DistanceNauticalMiles distance)
        CommandMunitionDefineMissile munitionDefineMissile = new CommandMunitionDefineMissile(CommandManagers.getInstance(), command, id1, id2, id3, distance);
        CommandManagers.getInstance().schedule(munitionDefineMissile);
    }

    public void defineDepthSensor(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 8);
        String id1 = parts[3];
        Verifier.verifyID(id1);
        AgentID idSensor = new AgentID(id1);

        String altitudeString = parts[7];
        Verifier.verifyAltitude(altitudeString);
        Altitude altitude = new Altitude(Double.parseDouble(altitudeString));

        //CommandSensorDefineDepth(CommandManagers managers, java.lang.String text, AgentID idSensor, Altitude depth)
        CommandSensorDefineDepth defineDeptheCommand = new CommandSensorDefineDepth(CommandManagers.getInstance(), command, idSensor, altitude);
        CommandManagers.getInstance().schedule(defineDeptheCommand);
    }


    public void defineDistanceSensor(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 8);
        String id1 = parts[3];
        Verifier.verifyID(id1);
        AgentID idSensor = new AgentID(id1);

        String distanceString = parts[7];
        Verifier.verifyDistance(distanceString);
        DistanceNauticalMiles distance = new DistanceNauticalMiles(Double.parseDouble(distanceString));

        //CommandSensorDefineDistance(CommandManagers managers, java.lang.String text, AgentID idSensor, DistanceNauticalMiles distance)
        CommandSensorDefineDistance defineDistanceCommand = new CommandSensorDefineDistance(CommandManagers.getInstance(), command, idSensor, distance);
        CommandManagers.getInstance().schedule(defineDistanceCommand);
    }

    public void defineTimeSensor(String command, String[] parts) {
        Verifier.verifyCommandHasNumArguments(command, 8);
        String idSensorString = parts[3];
        Verifier.verifyID(idSensorString);
        AgentID idSensor = new AgentID(idSensorString);

        String timeString = parts[7];
        Verifier.verifyTime(timeString);
        Time time = new Time(Double.parseDouble(timeString));

        //CommandSensorDefineTime(CommandManagers managers, java.lang.String text, AgentID idSensor, Time time)
        CommandSensorDefineTime defineTimeCommand = new CommandSensorDefineTime(CommandManagers.getInstance(), command, idSensor, time);
        CommandManagers.getInstance().schedule(defineTimeCommand);
    }
}
