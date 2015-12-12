//====================================
//	Kyle Russell
//	jdamvc
//	Routes
//====================================

package jdamvc.engine.core;

//------------------------------------------

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
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
    
    private boolean isRouteNode(Node node)
    {
        if(node != null)
            return node.getNodeName().equalsIgnoreCase(ROUTE_TAG);
        else
            return false;
    }
    
    private boolean isRouteGroupNode(Node node)
    {
        if(node != null)
            return node.getNodeName().equalsIgnoreCase(ROUTE_GROUP_TAG);
        else
            return false;
    }
    
    private void initRouteNode(Node node, RouteGroup parent)
    {
        NamedNodeMap attrs      =   node.getAttributes();
        String name             =   attrs.getNamedItem("name").getNodeValue();
        String controllerName   =   attrs.getNamedItem("controller").getNodeValue();
        String methodName       =   attrs.getNamedItem("method").getNodeValue();
        String addr             =   attrs.getNamedItem("path").getNodeValue();
        
        Path path               =   new Path(name, controllerName, methodName, addr);
        if(parent == null)
            add(path);
        
        else
            parent.addPath(path);
    }
    
    private void initRouteGroupNode(Node node, RouteGroup parent)
    {
        String prefix       =   node.getAttributes().getNamedItem("prefix").getNodeValue();
        RouteGroup group    =   new RouteGroup(prefix);
        
        NodeList children   =   node.getChildNodes();
        for(int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i);
            
            if(isRouteNode(childNode))
                initRouteNode(childNode, group);
            
            else if(isRouteGroupNode(childNode))
                initRouteGroupNode(childNode, group);
        }
        
        if(parent != null)
            parent.addGroup(group);
        
        registerGroup(group);
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
            
            if(isRouteNode(node))
                initRouteNode(node, null);
            
            else if(isRouteGroupNode(node))
                initRouteGroupNode(node, null);
        }
    }
}
