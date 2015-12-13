//====================================
//	Kyle Russell
//	jdamvc
//	AbstractView
//====================================

package jdamvc.engine.view;

import jdamvc.engine.controller.ControllerMessage;
import jdamvc.engine.core.CommandInterpreter;
import jdamvc.engine.core.Path;

//------------------------------------
//            ABSTRACTVIEW
//------------------------------------
//- Provides a general View for sub-classes to use
//- Has CommandInterpreter and View functions
//- Has default display() which shows basic view info


public abstract class AbstractView extends CommandInterpreter implements View
{
    protected ControllerMessage viewData; //The messages passed to the view
    protected View nextView; //The next view  if set
    protected View prevView; //The previous view if set
    protected Path path; //The path that was used to fetch the view
    
    //Creates a default view
    //view details should be set later
    public AbstractView()
    {
        this(new ControllerMessage());
    }
    
    public AbstractView(ControllerMessage viewData)
    {
        this.viewData   =   viewData;
    }
    
    
    //Views still need to output their listener file path
    //This path is necessary to load commands for the view
    @Override
    protected abstract String getListenerPath();
    
    //Send a message to the view
    public void passData(String messageName, String message)
    {
        viewData.add(messageName, message);
    }
    
    
    //Returns the messages currently in the view
    @Override
    public ControllerMessage getViewData()
    {
        return viewData;
    }
    
    //Displays a header with view name and description
    //Shows all available commands in the view
    @Override
    public abstract void display();
    
    //Returns the access level of the view
    //Generally all views are accessible
    //If this is not the case override
    @Override
    public int getAccessLevel()
    {
        return 0;
    }
    
    //Returns the previous view
    //May be null if not set
    @Override
    public View getPrevView()
    {
        return prevView;
    }
    
    //Returns the next view
    //May be null if not set
    @Override
    public View getNextView()
    {
        return nextView;
    }
    
    @Override
    public Path getPath()
    {
        return path;
    }
    
    //Set the previous view
    @Override
    public void setPrevView(View prevView)
    {
        this.prevView   =   prevView;
    }
    
    //Set the next view
    @Override
    public void setNextView(View nextView)
    {
        this.nextView   =   nextView;
    }
    
    @Override
    public void setPath(Path path)
    {
        this.path   =   path;
    }
}
