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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gestures;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import javax.swing.JTextPane;



public class TournamentBuilder extends JFrame {

	private JPanel contentPane;
	private JTable table;	
	private JTextField id;
	private JTextField playerName;
	private JTextField roundsPlayed;
	private JTextField roundsWon;
	private JTextField roundsLost;
	private JTextField roundsTied;	
	private ArrayList<Player> roundOneWinners;
	private ArrayList<Player> roundTwoWinners;
	private TournamentBuilderModel tournamentBuilderModel;
	private JLabel lblPlayerRoster;
	//private ArrayList<ArrayList<ArrayList<JTextField>>> bracket;
	private ArrayList<Player> players;
	//public String[] columnNames = new String[] { "ID", "Name", "Rounds Played", "Rounds Won", "Rounds Lost", "Rounds Tied" };
	public String[] columnNames = new String[] {"ID","Name", "NPC?"};
	private JTextField gestureBias;
	private JTextField txtGesture;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TournamentBuilder frame = new TournamentBuilder();
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
	public TournamentBuilder() {
		Helper helper = new Helper();
		ArrayList<Player> roundOneWinners = new ArrayList<Player>(4);
		ArrayList<Player> roundTwoWinners = new ArrayList<Player>(2);
		players = helper.populatePlayers(8);
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
		Iterator<Player> iterator = players.iterator();
		try {
			id.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 0).toString());
			playerName.setText(table.getModel()
					.getValueAt(table.getSelectedRow(), 1).toString());
			while (iterator.hasNext()){
				Player currentPlayer = iterator.next();
				if (currentPlayer.getName().equals(playerName.getText())){
					roundsPlayed.setText(currentPlayer.getRoundsPlayed().toString());
					roundsWon.setText(currentPlayer.getRoundsWon().toString());
					roundsLost.setText(currentPlayer.getRoundsLost().toString());
					roundsTied.setText(currentPlayer.getRoundsTied().toString());
					if (currentPlayer instanceof SimPlayer){
						gestureBias.setText(((SimPlayer) currentPlayer).getGestureBias().getDescription());
					}else{
						gestureBias.setText("N/A");
					}
				}
			}
			
