package Erica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author owner
 */
public class PlaylistViewer extends JPanel
{
    private int myWidth = 150;
    private int myHeight = 600;
    Playlist myPlaylist;
    final JList list;

    final JLabel library = new JLabel("Library");
    final JLabel playlist = new JLabel("Playlists");

    
    protected PlaylistViewer() {
        myPlaylist = new Playlist("C:/Users/owner/Desktop/CD ART/playlist.xml");
        list = new JList(myPlaylist.getStringArray());
        setPreferredSize(new Dimension(150,600));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(library);
        add(playlist);
        add(list);
        setVisible(true);
        
    }

    public static void main(String...arg) {
        PlaylistViewer pv = new PlaylistViewer();
        pv.setBackground(Color.red);

        JFrame frame = new JFrame("PlayViewer");

        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(pv);
        frame.add(content);

        frame.setContentPane(content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}