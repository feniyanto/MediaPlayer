package Erica;

/**************
Erica Olavarria
December 2011
Data Structures
Final Project
***************
FocusHandler
Class 8 of 16
**************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
  * This code was taken from <http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=5018574>
  * It is a workaround to take the focus off the JOptionPane and place it on a component.
  */
public class FocusHandler implements HierarchyListener{
	public void hierarchyChanged(HierarchyEvent e){
		final Component c = e.getComponent();
		if (c.isShowing() && (e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
			Window toplevel = SwingUtilities.getWindowAncestor(c);
			toplevel.addWindowFocusListener(new WindowAdapter() {
				public void windowGainedFocus(WindowEvent e) {
					c.requestFocus();
				}
			});
		}
	}//end hierarchyChanged
}//end FocusHandler