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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;

import java.util.ArrayList;

public class PlayerBrowser extends JFrame {

	private JTextField playerName;
	private JTextField roundsPlayed;
	private JTextField roundsWon;
	private JTextField roundsLost;
	private JTextField roundsTied;
	private JTable table;
	private ArrayList<Player> players;
	public String[] columnNames = new String[] { "Name", "Rounds Won", "Rounds Lost", "Rounds Tied" };
	public PlayerBrowserModel playerBrowserModel ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerBrowser window = new PlayerBrowser();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayerBrowser() {
		Helper helper = new Helper();
		players = helper.populatePlayers(100);
		initialize();
		initTable();
	}
	private void initTable() {

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							populateFields();
						}
					}
				});
		refreshTable();

	}
	public void populateFields(){
		try {
			playerName.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 0).toString());
			roundsWon.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 1).toString());
			roundsLost.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 2).toString());
			roundsTied.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 3).toString());			
		} catch (Exception e) {}
	}
	
	public void refreshTable(){
		playerBrowserModel = new PlayerBrowserModel();
		Object[][] data = null;

		data = new Object[players.size()][4];
		int i = 0;
		for (Player player : players) {
			data[i][0] = player.getName();
			data[i][1] = player.getRoundsWon();
			data[i][2] = player.getRoundsLost();
			data[i][3] = player.getRoundsTied();
			i++;
		}
		playerBrowserModel.setDataVector(data, columnNames);
		table.repaint();
	}
	
	public void initialize(){
		playerBrowserModel = new PlayerBrowserModel();
		//setModel(playerBrowserModel);
		setTitle("Player Browser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(null);
		
		
		table = new JTable(playerBrowserModel);
		table.setBackground(Color.WHITE);
		table.setBounds(12, 279, 608, -246);
		getContentPane().add(table);
		
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 608, 247);
		getContentPane().add(scrollPane);		
		
		
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
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCreate();
			}
		});
		btnCreate.setBounds(45, 318, 99, 25);
		getContentPane().add(btnCreate);
		
		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doList();
			}
		});
		btnList.setBounds(191, 318, 99, 25);
		getContentPane().add(btnList);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate();
			}
		});
		btnUpdate.setBounds(337, 318, 99, 25);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		btnDelete.setBounds(483, 318, 99, 25);
		getContentPane().add(btnDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 356, 610, 7);
		getContentPane().add(separator);	
		
	}
	
	public void doCreate(){
		
	}	
	public void doList(){
		
	}
	public void doUpdate(){
		
	}
	public void doDelete(){
		
	}
}
