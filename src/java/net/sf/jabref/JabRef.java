/*
Copyright (C) 2003 Morten O. Alver, Nizar N. Batada

All programs in this directory and
subdirectories are published under the GNU General Public License as
described below.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or (at
your option) any later version.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
USA

Further information about the GNU GPL is available at:
http://www.gnu.org/copyleft/gpl.ja.html

*/
package net.sf.jabref;
import java.io.File;
import java.awt.Font;
import java.awt.Color;
import javax.swing.*;
//import javax.swing.UIManager;
//import javax.swing.UIDefaults;
//import javax.swing.UnsupportedLookAndFeelException;

public class JabRef {

    public static void main(String[] args) {

	//Font fnt = new Font("plain", Font.PLAIN, 12);
	Object fnt = new UIDefaults.ProxyLazyValue
	    ("javax.swing.plaf.FontUIResource", null,
	     new Object[] {"plain", new Integer(Font.PLAIN), new Integer(12)});

	UIManager.put("Button.font", fnt);
	UIManager.put("ToggleButton.font", fnt);
	UIManager.put("RadioButton.font", fnt);
	UIManager.put("CheckBox.font", fnt);
	UIManager.put("ColorChooser.font", fnt);
	UIManager.put("ComboBox.font", fnt);
	UIManager.put("Label.font", fnt);
	UIManager.put("List.font", fnt);
	UIManager.put("MenuBar.font", fnt);
	UIManager.put("MenuItem.font", fnt);
	UIManager.put("RadioButtonMenuItem.font", fnt);
	UIManager.put("CheckBoxMenuItem.font", fnt);
	UIManager.put("Menu.font", fnt);
	UIManager.put("PopupMenu.font", fnt);
	UIManager.put("OptionPane.font", fnt);
	UIManager.put("Panel.font", fnt);
	UIManager.put("ProgressBar.font", fnt);
	UIManager.put("ScrollPane.font", fnt);
	UIManager.put("Viewport.font", fnt);
	UIManager.put("TabbedPane.font", fnt);
	UIManager.put("Table.font", fnt);
	UIManager.put("TableHeader.font", fnt);
	UIManager.put("TextField.font", fnt);
	UIManager.put("PasswordField.font", fnt);
	UIManager.put("TextArea.font", fnt);
	UIManager.put("TextPane.font", fnt);
	UIManager.put("EditorPane.font", fnt);
	UIManager.put("TitledBorder.font", fnt);
	UIManager.put("ToolBar.font", fnt);
	UIManager.put("ToolTip.font", fnt);
	UIManager.put("Tree.font", fnt);

	//String osName = System.getProperty("os.name", "def");
	if (Globals.osName.equals(Globals.MAC)) {
	    Util.pr("Disabling Kunststoff look & feel on Mac OS X.");
	} else {
	    try {
		LookAndFeel lnf = new com.incors.plaf.kunststoff.KunststoffLookAndFeel();
		//com.incors.plaf.kunststoff.KunststoffLookAndFeel.setCurrentTheme(new com.incors.plaf.kunststoff.themes.KunststoffDesktopTheme());
		UIManager.setLookAndFeel(lnf);
	    } catch (UnsupportedLookAndFeelException ex) {}
	}

	JabRefPreferences prefs = new JabRefPreferences();
        /*if (!prefs.get("columnNames").substring(0,1).equals(GUIGlobals.NUMBER_COL)) {
          prefs.put("columnNames", GUIGlobals.NUMBER_COL+";"+prefs.get("columnNames"));
          prefs.put("columnWidths", GUIGlobals.NUMBER_COL_LENGTH+";"+prefs.get("columnWidths"));
        }*/
	BibtexEntryType.loadCustomEntryTypes(prefs);
	Globals.setLanguage(prefs.get("language"), "");
	GUIGlobals.CURRENTFONT = new Font
	    (prefs.get("fontFamily"), prefs.getInt("fontStyle"),
	     prefs.getInt("fontSize"));

	JabRefFrame jrf = new JabRefFrame();

	if(args.length > 0){
	    System.out.println("Opening: " + args[0]);
	    jrf.output("Opening: " + args[0]);
	    //verify the file
	    File f = new File (args[0]);

	    if( f.exists() && f.canRead() && f.isFile()) {
		jrf.fileToOpen=f;
		jrf.openDatabaseAction.openIt(true);
	    }else{
		System.err.println("Error" + args[0] + " is not a valid file or is not readable");
		//JOptionPane...
	    }


	}else{//no arguments (this will be for later and other command line switches)
	    // ignore..
	}
      /*
      BibtexEntry b1 = new BibtexEntry("ee", BibtexEntryType.ARTICLE);
      BibtexEntry b2 = new BibtexEntry("eee", BibtexEntryType.ARTICLE);
      BibtexEntry b3 = new BibtexEntry("eeee", BibtexEntryType.ARTICLE);

      b1.setField("author", "M. O. Alver");
      b2.setField("author", "Alver, Morten");
      b3.setField("author", "Morten Alver and Fredrik Skagen");

      b1.setField("title", "Ole Brumm");
      b2.setField("title", "Ole Brumm");
      b2.setField("journal", "Ole Brumm");
      b3.setField("title", "Ole Brumm er i skagen");
      float threshold = 0.8f;
      Util.pr(""+Util.isDuplicate(b1, b2, threshold)+
              "\n"+Util.isDuplicate(b1, b3, threshold)+
              "\n"+Util.isDuplicate(b2, b3, threshold));
      */
      }

}
