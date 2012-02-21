package PlayViewer;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author owner
 */
public class PlayViewer extends JFrame
{
    public PlayViewer() {
        super("Play Viewer");
        setLayout(new FlowLayout());
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        setPreferredSize(dim);
        add(new ImagePanel());
        add(new ImagePanel());
        pack();
        setResizable(true);
        centerFrame();
        setVisible(true);
    }

    // Place this JFrame in the center of the screen
    private void centerFrame()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension sd = tk.getScreenSize();
        Dimension fd = getSize();
        setLocation(sd.width/2 - fd.width/2, sd.height/2 - fd.height/2);
    }  // end of centerFrame()

    public static void main(String...args) {
        new PlayViewer().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
