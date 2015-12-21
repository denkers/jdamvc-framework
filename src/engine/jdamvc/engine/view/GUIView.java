//====================================
//	Kyle Russell
//	jdamvc
//	GUIView
//====================================

package jdamvc.engine.view;

import jdamvc.engine.core.Agent;
import jdamvc.engine.core.ExceptionOutput;
import jdamvc.engine.view.layout.Layout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import jdamvc.engine.controller.Message;


public abstract class GUIView extends AbstractView
{
    protected JPanel panel;
    protected Font helveticaThin;
    protected BufferedImage addSmallImage;
    protected BufferedImage removeSmallImage;
    protected BufferedImage editSmallImage;
    protected BufferedImage successImage;
    protected BufferedImage failImage;
    protected BufferedImage searchSmallImage;
    protected ImageIcon spinnerSmall;
    
    public GUIView()
    {
        this(new Message());
    }
    
    public GUIView(Message viewData)
    {
        super(viewData);
        initGUIView();
    }
    
    @Override
    protected String getListenerPath()
    {
        return null;
    }
    
    protected void initPanel()
    {
        if(panel != null)
            panel.setPreferredSize(Agent.getWindow().getAppLayout().getViewPane().getPreferredSize());
    }
    
    protected abstract void initComponents();
    
    protected abstract void initResources();
    
    protected abstract void initListeners();
    
    protected void initAppResources()
    {
        try
        {
            helveticaThin       =   Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(Layout.getImage("HelveticaThin.ttf")));
            addSmallImage       =   ImageIO.read(new File(Layout.getImage("addSmallIcon.png")));
            removeSmallImage    =   ImageIO.read(new File(Layout.getImage("removeSmallIcon.png")));
            editSmallImage      =   ImageIO.read(new File(Layout.getImage("edit_icon.png")));
            successImage        =   ImageIO.read(new File(Layout.getImage("successicon.png")));
            failImage           =   ImageIO.read(new File(Layout.getImage("failicon.png")));
            searchSmallImage    =   ImageIO.read(new File(Layout.getImage("search_icon.png")));
            spinnerSmall        =   new ImageIcon(Layout.getImage("spinner_small.gif"));
        }
        
        catch(IOException | FontFormatException e)
        {
            ExceptionOutput.output("Loading resource error: " + e.getMessage(), ExceptionOutput.OutputType.MESSAGE);
        }
    }
    
    protected void initGUIView()
    {
        initAppResources();
        initResources();
        initComponents();
        initListeners();
    }
    
    @Override
    public void display()
    {
        panel.revalidate();
        panel.repaint();
    }
    
    public JPanel getPanel()
    {
        return panel;
    }
}
