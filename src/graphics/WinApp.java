package graphics;

/*
 * WinApp is both a JPanel which contains the paint proc and it is an
 * adaptor for the two types of mouse listeners and the key listener.
 *
 * So to build a simple windows app you extend WinApp, override
 * paintComponent() and any of the listening behaviors like mouseClicked()
 * or keyTyped() that you need in your app.
 *
 * It is only intended for simple swing apps that have but a single window
 * frame and a single panel. Because it uses static variables you can NOT
 * have multiple WinApp applications running on your screen at the same time.
 * It is only intended for quick and dirty simple app development and testing.
 */

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class WinApp extends JPanel
        implements MouseListener, MouseMotionListener, KeyListener{
    public static JFrame FRAME;
    public static WinApp PANEL; // JPanel that is also a lisener
    public static String TITLE = "No Name";
    public static Dimension PREF_SIZE = new Dimension(500,400);

    public WinApp(String t, int w, int h){
        TITLE = t; PREF_SIZE = new Dimension(w,h);
    }

    public Dimension getPreferredSize() {return PREF_SIZE;}

    private static void createAndShowGUI(){
        FRAME = new JFrame(TITLE);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.addKeyListener(PANEL); // keyListener added to frame
        FRAME.getContentPane().add(PANEL);
        FRAME.pack();
        FRAME.setVisible(true);
    }
    public static void launch(){
        // add in the listeners first
        PANEL.addMouseListener(PANEL); // mouseListeners added to panel
        PANEL.addMouseMotionListener(PANEL);

        javax.swing.SwingUtilities.invokeLater(
                new Runnable(){ public void run(){createAndShowGUI();} }
        );
    }

    @Override
    public void mouseClicked(MouseEvent me){}
    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    @Override
    public void mouseDragged(MouseEvent me) {}
    @Override
    public void mouseMoved(MouseEvent me) {}

    @Override
    public void keyTyped(KeyEvent ke) {}
    @Override
    public void keyPressed(KeyEvent ke) {}
    @Override
    public void keyReleased(KeyEvent ke) {}
}

