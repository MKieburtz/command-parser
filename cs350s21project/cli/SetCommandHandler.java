package cs350s21project.cli;

import cs350s21project.controller.CommandManagers;
import cs350s21project.controller.command.actor.CommandActorLoadMunition;
import cs350s21project.datatype.AgentID;

public class SetCommandHandler {
    public void handleSetCommand(String command) {
        
    }

    private void load(String command, String[] parts) {
        String id = parts[1];
        Verifier.verifyID(id);
        AgentID agentID = new AgentID(id);

        String munitionID = parts[4];
        Verifier.verifyID(munitionID);
        AgentID agentIDMunition = new AgentID(munitionID);

        CommandActorLoadMunition loadCommand = new CommandActorLoadMunition(CommandManagers.getInstance(), command, agentID, agentIDMunition);

        CommandManagers.getInstance().schedule(loadCommand);
    }
}
