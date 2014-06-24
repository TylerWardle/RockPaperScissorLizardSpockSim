package ca.bcit.comp2613.a00913377.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class TestSwing extends JFrame {
	private JTextField playerName;
	private JTextField roundsPlayed;
	private JTextField roundsWon;
	private JTextField roundsLost;
	private JTextField roundsTied;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSwing frame = new TestSwing();
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
	public TestSwing() {
		setTitle("Player Browser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(null);
		
		playerName = new JTextField();
		playerName.setBounds(41, 399, 161, 20);
		getContentPane().add(playerName);
		playerName.setColumns(10);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setBounds(44, 376, 100, 16);
		getContentPane().add(lblPlayerName);
		
		roundsPlayed = new JTextField();
		roundsPlayed.setBounds(243, 398, 56, 22);
		getContentPane().add(roundsPlayed);
		roundsPlayed.setColumns(10);
		
		JLabel lblPlayed = new JLabel("Played");
		lblPlayed.setBounds(243, 376, 56, 16);
		getContentPane().add(lblPlayed);
		
		roundsWon = new JTextField();
		roundsWon.setColumns(10);
		roundsWon.setBounds(340, 397, 56, 22);
		getContentPane().add(roundsWon);
		
		roundsLost = new JTextField();
		roundsLost.setColumns(10);
		roundsLost.setBounds(437, 397, 56, 22);
		getContentPane().add(roundsLost);
		
		roundsTied = new JTextField();
		roundsTied.setColumns(10);
		roundsTied.setBounds(534, 397, 56, 22);
		getContentPane().add(roundsTied);
		
		JLabel lblWon = new JLabel("Won");
		lblWon.setBounds(340, 376, 56, 16);
		getContentPane().add(lblWon);
		
		JLabel lblLost = new JLabel("Lost");
		lblLost.setBounds(437, 376, 56, 16);
		getContentPane().add(lblLost);
		
		JLabel lblTied = new JLabel("Tied");
		lblTied.setBounds(534, 376, 56, 16);
		getContentPane().add(lblTied);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(45, 318, 99, 25);
		getContentPane().add(btnCreate);
		
		JButton btnList = new JButton("List");
		btnList.setBounds(191, 318, 99, 25);
		getContentPane().add(btnList);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(337, 318, 99, 25);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(483, 318, 99, 25);
		getContentPane().add(btnDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 356, 610, 7);
		getContentPane().add(separator);
		
		List list = new List();
		list.setMultipleSelections(false);
		list.setBounds(10, 10, 612, 290);
		getContentPane().add(list);
	}
}
