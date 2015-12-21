//====================================
//	Kyle Russell
//	StudentCore
//	ViewPane
//====================================
package jdamvc.engine.view.layout;

import jdamvc.engine.view.GUIView;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import jdamvc.engine.core.Config;
import jdamvc.engine.core.RouteHandler;


public class ViewPane extends JPanel
{
    private GUIView activeView;
    private final GUIView transitionView;
    
    private final String TRANSITION_VIEW    =   "transition";
    private final String ACTIVE_VIEW        =   "active";
    
    public ViewPane()
    {
        super(new CardLayout());
        setPreferredSize(new Dimension
        (
            Window.getWindowDim().x,
            Window.getWindowDim().y
        ));
        
        transitionView  =   (GUIView) RouteHandler.go(Config.TRANS_VIEW_ROUTE, null);
        
        if(transitionView != null)
        {
            
            transitionView.getPanel().setPreferredSize(getPreferredSize());
            add(transitionView.getPanel(), TRANSITION_VIEW);
        }
        
        setBackground(Color.WHITE);
    }
    
    public GUIView getActiveView()
    {
        return activeView;
    }
    
    public void addPanel(JPanel panel)
    {
        add(panel);
        revalidate();
    }
    
    public void showTransition()
    {
        if(transitionView == null) return;
        
        CardLayout cLayout  =   (CardLayout) getLayout();
        cLayout.show(this, TRANSITION_VIEW);
    }
    
    public void hideTransition()
    {
        if(activeView == null || transitionView == null) return;
        
        CardLayout cLayout  =   (CardLayout) getLayout();
        cLayout.show(this, ACTIVE_VIEW);
    }
    
    public void setActiveView(GUIView view)
    {
        if(view == null) return;
        
        activeView  =   view;
        add(activeView.getPanel(), ACTIVE_VIEW);
    }
}
