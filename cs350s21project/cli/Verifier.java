package cs350s21project.cli;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifier {

	/*
	 * Datatype - DistanceNauticalMiles; example: 10, 25.3; Definition: unsigned real (nautical miles) 
	 */
	public static void verifyDistance(String distance) {
		try {
            double distanceDouble = Double.parseDouble(distance);
            
            if(distanceDouble < 0.0) { //check if time is less than 0.0
            	throwError("DistanceNauticalMiles", distance);
            }
        } catch (NumberFormatException error) {
            throwError("DistanceNauticalMiles", distance);
        }
	}
	
	/*
	 * Datatype - String; example: '/stu/myfilename.txt'; Definition: 'filename' with normal filename punctuation
	 */
	public static void verifyFilename(String filename){
		try {
			File fname = new File(filename);
			if(!fname.exists()) {
				throw new RuntimeException("Error! filename: " + filename + " Does not exist! Please enter a valid filename. Example: /<user>/myfilename.txt");
			}
		} catch(Exception error) {
			throwError("String", filename);
		}
	}
	
	/*
	 * Datatype - FieldOfView; example: 10, 45; Definition: unsigned real (navigational degrees)
	 */
	public static void verifyFov(String fov) {
		try {
            int fovInt = Integer.parseInt(fov);
        } catch (NumberFormatException error) {
            throwError("FieldOfView", fov);
        }
	}
	
	/* 
	 * Datatype - AgentID; example: dog, cat32, dog.cat; Definition: alphanumeric string, plus underscore and dot
	 */
	public static void verifyID(String ID){
		try {
			if(ID == null) {
				throwError("AgentID", ID);
			} 
			if(!ID.matches("^[a-zA-Z0-9+_.]*$")) { // this has been working for me, but im not sure if "+_." will add problems later on - Spencer Metz
				throwError("AgentID", ID);
			}
		} catch(Exception error) {
			throwError("AgentID", ID);
		}
	}
	
	/*
	 * Datatype - Time; example: 5, 10.8; Definition: unsigned time (seconds)
	 */
	public static void verifyTime(String time){
		try {
            double timeDouble = Double.parseDouble(time);
            
            if(timeDouble < 0.0) { //check if time is less than 0.0
    			throwError("Time", time);
            }
		} catch(Exception error) {
			throwError("Time", time);
		}
	}
  
  public static void verifyAltitude(String altitude) {
        // just check if this is a number
        try {
            int altitudeInt = Integer.parseInt(altitude);
        } catch (NumberFormatException ex) {
            throwError("altitude", altitude);
        }
    }

    public static void verifyCourse(String course) {
        // check if the course is three characters
        if (course.length() != 3) {
            throwError("course", course);
        }
        // if it is then check if it's a number in the range of 0-360
        try {
            int courseInt = Integer.parseInt(course);
            if (courseInt < 0 || courseInt >= 360) {
                throwError("course", course);
            }
        } catch (NumberFormatException ex) {
            throwError("course", course);
        }
    }

    public static void verifyElevation(String elevation) {
        // check if it's a number between 0 and 360
        try {
            double elevationInt = Double.parseDouble(elevation);
            if (elevationInt < 0 || elevationInt >= 360) {
                throwError("elevation", elevation);
            }
        } catch (NumberFormatException ex) {
            throwError("elevation", elevation);
        }
    }

    public static void verifyLatitude(String latitude) {
        // checks for 1 or 2 numbers then a * then another 1 or 2 numbers then a ' then
        // another 1 or 2 numbers with an optional decimal.
        if (!Pattern.matches("^(\\d{1,2})\\*(\\d{1,2})'(\\d{1,2}(\\.\\d*)?)\"$", latitude)) {
            throwError("latitude", latitude);
        }

        int degrees = getDegreesFromLatLong(latitude);
        int minutes = getMinutesFromLatLong(latitude);
        double seconds = getSecondsFromLatLong(latitude);

        if (degrees < 0 || degrees > 90) {
            throwError("latitude degrees", latitude);
        }

        if (minutes < 0 || minutes >= 60) {
            throwError("latitude minutes", latitude);
        }

        if (seconds < 0 || seconds >= 60) {
            throwError("latitude seconds", latitude);
        }
    }

    public static void verifyLongitude(String longitude) {
        // checks for 1 to 3 numbers then a * then another 1 or 2 numbers then a ' then
        // another 1 or 2 numbers with an optional decimal.
        if (!Pattern.matches("^(\\d{1,3})\\*(\\d{1,2})'(\\d{1,2}(\\.\\d*)?)\"$", longitude)) {
            throwError("longitude", longitude);
        }

        int degrees = getDegreesFromLatLong(longitude);
        int minutes = getMinutesFromLatLong(longitude);
        double seconds = getSecondsFromLatLong(longitude);

        if (degrees < 0 || degrees > 180) {
            throwError("latitude degrees", longitude);
        }

        if (minutes < 0 || minutes >= 60) {
            throwError("latitude minutes", longitude);
        }

        if (seconds < 0 || seconds >= 60) {
            throwError("latitude seconds", longitude);
        }
    }
	
	public static void verifyCoordinates(String coord) {
		if(coord == null || coord.isBlank()) {
			throwError("coordinates", coord);
		}
		String p = ".+\\/.+\\/.+";
		if(!Pattern.matches(p, coord)) {
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

	public static void verifyCommandHasNumArguments(String command, int arguments) {
		String[] parts = command.split(" +");
		if (parts.length != arguments) {
			throw new RuntimeException("Command has wrong number of arguments: " + command
					+ "\nExpected " + arguments + " got " + parts.length);
		}
	}

	public static void verifyCommandHasAtLeastNumArguments(String command, int minArguments) {
		String[] parts = command.split(" +");
		if (parts.length < minArguments) {
			throw new RuntimeException("Command doesn't have enough arguments: " + command +
					"\nExpected at least " + minArguments + " got " + parts.length);
		}
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

    private static void throwError(String datatype, String badValue) {
        throw new RuntimeException("Error! Invalid " + datatype + ": " + badValue);
    }
}
