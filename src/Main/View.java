package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;

public class View extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	static StationsZones zone = new StationsZones();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea stationDetails = new JTextArea();
		stationDetails.setText("Enter Undergound Name: ");
		stationDetails.setBounds(35, 19, 382, 232);

		InputMap im = stationDetails.getInputMap();

		KeyStroke tab = KeyStroke.getKeyStroke("TAB");
		stationDetails.getActionMap().put(im.get(tab), new TabAction());

		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		stationDetails.getActionMap().put(im.get(enter), new EnterAction());

		contentPane.add(stationDetails);
	}

	class TabAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextArea textArea = (JTextArea) e.getSource();
			String text = textArea.getText();
			String stationName = text.replace("Enter Undergound Name: ", "");

			Object[] a = zone.MapOfLists().keySet().toArray();

			for (int i = 0; i < zone.MapOfLists().size(); i++) {
				if (a[i].toString().toLowerCase().contains(stationName.toLowerCase())) {
					textArea.setText(text.substring(0, 23) + a[i]);
				}
			}
		}
	}

	class EnterAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextArea textArea = (JTextArea) e.getSource();
			String text = textArea.getText();
			String stationName = text.replace("Enter Undergound Name: ", "");

			for (Map.Entry<String, List<Integer>> entry : zone.MapOfLists().entrySet()) {
				if (entry.getKey().toLowerCase().contains(stationName.toLowerCase())) {
					textArea.append(System.lineSeparator() + entry.getKey() + " has zone(s): ");
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (i == entry.getValue().size() - 1) {
							textArea.append(Integer.toString(entry.getValue().get(i)));
						} else {
							textArea.append(Integer.toString(entry.getValue().get(i)) + ",");
						}
					}
				}
			}
		}
	}
}