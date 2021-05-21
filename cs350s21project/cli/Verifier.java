package cs350s21project.cli;

import java.io.File;

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
	
	private static void throwError(String datatype, String badValue) {
        throw new RuntimeException("Error! Invalid " + datatype + ": " + badValue);
    }
}
