//====================================
//	Kyle Russell
//	jdamvc
//	BaseController
//====================================

package application.controllers;

import jdamvc.engine.controller.Controller;
import jdamvc.engine.controller.ControllerMessage;
import jdamvc.engine.core.Path;
import jdamvc.engine.view.View;

public class MasterController extends Controller 
{
    public MasterController(Path path)
    {
        super(path);
    }
    
    public MasterController(ControllerMessage postData, Path path)
    {
        super(postData, path);
    }
    
    public MasterController(ControllerMessage postData, Controller.RequestType requestType, Path path)
    {
        super(postData, requestType, path);
    }
    
    
    //Returns an error view
    //The passed errorMessage is displayed in the views title
    //GUI Only - CUI should report errors normally
    public View getErrorView(String errorMessage)
    {
        ControllerMessage data  =   new ControllerMessage();
        data.add("errorMessage", errorMessage);
        return prepareView(new ErrorView(data));
    }
}
