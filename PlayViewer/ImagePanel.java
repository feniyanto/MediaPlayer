package PlayViewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
    Map desktopHints = null;
    private int PWIDTH = -1;
    private int PHEIGHT = -1;
    private JPanel viewer;
    private static final Color bgColor = Color.BLACK;
    String prefix = "C:/Users/owner/Desktop/";
    String imgPaths = prefix+"DMB - ART.jpg";

    public ImagePanel() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        desktopHints = (Map)(tk.getDesktopProperty("awt.font.desktophints"));

        Dimension dim = tk.getScreenSize();
        PWIDTH = dim.width;
        PHEIGHT = dim.height;

        new ImageLoadingWorker(viewer, imgPaths).execute();
        
        setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.ORANGE);

        g2d.setFont(new Font("Andy",Font.BOLD,44));
        g2d.drawString("SONG ARTIST", (PWIDTH/4) - 50, 20);
        if (desktopHints != null) {
            g2d.addRenderingHints(desktopHints);
        }
        g2d.drawString("SONG ALBUM", (PWIDTH/4) - 50, (PHEIGHT/4));
        g2d.drawImage(new ImageLoadingWorker(viewer,imgPaths).doInBackground().get(0), null, this);
        // g.drawImage();
    }
}
