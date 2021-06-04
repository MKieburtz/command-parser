package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.view.CommandViewCreateWindowTop;
import cs350s21project.datatype.AgentID;
import cs350s21project.datatype.Latitude;
import cs350s21project.datatype.Longitude;

public class CreateCommandHandler {
    public void handleCreateCommand(String command) {
        Verifier.verifyCommandHasAtLeastNumArguments(command, 2);
		String[] parts = command.split(" +");
		if(parts[1].equalsIgnoreCase("window")) {
			handleCreateWindowCommand(command, parts);
		}
    }
    
    private void handleCreateWindowCommand(String command, String[] parts) {
		Verifier.verifyCommandHasNumArguments(command, 13);
		if(parts[3].equalsIgnoreCase("top")) {
			createWindowTop(command, parts);
		} else {
			return;
		}
	}
	
	private void createWindowTop(String command, String[] parts) {
		//id
		Verifier.verifyID(parts[2]);
		AgentID id = new AgentID(parts[2]);
		if(!parts[4].equalsIgnoreCase("view") || !parts[5].equalsIgnoreCase("with")) {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (must have \"view with\")");
		}
		//size
		Verifier.verifySize(parts[6]);
		int size = Integer.parseInt(parts[6]);
		if(parts[7].charAt(0) != '(') {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing '(' )");
		}
		//latitude1
		Verifier.verifyLatitude(parts[7].substring(1));
		String editable = parts[7].substring(1);
		int degrees = Integer.parseInt(editable.substring(0, editable.indexOf('*')));
		editable = editable.substring(editable.indexOf('*')+1);
		int minutes = Integer.parseInt(editable.substring(0, editable.indexOf('\'')));
		editable = editable.substring(editable.indexOf('\'')+1);
		double seconds = Double.parseDouble(editable.substring(0, editable.length()-1));
		Latitude lat1 = new Latitude(degrees, minutes, seconds);
		//latitude2
		Verifier.verifyLatitude(parts[8]);
		editable = parts[8];
		degrees = Integer.parseInt(editable.substring(0, editable.indexOf('*')));
		editable = editable.substring(editable.indexOf('*')+1);
		minutes = Integer.parseInt(editable.substring(0, editable.indexOf('\'')));
		editable = editable.substring(editable.indexOf('\'')+1);
		seconds = Double.parseDouble(editable.substring(0, editable.length()-1));
		Latitude lat2 = new Latitude(degrees, minutes, seconds);
		//latitude3
		Verifier.verifyLatitude(parts[9]);
		editable = parts[9];
		degrees = Integer.parseInt(editable.substring(0, editable.indexOf('*')));
		editable = editable.substring(editable.indexOf('*')+1);
		minutes = Integer.parseInt(editable.substring(0, editable.indexOf('\'')));
		editable = editable.substring(editable.indexOf('\'')+1);
		seconds = Double.parseDouble(editable.substring(0, editable.length()-1));
		Latitude lat3 = new Latitude(degrees, minutes, seconds);
		if(parts[9].indexOf(')') == -1) {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing ')' )");
		}
		if(parts[10].charAt(0) != '(') {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing '(' )");
		}
		//longitude1
		Verifier.verifyLongitude(parts[10].substring(1));
		editable = parts[10].substring(1);
		degrees = Integer.parseInt(editable.substring(0, editable.indexOf('*')));
		editable = editable.substring(editable.indexOf('*')+1);
		minutes = Integer.parseInt(editable.substring(0, editable.indexOf('\'')));
		editable = editable.substring(editable.indexOf('\'')+1);
		seconds = Double.parseDouble(editable.substring(0, editable.length()-1));
		Longitude lon1 = new Longitude(degrees, minutes, seconds);
		//longitude2
		Verifier.verifyLongitude(parts[11]);
		editable = parts[11];
		degrees = Integer.parseInt(editable.substring(0, editable.indexOf('*')));
		editable = editable.substring(editable.indexOf('*')+1);
		minutes = Integer.parseInt(editable.substring(0, editable.indexOf('\'')));
		editable = editable.substring(editable.indexOf('\'')+1);
		seconds = Double.parseDouble(editable.substring(0, editable.length()-1));
		Longitude lon2 = new Longitude(degrees, minutes, seconds);
		//longitude3
		Verifier.verifyLongitude(parts[12]);
		editable = parts[12];
		degrees = Integer.parseInt(editable.substring(0, editable.indexOf('*')));
		editable = editable.substring(editable.indexOf('*')+1);
		minutes = Integer.parseInt(editable.substring(0, editable.indexOf('\'')));
		editable = editable.substring(editable.indexOf('\'')+1);
		seconds = Double.parseDouble(editable.substring(0, editable.length()-1));
		Longitude lon3 = new Longitude(degrees, minutes, seconds);
		if(parts[12].indexOf(')') == -1) {
			throw new RuntimeException("Error! Incorrect syntax of create window statement (missing ')' )");
		}
		
		//create Window
		CommandViewCreateWindowTop window = new CommandViewCreateWindowTop(CommandManagers.getInstance(), command, id, size, lat1, lat2, lat3, lon1, lon2, lon3);
		CommandManagers.getInstance().schedule(window);
	}
}
