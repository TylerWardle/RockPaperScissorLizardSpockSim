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
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import javax.swing.JTextPane;
import javax.swing.table.TableModel;



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
	private ArrayList<Player> players;	
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
		roundOneWinners = new ArrayList<Player>(4);
		roundTwoWinners = new ArrayList<Player>(2);
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
		} catch (Exception e) {}
	}
	
	public void refreshTable(){		
		Object[][] data = null;
		
		data = new Object[players.size()][3];
		int i = 0;
		for (Player player : players) {
			data[i][0] = player.getId();
			data[i][1] = player.getName();
			if (player instanceof SimPlayer){
				data[i][2] = true;
			}			
			i++;
		}
		tournamentBuilderModel.setDataVector(data, columnNames);		
		table.repaint();
	}	
	
	public void play(){
		Player tournamentWinner;
		
		roundOneWinners = executeRound(players);;
		roundTwoWinners = executeRound(roundOneWinners);
		tournamentWinner = executeRound(roundTwoWinners).get(0);
		JOptionPane.showMessageDialog(null,tournamentWinner.getName() + " wins the tournament!");		
		
	}
	
	public ArrayList<Player> executeRound(ArrayList<Player> players){
		Iterator<Player> round = players.iterator();
		
		Player winner;
		ArrayList<Player> winners = new ArrayList<Player>();
		
		Player playerOne;
		Gestures playerOneThrow;
		Player playerTwo;
		Gestures playerTwoThrow;
		
		while (round.hasNext()){			
			playerOne = round.next(); 
			playerTwo = round.next();	
						
			do{
				playerOneThrow = getPlayersThrow(playerOne);
				playerTwoThrow = getPlayersThrow(playerTwo);
				winner = updateScore(playerOne, playerTwo, playerOneThrow, playerTwoThrow);
				winners.add(winner);
			}while(playerOneThrow == playerTwoThrow);				
		}	
		winners.trimToSize();
		return winners;
	}
	
	public Player updateScore(Player playerOne, Player playerTwo, Gestures playerOneThrow, Gestures playerTwoThrow){
		
		playerOne.setRoundsPlayed(playerOne.getRoundsPlayed()+1);
		playerTwo.setRoundsPlayed(playerTwo.getRoundsPlayed()+1);
		
		if (playerOneThrow == playerTwoThrow){
			playerOne.setRoundsTied(playerOne.getRoundsTied()+1);
			playerTwo.setRoundsTied(playerTwo.getRoundsTied()+1);
			return null;
		}else if(playerOneThrow.getDefeatingGestures().contains(playerTwoThrow)){
			txtGesture.setText(playerTwo.getName() + " Wins!");
			playerOne.setRoundsLost(playerOne.getRoundsLost()+1);
			playerTwo.setRoundsWon(playerTwo.getRoundsWon()+1);
			return playerTwo;
		}else{
			txtGesture.setText(playerOne.getName() + " Wins!");
			playerOne.setRoundsWon(playerOne.getRoundsWon()+1);
			playerTwo.setRoundsLost(playerTwo.getRoundsLost()+1);
			return playerOne;
		}
		
	}
	
	public Gestures getPlayersThrow(Player player){
		Random random = new Random();
		if(player instanceof SimPlayer){
			if (random.nextBoolean()){
				return ((SimPlayer) player).getGestureBias();
			}else{
				return Gestures.getRandomGesture();
			}
		}else{
			Gestures gesture = (Gestures) JOptionPane.showInputDialog(null,
					player.getName() + " choose your weapon", "RPSLS Sim",
					JOptionPane.QUESTION_MESSAGE, null,Gestures.values(), Gestures.ROCK);
			if(gesture instanceof Gestures){
				return gesture;
			}
			return Gestures.ROCK;
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
		
		tournamentBuilderModel = new TournamentBuilderModel();
		contentPane.setLayout(null);
		table = new JTable(tournamentBuilderModel);
		table.setBounds(1, 1, 148, 248);
		table.setFillsViewportHeight(true);
		getContentPane().add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 30, 150, 250);
		getContentPane().add(scrollPane);			
		
		id = new JTextField();
		id.setBounds(10, 318, 56, 22);
		id.setEditable(false);
		id.setColumns(10);
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
		roundsPlayed.setBounds(247, 318, 56, 22);
		roundsPlayed.setEditable(false);
		getContentPane().add(roundsPlayed);
		roundsPlayed.setColumns(10);
		
		JLabel lblPlayed = new JLabel("Played");
		lblPlayed.setBounds(247, 291, 56, 22);
		getContentPane().add(lblPlayed);
		
		roundsWon = new JTextField();
		roundsWon.setBounds(313, 318, 56, 22);
		roundsWon.setEditable(false);
		roundsWon.setColumns(10);
		getContentPane().add(roundsWon);
		
		roundsLost = new JTextField();
		roundsLost.setBounds(376, 318, 56, 22);
		roundsLost.setEditable(false);
		roundsLost.setColumns(10);
		getContentPane().add(roundsLost);
		
		roundsTied = new JTextField();
		roundsTied.setBounds(442, 318, 56, 22);
		roundsTied.setEditable(false);
		roundsTied.setColumns(10);
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
		btnCreate.setBounds(723, 38, 99, 25);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCreate();
			}
		});
		getContentPane().add(btnCreate);
		
		JButton btnList = new JButton("Refresh");
		btnList.setBounds(723, 101, 99, 25);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		getContentPane().add(btnList);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(723, 164, 99, 25);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate();
			}
		});
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(723, 227, 99, 25);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		getContentPane().add(btnDelete);
		
		gestureBias = new JTextField();
		gestureBias.setBounds(508, 319, 56, 20);
		gestureBias.setEditable(false);
		contentPane.add(gestureBias);
		gestureBias.setColumns(10);
		
		JLabel lblGestureBias = new JLabel("Gesture Bias");
		lblGestureBias.setBounds(510, 292, 80, 22);
		contentPane.add(lblGestureBias);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(723, 290, 99, 23);
		contentPane.add(btnPlay);
		btnPlay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		
		txtGesture = new JTextField();
		txtGesture.setBounds(584, 319, 86, 20);
		txtGesture.setText("Gesture");
		contentPane.add(txtGesture);
		txtGesture.setColumns(10);
		
		JLabel lblRoundOneWinners = new JLabel("Round One Winners");
		lblRoundOneWinners.setBounds(170, 11, 148, 14);
		contentPane.add(lblRoundOneWinners);
		
		
		
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
