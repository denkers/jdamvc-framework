//====================================
//	Kyle Russell
//	StudentCore
//	Transition
//====================================

package application.views.gui.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import jdamvc.engine.view.layout.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jdamvc.engine.view.GUIView;

public class TransitionView extends GUIView
{
    private static ImageIcon spinnerImage;
    private static ImageIcon smallSpinnerImage;
    private JLabel spinnerLabel;
    
    
    public TransitionView() {}
    
    public static ImageIcon getSpinner()
    {
        return spinnerImage;
    }
    
    public static ImageIcon getSmallSpinner()
    {
        return smallSpinnerImage;
    }

    @Override
    protected void initComponents()
    {
        panel               =   new JPanel(new BorderLayout());
        spinnerLabel        =   new JLabel(spinnerImage);
        panel.setBackground(Color.WHITE);
        panel.add(spinnerLabel, BorderLayout.CENTER);
    }

    @Override
    protected void initResources()
    {
        spinnerImage        =   new ImageIcon(Layout.getImage("loadspinner.gif"));
        smallSpinnerImage   =   new ImageIcon(Layout.getImage("spinner.gif"));
    }

    @Override
    protected void initListeners() {}
}
