///====================================
//	Kyle Russell
//	StudentCore
//	Layout
//====================================

package jdamvc.engine.view.layout;

import jdamvc.engine.core.ExceptionOutput;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jdamvc.engine.core.Config;


public class Layout extends JPanel
{
    private final ViewPane viewPane;
    private final Menu menu;
    
    public Layout()
    {
        initLookAndFeel();
        setLayout(new BorderLayout());
        
        viewPane    =   new ViewPane();
        menu        =   new Menu();
        
        add(viewPane, BorderLayout.CENTER);
    }
    
    private void initLookAndFeel()
    {
        try 
        {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
        }
        
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            ExceptionOutput.output("[Exception] Failed to load LookAndFeel: " + e.getMessage(), ExceptionOutput.OutputType.DEBUG);
        }
    }
    
    public ViewPane getViewPane()
    {
        return viewPane;
    }
    
    public Menu getMenu()
    {
        return menu;
    }
    
    public static String getImage(String name)
    {
        return Config.RESOURCE_DIR + name;
    }
}
