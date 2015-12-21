///====================================
//	Kyle Russell
//	StudentCore
//	Layout
//====================================

package jdamvc.engine.view.layout;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import jdamvc.engine.core.Config;


public class Layout extends JPanel
{
    private final ViewPane viewPane;
    
    public Layout()
    {
        setLayout(new BorderLayout());
        
        viewPane    =   new ViewPane();        
        add(viewPane, BorderLayout.CENTER);
    }    
    
    public ViewPane getViewPane()
    {
        return viewPane;
    }     
    
    public static String getImage(String name)
    {
        return Config.RESOURCE_DIR + name;
    }
}
