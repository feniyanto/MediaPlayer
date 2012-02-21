package Erica.tag.ui;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class defines a Dialog for TagInfo to display
 * @author owner
 */
public class TagInfoDialog extends Dialog implements ActionListener
{
    protected Button _close = null;

    public TagInfoDialog(String title) {
        super(new Frame(), title, true);
        _close = new Button("Close");
        _close.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == _close) {
            this.dispose();
            System.exit(0);
        }
    }
}
