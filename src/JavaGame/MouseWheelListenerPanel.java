package JavaGame;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelListenerPanel extends JPanel implements MouseWheelListener {
    MouseWheelListenerPanel()
    {
        addMouseWheelListener(this);
    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if (e.isControlDown())
        {
            if (e.getWheelRotation() < 0)
            {
                System.out.println("mouse wheel Up");
            }
            else
            {
                System.out.println("mouse wheel Down");
            }
        }
        else
        {
            getParent().dispatchEvent(e);
        }

    }
}
