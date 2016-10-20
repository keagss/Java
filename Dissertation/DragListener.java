import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

//this class allows the user to move labels
public class DragListener extends MouseInputAdapter
{
    Point location;
    MouseEvent pressed;

    public void mousePressed(MouseEvent me)
    {
        pressed = me;
    }

    public void mouseDragged(MouseEvent me)
    {
        //changes location of component to new location
        Component component = me.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);
    }
}