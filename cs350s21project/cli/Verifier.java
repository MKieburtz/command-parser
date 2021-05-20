package cs350s21project.cli;

public class Verifier {
    public static void verifyAltitude(String altitude) {
        // just check if this is a number
        try {
            int a = Integer.parseInt(altitude);
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Error! Invalid altitude: " + altitude);
        }
    }

    public static void verifyCourse(String course) {
        // check if the course is three characters
        if (course.length() != 3) {
            throw new RuntimeException("Error! Invalid course: " + course);
        }
        // if it is then check if it's a number in the range of 0-360
        try {
            int c = Integer.parseInt(course);
            if (c < 0 || c > 360) {
                throw new RuntimeException("Error! Invalid course: " + course);
            }
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Error! Invalid course: " + course);
        }
    }

    public static void verifyElevation(String elevation) {

    }

    public static void verifyLatitude(String latitude) {

    }

    public static void verifyLongitude(String longitude) {

    }
}
