package cs350s21project.cli;

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

        } else if (parts[2].equalsIgnoreCase("depth_charge")) {

        } else if (parts[2].equalsIgnoreCase("torpedo")) {
            defineTorpedo(command, parts);
        } else if (parts[2].equalsIgnoreCase("missile")) {

        }
    }

    private void defineTorpedo(String command, String[] parts) {
        // define the torpedo
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

    }

    private void defineThermalSensor(String command, String[] parts) {

    }

    private void defineAcousticSensor(String command, String[] parts) {

    }

    private void defineShip(String command, String[] parts) {

    }
}
