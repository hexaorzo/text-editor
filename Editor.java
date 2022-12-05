import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class Editor extends JFrame implements ActionListener 
{
	JTextArea t;    // Text component
	JFrame f;       // Frame
	Editor()        // Constructor
	{
		f = new JFrame("editor");       // Create a frame
		try
        	{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");        // Set look and feel
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());                         // Set theme
		}
		catch (Exception e) 
        	{
            		e.printStackTrace();
		}
		t = new JTextArea();                // Text component
		JMenuBar mb = new JMenuBar();       // Create a menubar
		JMenu m1 = new JMenu("File");       // Create a menu for menu list
        	// Create menu items
		JMenuItem mn = new JMenuItem("New");
		JMenuItem mo = new JMenuItem("Open");
		JMenuItem ms = new JMenuItem("Save");
		JMenuItem mp = new JMenuItem("Print"); 
		// Create menubar items
		JButton mx = new JButton("Cut");
		JButton mc = new JButton("Copy");
		JButton mv = new JButton("Paste");
        	JButton mcl = new JButton("Close");
	        // Make buttons transparent
        	mx.setOpaque(false);
        	mx.setContentAreaFilled(false);
        	mx.setBorderPainted(false);
        	mc.setOpaque(false);
        	mc.setContentAreaFilled(false);
        	mc.setBorderPainted(false);
        	mv.setOpaque(false);
        	mv.setContentAreaFilled(false);
        	mv.setBorderPainted(false);
        	mcl.setOpaque(false);
        	mcl.setContentAreaFilled(false);
        	mcl.setBorderPainted(false);
        	// Add action listener
		mn.addActionListener(this);
		mo.addActionListener(this);
		ms.addActionListener(this);
		mp.addActionListener(this);
        	mx.addActionListener(this);
		mc.addActionListener(this);
		mv.addActionListener(this);
		mcl.addActionListener(this);
        	// Add menu items to menu
        	m1.add(mn);
		m1.add(mo);
		m1.add(ms);
		m1.add(mp);
        	// Add buttons to menubar
		mb.add(m1);
		mb.add(mx);
        	mb.add(mc);
        	mb.add(mv);
		mb.add(mcl);
		f.setJMenuBar(mb);
		f.add(t);
		f.setSize(800, 500);
        	f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)                  // If a button is pressed
	{
		String s = e.getActionCommand();
		if (s.equals("Cut"))
			t.cut();
		else if (s.equals("Copy")) 
			t.copy();
		else if (s.equals("Paste"))
			t.paste();
		else if (s.equals("Save")) 
        	{
			JFileChooser j = new JFileChooser("f:");            // Create an object of JFileChooser class
			int r = j.showSaveDialog(null);                     // Invoke the showsSaveDialog function to show the save dialog
			if (r == JFileChooser.APPROVE_OPTION) 
            		{
				File fi = new File(j.getSelectedFile().getAbsolutePath());          // Set the label to the path of the selected directory
				try 
                		{
					FileWriter wr = new FileWriter(fi, false);  // Create a file writer
					BufferedWriter w = new BufferedWriter(wr);  // Create buffered writer to write
					w.write(t.getText());                       // Write
					w.flush();
					w.close();
				}
				catch (Exception evt) 
                		{
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			else                                                // If the user cancelled the operation
				JOptionPane.showMessageDialog(f, "The user cancelled the operation!");
		}
		else if (s.equals("Print")) 
        	{
			try 
            		{
				t.print();                                      // Print the file
			}
			catch (Exception evt) 
            		{
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		else if (s.equals("Open")) 
        	{
			JFileChooser j = new JFileChooser("f:");            // Create an object of JFileChooser class
			int r = j.showOpenDialog(null);                     // Invoke the showsOpenDialog function to show the save dialog
			if (r == JFileChooser.APPROVE_OPTION)               // If the user selects a file
            		{
				File fi = new File(j.getSelectedFile().getAbsolutePath());          // Set the label to the path of the selected directory
				try 
                		{
					String s1 = "", sl = "";
					FileReader fr = new FileReader(fi);
					BufferedReader br = new BufferedReader(fr);
					sl = br.readLine();                         // Initialize sl
					while ((s1 = br.readLine()) != null)        // Take the input from the file
                    			{
						sl = sl + "\n" + s1;
					}					
					t.setText(sl);          // Set the text
				}
				catch (Exception evt) 
                		{
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			else        // If the user cancelled the operation
				JOptionPane.showMessageDialog(f, "You cancelled the operation");
		}
		else if (s.equals("New")) 
        	{
			t.setText("");
		}
		else if (s.equals("Close")) 
        	{
			f.setVisible(false);
		}
	}
	public static void main(String args[])              // Main class
	{
        	Editor e = new Editor();
	}
}
