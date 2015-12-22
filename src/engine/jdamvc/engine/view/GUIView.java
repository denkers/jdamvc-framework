//====================================
//	Kyle Russell
//	jdamvc
//	GUIView
//====================================

package jdamvc.engine.view;

import jdamvc.engine.core.Agent;
import java.awt.Font;
import java.awt.image.BufferedImage;
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
    
    protected void initAppResources() {}
    
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
