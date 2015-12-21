//====================================
//	Kyle Russell
//	jdamvc
//	Controller
//====================================

package jdamvc.engine.controller;

import jdamvc.engine.core.Path;
import jdamvc.engine.view.View;


//------------------------------------------
//             CONTROLLER
//------------------------------------------
//- Controller provides the bridge between routes and views
//- Methods in Controller children should interface routes


public abstract class Controller
{
    //Defines the type of request for a controller call
    public enum RequestType
    {
        GET, //Requesting data from a source
        POST //Submitting data to a source
    }
    
    protected Message controllerData; //the passed input data for a post request
    protected RequestType requestType; //immediate request type (GET, POST)
    protected Path path; //the controller sessions path
    
    public Controller()
    {
        controllerData  =   new Message();
        requestType     =   RequestType.GET;
        path            =   null;
    }      
    
    //Returns the controller sessions input data
    //Data may be empty if GET request
    protected Message getPostData()
    {
        return controllerData;
    }
    
    //Returns the controllers request type
    public RequestType getRequestType()
    {
        return requestType;
    }
    
    //Returns true if the controller session has input data
    public boolean hasPostData()
    {
        return controllerData.hasMessages();
    }

    //Returns the path that was used to create the controller
    public Path getPath()
    {
        return path;
    }
    
    public View prepareView(View view)
    {
        view.setPath(path);
        return view;
    }
    
    //Checks post data such that it must contain all expected keys
    protected boolean validatePostData(String[] expectedNames)
    {
        if(!hasPostData()) return false;
        else
        {
            boolean validate = true;
            for(String name : expectedNames)
                if(!controllerData.messageExists(name)) 
                    validate = false;
            
            return validate;
        }
    }
    
    public void setControllerData(Message controllerData)
    {
        this.controllerData =   controllerData;
    }
    
    public void setPath(Path path)
    {
        this.path   =   path;
    }
}
