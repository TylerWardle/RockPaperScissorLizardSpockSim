package ca.bcit.comp2613.rockpaperscissorslizardspocksim;
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

import ca.bcit.comp2613.a00913377.util.Helper;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gestures;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.table.DefaultTableModel;

import java.util.Iterator;

import javax.swing.JTextPane;
import javax.swing.table.TableModel;
import javax.swing.Box;



public class TournamentBuilder extends JFrame {

	private JPanel contentPane;
	private JTable table;	
	private JTable roundOne;
	private JTable roundTwo;
	private JTextField id;
	private JTextField playerName;
	private JTextField roundsPlayed;
	private JTextField roundsWon;
	private JTextField roundsLost;
	private JTextField roundsTied;		
	private BracketModel bracketModel;
	private RoundOneModel roundOneModel;
	private RoundTwoModel roundTwoModel;
	private JLabel lblPlayerRoster;	
	private ArrayList<Player> players;	
	public String[] columnNames = new String[] {"ID","Name", "NPC?"};
	private JTextField gestureBias;
	private JTextField txtGesture;
	//note that brackets size may only be doubles beginning with 2 ie. 2,4,8,16,32...
	private int BRACKET_SIZE = 8; 
	private ArrayList<ArrayList<Player>> bracket;


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
		players = helper.populatePlayers(BRACKET_SIZE);
		bracket = new ArrayList<ArrayList<Player>>();
		initializeEmptyBracket();		
		initializeView();
		initTable(table,players);
		initTable(roundOne,bracket.get(1));
		initTable(roundTwo,bracket.get(2));		
		
		
	}
	
	private void initTable(final JTable selectedTable, final ArrayList<Player> fillPlayers) {

		selectedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							populateFields(selectedTable);
						}
					}
				});		
		refreshTable();
	}
	
	public void populateFields(JTable selectedTable){
		Iterator<Player> iterator = players.iterator();
		try {
			id.setText(selectedTable.getModel()
					.getValueAt(selectedTable.getSelectedRow(), 0).toString());
			playerName.setText(selectedTable.getModel()
					.getValueAt(selectedTable.getSelectedRow(), 1).toString());
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
		bracketModel.setDataVector(getCurrentData(players), columnNames);	
		
		if (bracket.get(1).size() > 0){
			roundOneModel.setDataVector(getCurrentData(bracket.get(1)), columnNames);
		}
		if (bracket.get(2).size() > 0){
			roundTwoModel.setDataVector(getCurrentData(bracket.get(2)), columnNames);
		}
		table.repaint();
		roundOne.repaint();
		roundTwo.repaint();
	}	
	
	public Object[][] getCurrentData(ArrayList<Player> playerRoster){
		Object[][] data = null;
		
		data = new Object[playerRoster.size()][3];
		int i = 0;
		for (Player player : playerRoster) {
			data[i][0] = player.getId();
			data[i][1] = player.getName();
			if (player instanceof SimPlayer){
				data[i][2] = true;
			}			
			i++;
		}
		return data;
	}
	
	public void play(){		
		int round = 1;
		bracket.set(0, players);
		
		for(ArrayList<Player> players: bracket){
			if (players.size() > 1){
				bracket.set(round, executeRound(players));
				round++;
			}
		}
		JOptionPane.showMessageDialog(null,bracket.get(bracket.size()-1).get(0).getName()+ " wins the tournament!");		
	}
	
	public ArrayList<Player> executeRound(ArrayList<Player> players){
		Iterator<Player> player = players.iterator();
		
		Player matchWinner;
		ArrayList<Player> roundWinners = new ArrayList<Player>();
		
		//Match specifics
		Player playerOne;
		Gestures playerOneThrow;
		Player playerTwo;
		Gestures playerTwoThrow;
		
		//step through the players participating in the round and play one against the next 
		//till all have played a round. return a list of winners
		while (player.hasNext()){			
			playerOne = player.next(); 
			playerTwo = player.next();	
						
			do{
				playerOneThrow = getPlayersThrow(playerOne);
				playerTwoThrow = getPlayersThrow(playerTwo);
				matchWinner = updateScore(playerOne, playerTwo, playerOneThrow, playerTwoThrow);				
			}while(matchWinner == null);	
			roundWinners.add(matchWinner);
			refreshTable();
		}	
		roundWinners.trimToSize();
		return roundWinners;
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
			}else{
				return Gestures.ROCK;
			}
		}
	}
	
	public void initializeView(){
		
		setTitle("Tournament Builder");		
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		bracketModel = new BracketModel();
		contentPane.setLayout(null);
		table = new JTable(bracketModel);
		table.setBounds(10, 30, 148, 248);
		table.setFillsViewportHeight(true);
		getContentPane().add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 30, 150, 250);
		getContentPane().add(scrollPane);			
		
		roundOneModel = new RoundOneModel();
		roundOne = new JTable(roundOneModel);
		roundOne.setBounds(170, 30, 148, 248);
		roundOne.setFillsViewportHeight(true);
		getContentPane().add(roundOne);
		JScrollPane scrollPaneOne = new JScrollPane(roundOne);
		scrollPaneOne.setBounds(170, 30, 150, 250);
		getContentPane().add(scrollPaneOne);
		
		roundTwoModel = new RoundTwoModel();
		roundTwo = new JTable(roundTwoModel);
		roundTwo.setBounds(330, 30, 148, 248);
		roundTwo.setFillsViewportHeight(true);
		getContentPane().add(roundTwo);
		JScrollPane scrollPaneTwo = new JScrollPane(roundTwo);
		scrollPaneTwo.setBounds(330, 30, 150, 250);
		getContentPane().add(scrollPaneTwo);
		
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
		btnCreate.setBounds(618, 30, 99, 25);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCreate();
			}
		});
		getContentPane().add(btnCreate);
		
		JButton btnList = new JButton("Refresh");
		btnList.setBounds(618, 93, 99, 25);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		getContentPane().add(btnList);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(618, 156, 99, 25);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate();
			}
		});
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(618, 219, 99, 25);
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
		btnPlay.setBounds(618, 282, 99, 23);
		contentPane.add(btnPlay);
		btnPlay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		
		txtGesture = new JTextField();
		txtGesture.setBounds(490, 30, 86, 20);
		contentPane.add(txtGesture);
		txtGesture.setColumns(10);
		
		JLabel lblRoundOneWinners = new JLabel("Round One Winners");
		lblRoundOneWinners.setBounds(170, 11, 148, 14);
		contentPane.add(lblRoundOneWinners);
		
		JLabel lblRoundTwoWinners = new JLabel("Round Two Winners");
		lblRoundTwoWinners.setBounds(330, 11, 148, 14);
		contentPane.add(lblRoundTwoWinners);
		
		JLabel lblWinner = new JLabel("Tournament Winner");
		lblWinner.setBounds(490, 11, 131, 14);
		contentPane.add(lblWinner);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(600, 30, 138, 309);
		contentPane.add(verticalBox);		
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
		int roundSize = BRACKET_SIZE;				
		while( roundSize >= 1){
			bracket.add(new ArrayList<Player>(roundSize));			
			roundSize /= 2; 
		}			
	}
}
