//====================================
//	Kyle Russell
//	jdamvc
//	BaseController
//====================================

package application.controllers;

import application.views.gui.layout.ErrorView;
import application.views.gui.layout.TransitionView;
import jdamvc.engine.controller.Controller;
import jdamvc.engine.controller.ControllerMessage;
import jdamvc.engine.view.View;

public class MasterController extends Controller 
{       
    public View getTransitionView()
    {
        return new TransitionView();
    }
    
    //Returns an error view
    //The passed errorMessage is displayed in the views title
    //GUI Only - CUI should report errors normally
    public View getErrorView(String errorMessage)
    {
        ControllerMessage data  =   new ControllerMessage();
        data.add("errorMessage", errorMessage);
        return new ErrorView(data);
    }
}
