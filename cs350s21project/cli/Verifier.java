package cs350s21project.cli;

public class Verifier {

    private static void throwError(String datatype, String badValue) {
        throw new RuntimeException("Error! Invalid " + datatype + ": " + badValue);
    }
    
    public static void verifyCoordinates(String coord) {
		if(coord == null || coord.isBlank()) {
			throwError("coordinates", coord);
		}
		String p = ".+\\/.+\\/.+";
		if(Pattern.matches(p, coord)) {
			throwError("coordinates", coord);
		}
		Scanner s = new Scanner(coord);
		s.useDelimiter("/");
		String latitude = s.next();
		verifyLatitude(latitude);
		String longitude = s.next();
		verifyLongitude(longitude);
		String altitude = s.next();
		verifyAltitude(altitude);
		s.close();
	}

	public static void verifyPower(String power) {
		if(power == null || power.isBlank()) {
			throwError("power", power);
		}
		try {
			Double.parseDouble(power);
		} catch(NumberFormatException e) {
			throwError("power", power);
		}
		if(Double.parseDouble(power) < 0) {
			throwError("power", power);
		}
	}
	
	public static void verifySensitivity(String sens) {
		if(sens == null || sens.isBlank()) {
			throwError("sensitivity", sens);
		}
		try {
			Double.parseDouble(sens);
		} catch(NumberFormatException e) {
			throwError("sensitivity", sens);
		}
		if(Double.parseDouble(sens) < 0) {
			throwError("sensitivity", sens);
		}
	}
	
	public static void verifySize(String size) {
		if(size == null || size.isBlank()) {
			throwError("size", size);
		}
		try {
			Integer.parseInt(size);
		} catch(NumberFormatException e) {
			throwError("size", size);
		}
		if(Integer.parseInt(size) < 0) {
			throwError("size", size);
		}
	}
	
	public static void verifySpeed(String speed) {
		if(speed == null || speed.isBlank()) {
			throwError("speed", speed);
		}
		try {
			Double.parseDouble(speed);
		} catch(NumberFormatException e) {
			throwError("speed", speed);
		}
		if(Double.parseDouble(speed) < 0) {
			throwError("speed", speed);
		}
	}
}
