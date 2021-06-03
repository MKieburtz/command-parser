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
			defineBomb(command, parts);
		} else if (parts[2].equalsIgnoreCase("shell")) {
			defineShell(command, parts);
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
			Verifier.verifyCommandHasAtLeastNumArguments(command, 4);
			if(parts[3].equalsIgnoreCase("active")) {
				defineActiveSonarSensor(command, parts);
			} else if(parts[3].equalsIgnoreCase("passive")) {
				definePassiveSonarSensor(command, parts);
			}
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
		if(!parts[5].equalsIgnoreCase("with") || !parts[5].equalsIgnoreCase("power")) {
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
		if(!parts[5].equalsIgnoreCase("with") || !parts[5].equalsIgnoreCase("sensitivity")) {
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
}
