//====================================
//	Kyle Russell
//	jdamvc
//	ResponseDataView
//====================================

package jdamvc.engine.view;

import jdamvc.engine.controller.Message;
import jdamvc.engine.core.Path;


public class ResponseDataView implements DataView
{
    protected boolean responseStatus;
    protected String responseMessage;
    protected Message responseData;
    protected int accessLevel;
    protected Path path;
    
    public ResponseDataView(String responseMessage, boolean responseStatus)
    {
        this(responseMessage, responseStatus, new Message(), 0);
    }
    
    public ResponseDataView(String responseMessage, boolean responseStatus, Message responseData, int accessLevel)
    {
        this.responseMessage    =   responseMessage;
        this.responseStatus     =   responseStatus;
        this.responseData       =   responseData;
        this.accessLevel        =   accessLevel;
    }
    
    @Override
    public boolean getResponseStatus()
    {
        return responseStatus;
    }

    @Override
    public String getResponseMessage() 
    {
        /*final int SUCCESS_COLOUR    =   CUITextTools.GREEN;
        final int FAIL_COLOUR       =   CUITextTools.RED;
        
        return (responseStatus)? 
        CUITextTools.changeColour(responseMessage, SUCCESS_COLOUR) : CUITextTools.changeColour(responseMessage, FAIL_COLOUR); */
        return responseMessage;
    }
    
    public String getRawResponseMessage()
    {
        return responseMessage;
    }
    
    @Override
    public void setPath(Path path)
    {
        this.path   =   path;
    }
    
    @Override
    public Path getPath()
    {
        return path;
    }

    @Override
    public Message getResponseData()
    {
        return responseData;
    }

    @Override
    public String getJson() 
    {
        return "";
    }

    @Override
    public void display() 
    {
        System.out.println(getJson());
    }

    @Override
    public int getAccessLevel() 
    {
        return accessLevel;
    }

    @Override
    public Message getViewData() 
    {
        return responseData;
    }

    @Override
    public void fire(String command, Object instance) {}

    @Override
    public View getPrevView() 
    {
        return null;
    }

    @Override
    public View getNextView() 
    {
        return null;
    }

    @Override
    public void setPrevView(View prevView) {}

    @Override
    public void setNextView(View nextView) {}
}
