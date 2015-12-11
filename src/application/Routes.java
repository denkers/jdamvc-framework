//====================================
//	Kyle Russell
//	jdamvc
//	Routes
//====================================

package jdamvc.application;

import jdamvc.engine.core.Router;

//------------------------------------------
//                ROUTES
//------------------------------------------
//- Routes define paths in the application
//- Routes map to a controller that handles behaviour
//- Routes can be accessed by name and location
//- Paramaters can be passed where necessary
//- Filters can be used to restrict access to a route
//- Groups can be defined to cluster similar routes

public class Routes extends Router 
{
    public Routes()
    {
        super();
    }
    
    //Define the applications routes in here
    //Routes only need to be defined once
    @Override
    protected void initRoutes()
    {
        add("getErrorPage", "BaseController", "getErrorView", "/error");
        add("getHome", "MasterController", "getHomeView", "/home");
    }
}
