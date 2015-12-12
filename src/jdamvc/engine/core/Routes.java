//====================================
//	Kyle Russell
//	jdamvc
//	Routes
//====================================

package jdamvc.engine.core;

//------------------------------------------

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    
    private static final String ROUTE_TAG       =   "Route";
    
    private static final String ROUTE_GROUP_TAG =   "RouteGroup";
    
    private void initRoute(Node node)
    {
        
    }
    
    private void initRouteGroup(Node node)
    {
        
    }
    
    //Define the applications routes in here
    //Routes only need to be defined once
    @Override
    protected void initRoutes()
    {
        String routePath    =   "application/Routes.xml";
        Document doc        =   XMLParser.getDocument(routePath);
        
        NodeList childNodes     =   doc.getChildNodes();
        int numNodes            =   childNodes.getLength();
        
        for(int i = 0; i < numNodes; i++)
        {
            Node node   =   childNodes.item(i);
            String tag  =   node.getNodeName();
            
            if(tag.equalsIgnoreCase(ROUTE_TAG))
                initRoute(node);
            
            else if(tag.equalsIgnoreCase(ROUTE_GROUP_TAG))
                initRouteGroup(node);
        }
    }
}