			//roundsPlayed.setText(table.getModel()
			//		.getValueAt(table.getSelectedRow(), 2).toString());
			//roundsWon.setText(table.getModel()
			//		.getValueAt(table.getSelectedRow(), 3).toString());
			//roundsLost.setText(table.getModel()
			//		.getValueAt(table.getSelectedRow(), 4).toString());
			//roundsTied.setText(table.getModel()
			//		.getValueAt(table.getSelectedRow(), 5).toString());			
		} catch (Exception e) {}
	}
	
	public void refreshTable(){		
		Object[][] data = null;

		//data = new Object[players.size()][6];
		data = new Object[players.size()][3];
		int i = 0;
		for (Player player : players) {
			data[i][0] = player.getId();
			data[i][1] = player.getName();
			if (player instanceof SimPlayer){
				data[i][2] = true;
			}
			//data[i][2] = player.getRoundsPlayed();
			//data[i][3] = player.getRoundsWon();
			//data[i][4] = player.getRoundsLost();
			//data[i][5] = player.getRoundsTied();
			i++;
		}
		tournamentBuilderModel.setDataVector(data, columnNames);		
		table.repaint();
	}	
	
	public void play(){
		for (Player player: players){
			
		}
		Gestures gesture = (Gestures) JOptionPane.showInputDialog(null,"Choose your weapon", "RPSLS Sim",
				JOptionPane.QUESTION_MESSAGE, null,Gestures.values(), Gestures.ROCK);
		if(gesture instanceof Gestures){
			txtGesture.setText(gesture.getDescription());
		}
		
	}
	
	public void initialize(){
		
		setTitle("Tournament Builder");
		
		//bracket = new ArrayList<ArrayList<ArrayList<JTextField>>>();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tournamentBuilderModel = new TournamentBuilderModel();		
		table = new JTable(tournamentBuilderModel);
		table.setFillsViewportHeight(true);
		//table.setBackground(Color.WHITE);
		table.setBounds(0, -106, 150, 250);
		getContentPane().add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 30, 150, 250);
		getContentPane().add(scrollPane);	
		
		id = new JTextField();
		id.setEditable(false);
		id.setColumns(10);
		id.setBounds(10, 318, 56, 22);
		getContentPane().add(id);
		
		JLabel IDLable = new JLabel("ID");
		IDLable.setBounds(10, 291, 56, 16);
		getContentPane().add(IDLable);
		
		playerName = new JTextField();
		playerName.setBounds(76, 319, 161, 22);
		getContentPane().add(playerName);
		playerName.setColumns(10);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setBounds(76, 292, 100, 16);
		getContentPane().add(lblPlayerName);
		
		roundsPlayed = new JTextField();
		roundsPlayed.setEditable(false);
		roundsPlayed.setBounds(247, 318, 56, 22);
		getContentPane().add(roundsPlayed);
		roundsPlayed.setColumns(10);
		
		JLabel lblPlayed = new JLabel("Played");
		lblPlayed.setBounds(247, 291, 56, 22);
		getContentPane().add(lblPlayed);
		
		roundsWon = new JTextField();
		roundsWon.setEditable(false);
		roundsWon.setColumns(10);
		roundsWon.setBounds(313, 318, 56, 22);
		getContentPane().add(roundsWon);
		
		roundsLost = new JTextField();
		roundsLost.setEditable(false);
		roundsLost.setColumns(10);
		roundsLost.setBounds(376, 318, 56, 22);
		getContentPane().add(roundsLost);
		
		roundsTied = new JTextField();
		roundsTied.setEditable(false);
		roundsTied.setColumns(10);
		roundsTied.setBounds(442, 318, 56, 22);
		getContentPane().add(roundsTied);
		
		JLabel lblWon = new JLabel("Won");
		lblWon.setBounds(313, 291, 56, 22);
		getContentPane().add(lblWon);
		
		JLabel lblLost = new JLabel("Lost");
		lblLost.setBounds(376, 291, 56, 22);
		getContentPane().add(lblLost);
		
		JLabel lblTied = new JLabel("Tied");
		lblTied.setBounds(442, 291, 56, 22);
		getContentPane().add(lblTied);
		
		lblPlayerRoster = new JLabel("Player Roster");
		lblPlayerRoster.setBounds(10, 11, 138, 14);
		contentPane.add(lblPlayerRoster);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCreate();
			}
		});
		btnCreate.setBounds(723, 38, 99, 25);
		getContentPane().add(btnCreate);
		
		JButton btnList = new JButton("Refresh");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		btnList.setBounds(723, 101, 99, 25);
		getContentPane().add(btnList);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate();
			}
		});
		btnUpdate.setBounds(723, 164, 99, 25);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		btnDelete.setBounds(723, 227, 99, 25);
		getContentPane().add(btnDelete);
		
		gestureBias = new JTextField();
		gestureBias.setEditable(false);
		gestureBias.setBounds(508, 319, 56, 20);
		contentPane.add(gestureBias);
		gestureBias.setColumns(10);
		
		JLabel lblGestureBias = new JLabel("Gesture Bias");
		lblGestureBias.setBounds(510, 292, 80, 22);
		contentPane.add(lblGestureBias);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(723, 290, 99, 23);
		contentPane.add(btnPlay);
		
		txtGesture = new JTextField();
		txtGesture.setText("Gesture");
		txtGesture.setBounds(170, 28, 86, 20);
		contentPane.add(txtGesture);
		txtGesture.setColumns(10);
		btnPlay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		
	}
	
	public void doCreate(){
		if (playerName != null && roundsPlayed != null && roundsWon != null &&
				roundsLost!= null && roundsTied != null){
			//players.add(new Player(players.size() + 1,
			//	playerName.getText(), 
			//	Integer.valueOf(roundsPlayed.getText()), 
			//	Integer.valueOf(roundsWon.getText()), 
			//	Integer.valueOf(roundsLost.getText()), 
			//	Integer.valueOf(roundsTied.getText())));
			players.add(new Player(players.size() + 1,playerName.getText(), 0, 0, 0, 0));

		}
	}		
	public void doUpdate(){
		//Player updatePlayer = new Player(Integer.valueOf(id.getText()),
		//		playerName.getText(), 
		//		Integer.valueOf(roundsPlayed.getText()), 
		//		Integer.valueOf(roundsWon.getText()), 
		//		Integer.valueOf(roundsLost.getText()), 
		//		Integer.valueOf(roundsTied.getText()));
		int rounds =0;
		int won =0;
		int	lost =0;
		int tied =0;
		Gestures gesture = Gestures.getRandomGesture();
		boolean playerFound = false;
		boolean nPCPlayer = false;
		
		for (Player currentPlayer: players){
			if (currentPlayer.getId() == Integer.valueOf(id.getText())){
				rounds = currentPlayer.getRoundsPlayed(); 
				won = currentPlayer.getRoundsWon(); 
				lost = currentPlayer.getRoundsLost();
				tied = currentPlayer.getRoundsTied();
				playerFound = true;
				if (currentPlayer instanceof SimPlayer){
					nPCPlayer = true;
					gesture = ((SimPlayer) currentPlayer).getGestureBias();		
				}
			}	
		}	
		if(playerFound){
			if (nPCPlayer){
				SimPlayer updatePlayer = new SimPlayer(Integer.valueOf(id.getText()),
					playerName.getText(),rounds, won, lost, tied, gesture);
				Helper.updatePlayer(players, updatePlayer);
			}else{
				Player updatePlayer = new Player(Integer.valueOf(id.getText()),
						playerName.getText(),rounds, won, lost, tied);
				Helper.updatePlayer(players, updatePlayer);
			}			
		}
	}
	
	public void doDelete(){
		Player deletePlayer = new Player(Integer.valueOf(id.getText()),
				playerName.getText(), 0, 0, 0, 0);
		Helper.deletePlayer(players, deletePlayer);
		
	}
	
	public void initializeEmptyBracket(){
		int playerSize = players.size();
		for (int i = 0 ; i< playerSize ; i+=2){
			
		}
	}
}
