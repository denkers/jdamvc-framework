//====================================
//	Kyle Russell
//	jdamvc
//	CommandInterpreter
//====================================

package jdamvc.engine.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//------------------------------------
//          COMMANDINTERPRETER
//------------------------------------
//- Handles execution and interpretation of commands
//- Loads commands from CommandListener and checks unrecognized commands
//- Has some utilities for showingCommands used by help


public abstract class CommandInterpreter implements CommandExecute
{
    protected Map<String, Command> commands; //Available commands fetched from listener
    
    public CommandInterpreter()
    {
        initCommands();
    }
    
    //Executes a command
    //- commandRaw: full command string passed
    //- instance: class instance to call the method on
    @Override
    public void fire(String commandRaw, Object instance)
    {
        if(commands == null) return;
        
        //Command structure: commandName param1 param2 .. paramN
        //Context triggers are resolved before being passed to interpreter
        List<String> paramList  =   new ArrayList<>(Arrays.asList(commandRaw.split("\\s")));
        String userCommand      =   paramList.get(0); //command name
        Command command         =   commands.get(userCommand); 
        
        //Check if command exists
        //Commands are listed in the classes respective listener file
        if(command == null)
            unrecognizedCommand();
        else
        {
            paramList.remove(0); //seperate command name from params
            String[] params =   paramList.toArray(new String[paramList.size()]);
            command.call(params, instance);
        }
    }
    
    //Prompts a message when the command is not found
    public void unrecognizedCommand()
    {
        String message  =   "Command was not recognized!";        
        ExceptionOutput.output(message, ExceptionOutput.OutputType.MESSAGE);
    }
    
    
    //Implementors should return the path to the listeners json file
    //Listeners can be found in engine\config\listeners
    protected abstract String getListenerPath();
    
    
    //Gets the commands listener path
    //Fetches all commands from the listener
    protected void initCommands()
    {
        String listenerFile     =   getListenerPath();
        
        if(listenerFile != null)
            commands                =   CommandListener.loadFactory(listenerFile).getCommands();
    }
}
