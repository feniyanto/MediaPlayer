package Erica.tag.ui;

import Erica.tag.TagInfo;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class EmptyDialog extends TagInfoDialog
{
    private TagInfo _info = null;

    public EmptyDialog(String title, int x, int y, int sx, int sy,
            TagInfo ti)
    {
        super(title);
        _info = ti;
        this.setSize(sx,sy);
        if((x != -1) && (y != -1)) this.setLocation(x,y);
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setFont(new Font("Verdana",Font.PLAIN, 12));
        this.setLayout(gridBag);
        c.fill = GridBagConstraints.BOTH;

        // File/URL label
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        Label flabel = new Label("No Information Available",Label.CENTER);
        gridBag.setConstraints(flabel,c);
        this.add(flabel);

        // Standard Tags.
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        Panel mainPan = new Panel();
        gridBag.setConstraints(mainPan, c);
        this.add(mainPan);

        Panel selectPan = new Panel();
        c.weightx = 1.0;
        gridBag.setConstraints(selectPan, c);
        this.add(selectPan);
        mainPan.setLayout(new GridLayout(1,2,0,1));
        // Left
        Panel leftPan = new Panel();
        leftPan.setLayout(new GridLayout(1,1,0,1));
        // Right
        Panel rightPan = new Panel();
        GridBagLayout rGB = new GridBagLayout();
        rightPan.setLayout(rGB);
        GridBagConstraints rC = new GridBagConstraints();
        rC.gridx = 0;
        rC.fill = GridBagConstraints.HORIZONTAL;
        mainPan.add(leftPan);
        mainPan.add(rightPan);
        selectPan.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 4));
        selectPan.add(_close);
    }

    /* Return TagInfo */
    public TagInfo getTagInfo()
    {
        return _info;
    }
}
