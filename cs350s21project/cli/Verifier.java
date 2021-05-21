package cs350s21project.cli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifier {
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
