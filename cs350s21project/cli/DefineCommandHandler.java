package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorDefineShip;
import cs350s21project.controller.command.munition.CommandMunitionDefineTorpedo;
import cs350s21project.controller.command.sensor.CommandSensorDefineAcoustic;
import cs350s21project.controller.command.sensor.CommandSensorDefineRadar;
import cs350s21project.controller.command.sensor.CommandSensorDefineThermal;
import cs350s21project.datatype.*;

import java.util.ArrayList;

public class DefineCommandHandler {
    public void handleDefineCommand(String command) {
        String[] parts = command.split(" +");

        if (parts[1].equalsIgnoreCase("ship")) {
            Verifier.verifyCommandHasAtLeastNumArguments(command, 6);
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

        } else if (parts[2].equalsIgnoreCase("depth_charge")) {

        } else if (parts[2].equalsIgnoreCase("torpedo")) {
            Verifier.verifyCommandHasNumArguments(command, 12);
            defineTorpedo(command, parts);
        } else if (parts[2].equalsIgnoreCase("missile")) {

        }
    }

    private void defineTorpedo(String command, String[] parts) {
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

        } else if (parts[2].equalsIgnoreCase("distance")) {

        } else if (parts[2].equalsIgnoreCase("time")) {

        }
    }

    private void defineRadarSensor(String command, String[] parts) {
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
}
