//====================================
//	Kyle Russell
//	StudentCore
//	Window
//====================================

package jdamvc.application.views.gui.layout;

import jdamvc.application.config.AppConfig;
import jdamvc.engine.view.GUIView;
import java.awt.Point;
import javax.swing.JFrame;


public class Window extends JFrame
{
    private final Layout layout;
    
    public Window()
    {
        super(AppConfig.APP_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        layout      =   new Layout();
        getContentPane().add(layout);
        layout.getMenu().attachTo(this);
        
        Point dim   =   getWindowDim();
        setSize(dim.x, dim.y);
        
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    public void display()
    {
        setVisible(true);
    }
    
    public static Point getWindowDim()
    {
        return new Point(AppConfig.WINDOW_WIDTH, AppConfig.WINDOW_HEIGHT);
    }
    
    public Layout getAppLayout()
    {
        return layout;
    }
    
    public void setActiveView(GUIView view)
    {
        layout.getViewPane().setActiveView(view);
    }
}
