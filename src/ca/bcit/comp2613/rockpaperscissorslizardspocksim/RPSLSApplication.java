package ca.bcit.comp2613.rockpaperscissorslizardspocksim;

import java.awt.EventQueue;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.BracketTableModel;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Gestures;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.Player;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.PlayerEntity;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.model.SimPlayer;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository.PlayerRepository;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.repository.SimPlayerRepository;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.util.BracketFullException;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.util.DuplicatePlayerException;
import ca.bcit.comp2613.rockpaperscissorslizardspocksim.util.PlayerEntityUtil;

/**
 * Generates a Swing frame view for the rockpaperscissorslizardspock application
 * Executes all control for the gameplay. Contains the main class for execution. 
 * @author Tyler Wardle
 * @version July 30, 2014
 */
public class RPSLSApplication extends JFrame {

	private JPanel contentPane;	
	private JTextField id;
	private JTextField playerName;
	private JTextField roundsPlayed;
	private JTextField roundsWon;
	private JTextField roundsLost;
	private JTextField roundsTied;		
	private JTextField gestureBias;
	private JTextField txtWinner;
	private JLabel lblPlayerRoster;	
	private List<JTable> tableRounds;		
	private List<BracketTableModel> tables;
	private List<List<PlayerEntity>> bracket;
	private static final int BRACKET_SIZE = 8; 
	public String[] columnNames = new String[] {"ID","Name", "NPC?"};	
	private static PlayerRepository playerRepository;
	private static SimPlayerRepository simPlayerRepository; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RPSLSApplication frame = new RPSLSApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Creates a copy of elements from an iterator<T> and returns the copy
	 * @param iter as an iterator
	 * @return copy as a List<T>
	 */
	public static <T> List<T> copyIterator(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext())
			copy.add(iter.next());
		return copy;
	}
	
	/**
	 * Initialize the the application and start the in memory H2database.
	 * Display introduction/Help 
	 */
	public RPSLSApplication() {		
		tables = new ArrayList<BracketTableModel>();
		tableRounds = new ArrayList<JTable>();		
		initializeEmptyBracket();			
		initializeView();						
		
		ConfigurableApplicationContext context = null;
		context = SpringApplication.run(H2Config.class);
			try {
				org.h2.tools.Server.createWebServer(null).start();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		EntityManagerFactory emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		
		playerRepository = context.getBean(PlayerRepository.class);
		simPlayerRepository = context.getBean(SimPlayerRepository.class);			
		bracket.set(0,getPlayerEntitiesInBracket());				
		
		int i = 0;
		for(JTable table : tableRounds){
			initTable(table,bracket.get(i));
			i++;
		}
		displayHelp();
		
	}
	
	/**
	 * Initializes the tables which hold the players as the tournament is played.
	 * sets action listeners for table selection which will populate the display 
	 * fields with the selected player info.
	 * 
	 * @param selectedTable as a JTable
	 * @param fillPlayers as a List<PlayerEntity>
	 */
	private void initTable(final JTable selectedTable, final List<PlayerEntity> fillPlayers) {

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
	
	/**
	 * Retrieves SimPlayers and Players in the tournament from their respective repositories
	 * and adds them to a returned ArrayList of PlayerEntity 
	 * @return allPlayers as a List<PlayerEntity>
	 */
	public List<PlayerEntity> getPlayerEntitiesInBracket(){
		ArrayList<PlayerEntity> allPlayers = new ArrayList<PlayerEntity>();
		allPlayers.addAll(copyIterator(playerRepository.findAll().iterator()));
		allPlayers.addAll(copyIterator(simPlayerRepository.findAll().iterator()));
		return allPlayers;
	}
	
	/**
	 * Populates the fields with the PlayerEntity in the selected table row
	 * @param selectedTable as a JTable
	 */
	public void populateFields(JTable selectedTable){
		Iterator<PlayerEntity> iterator = bracket.get(0).iterator();		
		try {
			id.setText(selectedTable.getModel()
					.getValueAt(selectedTable.getSelectedRow(), 0).toString());
			playerName.setText(selectedTable.getModel()
					.getValueAt(selectedTable.getSelectedRow(), 1).toString());
			while (iterator.hasNext()){
				PlayerEntity currentPlayer = iterator.next();
				if (currentPlayer.getName().equals(playerName.getText())){
					roundsPlayed.setText(currentPlayer.getRoundsPlayed().toString());
					roundsWon.setText(currentPlayer.getRoundsWon().toString());
					roundsLost.setText(currentPlayer.getRoundsLost().toString());
					roundsTied.setText(currentPlayer.getRoundsTied().toString());
					if (currentPlayer instanceof SimPlayer){
						gestureBias.setText(((SimPlayer) currentPlayer).getGestureBias().getDescription());
					}else{
						gestureBias.setText("");
					}
				}
			}					
		} catch (Exception e) {}
	}
	
	/**
	 * Refreshes all tables with the current PlayerEntities 
	 */
	public void refreshTable(){	
		int i = 0;
		for (BracketTableModel bracketTable: tables){			
			bracket.set(0, getPlayerEntitiesInBracket());
			bracketTable.setDataVector(getSelectedData(bracket.get(i)), columnNames);				
			tableRounds.get(i).repaint();
			i++;
		}
	}	
	
	/**
	 * Builds a 2d Object list of all relevent data in the input List of PlayerEntity and returns it
	 * @param playerRoster as a List<PlayerEntity>
	 * @return data as an Object[][]
	 */
	public Object[][] getSelectedData(List<PlayerEntity> playerRoster){
		Object[][] data = null;
		
		data = new Object[playerRoster.size()][3];
		int i = 0;
		for (PlayerEntity player : playerRoster) {
			data[i][0] = player.getId();
			data[i][1] = player.getName();
			if (player instanceof SimPlayer){
				data[i][2] = true;
			}			
			i++;
		}
		return data;
	}
	
	/**
	 * Executed when the Play button is pressed. Executes a tournament of RPSLS and displays the winner.
	 * 
	 */
	public void play(){		
		int round = 1;
		fillBracket();				
		
		for(List<PlayerEntity> players: bracket){
			if (players.size() > 1){
				List<PlayerEntity> winners = executeRound(players);
				if (winners != null){
					bracket.set(round, winners);
					refreshTable();
					round++;
				}else{
					bracket.clear();
					break;					
				}
			}
		}
		if(bracket.size() != 0){
			PlayerEntity winner = bracket.get(3).get(0);
			txtWinner.setText(winner.getName() + " Wins!");
			JOptionPane.showMessageDialog(null,winner.getName()+ " wins the tournament!");
			//JOptionPane.showMessageDialog(null,winner.getName()+ " wins the tournament! \n"
			//		+ "Defeated : " + winner.getDefeatedPlayers().get(0).getName() + " "+
			//		winner.getDefeatedPlayers().get(1).getName() + " "+
			//		winner.getDefeatedPlayers().get(2).getName());
		}
	}
	
	/**
	 * Executes one round of play in an tournament of RPSLS.
	 * Players from the inputed list are paired of against each other,
	 * the winner is added to the return winner list, and scores are updated. 
	 * 
	 * @param players as a List<PlayerEntity>
	 * @return roundWinners as a List<PlayerEntity>
	 */
	public List<PlayerEntity> executeRound(List<PlayerEntity> players){
		Iterator<PlayerEntity> player = players.iterator();
		
		PlayerEntity matchWinner;
		List<PlayerEntity> roundWinners = new ArrayList<PlayerEntity>();
		
		//Match specifics
		PlayerEntity playerOne;
		Gestures playerOneThrow;
		PlayerEntity playerTwo;
		Gestures playerTwoThrow;
		
		//step through the players participating in the round and play one against the next 
		//till all have played a round. return a list of winners
		while (player.hasNext()){			
			playerOne = player.next(); 
			playerTwo = player.next();	
						
			do{
				playerOneThrow = getPlayersThrow(playerOne);
				playerTwoThrow = getPlayersThrow(playerTwo);
				if (playerOneThrow == null || playerTwoThrow == null){
					return null;
				}
				matchWinner = updateScore(playerOne, playerTwo, playerOneThrow, playerTwoThrow);				
			}while(matchWinner == null);	
			roundWinners.add(matchWinner);
			
		}			
		return roundWinners;
	}
	
	/**
	 * Determines the winner and updates the scores of two players based on their gesture throws.
	 * returns the winner or null if a tie. 
	 * 
	 * @param playerOne as a PlayerEntity
	 * @param playerTwo as a PlayerEntity
	 * @param playerOneThrow as a Gesture
	 * @param playerTwoThrow as a Gesture
	 * @return winner as a PlayerEntity
	 */
	public PlayerEntity updateScore(PlayerEntity playerOne, PlayerEntity playerTwo, Gestures playerOneThrow, Gestures playerTwoThrow){
		PlayerEntity winner;
		
		playerOne.setRoundsPlayed(playerOne.getRoundsPlayed()+1);
		playerTwo.setRoundsPlayed(playerTwo.getRoundsPlayed()+1);
		
		if (playerOneThrow == playerTwoThrow){
			playerOne.setRoundsTied(playerOne.getRoundsTied()+1);
			playerTwo.setRoundsTied(playerTwo.getRoundsTied()+1);
			winner = null;
		}else if(playerOneThrow.getDefeatingGestures().contains(playerTwoThrow)){			
			playerOne.setRoundsLost(playerOne.getRoundsLost()+1);
			playerTwo.setRoundsWon(playerTwo.getRoundsWon()+1);
			//if (playerOne instanceof Player){
			//	playerTwo.getDefeatedPlayers().add((Player) playerOne);
			//}
			//playerTwo.getDefeatedSimPlayers().add((SimPlayer) playerOne);			
			winner = playerTwo;
		}else{			
			playerOne.setRoundsWon(playerOne.getRoundsWon()+1);
			playerTwo.setRoundsLost(playerTwo.getRoundsLost()+1);
			//if (playerTwo instanceof Player){
			//	playerOne.getDefeatedPlayers().add((Player)playerTwo);
			//}
			//simPlayerRepository.findOne(playerOne.getId()).getDefeatedSimPlayers().add((SimPlayer)playerTwo);			
			winner = playerOne;
		}		
		savePlayer(playerOne);
		savePlayer(playerTwo);
		return winner;
		
	}
	
	public void savePlayer(PlayerEntity player){
		if (player instanceof SimPlayer){
			simPlayerRepository.save((SimPlayer)player);
		}else{
			playerRepository.save((Player)player);
		}
	}
	
	/**
	 * Fills the remaining spaces in the starting tournament bracket with Simulated players 
	 * to achieve the full eight players for a tournament.
	 */
	public void fillBracket(){
		while (bracket.get(0).size() < BRACKET_SIZE){
			SimPlayer simPlayer = new SimPlayer();
			simPlayer = PlayerEntityUtil.generateSimPlayer(bracket.get(0));
			bracket.get(0).add(simPlayer);
			simPlayerRepository.save(simPlayer);
		}
		refreshTable();
	}
	
	/**
	 * Gets a players gesture throw. Live players are prompted for their throw
	 * while simulated players throws are generated at random and 50% of the time a simulated player throws 
	 * their gestureBias as a throw. 
	 * @param player as a PlayerEntity
	 * @return throw as a Gesture
	 */
	public Gestures getPlayersThrow(PlayerEntity player){
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
				return null;
			}
		}
	}
	
	/**
	 * Creates a new player and adds them to the starting bracket.
	 * throws errors if the bracket already contains eight players or if a player by that name already exists in the bracket.
	 * @throws DuplicatePlayerException
	 * @throws BracketFullException
	 */
	public void doCreate() throws DuplicatePlayerException, BracketFullException{			
		if (playerName != null){
			if(bracket.get(0).size()>= BRACKET_SIZE){
				throw new BracketFullException();
			}else if (playerRepository.findByName(playerName.getText()) != null){
				throw new DuplicatePlayerException(playerRepository.findByName(playerName.getText()));
			}							
			Player player = new Player();		
			player.setName(playerName.getText());
			player.setId(PlayerEntityUtil.getMaxID(bracket.get(0))+1);
			playerRepository.save(player);
			refreshTable();			
		}
	}		
	
	/**
	 * Update the selected players name. Throws an exception if the new name already exists  
	 * @throws DuplicatePlayerException
	 */
	public void doUpdate() throws DuplicatePlayerException{
		if (!playerName.getText().equals("")){
			if (playerName != null){				
				if (playerRepository.findByName(playerName.getText()) != null || simPlayerRepository.findByName(playerName.getText())!= null){
					throw new DuplicatePlayerException(playerRepository.findByName(playerName.getText()));
				}				
				if (playerRepository.findOne(Long.valueOf(id.getText())) != null){
					Player player = playerRepository.findOne(Long.valueOf(id.getText()));
					player.setName(playerName.getText());
					playerRepository.save(player);				
				}else{
					SimPlayer simPlayer = simPlayerRepository.findOne(Long.valueOf(id.getText()));
					simPlayer.setName(playerName.getText());
					simPlayerRepository.save(simPlayer);	
				}
			}			
			refreshTable();
		}
	}
	
	/**
	 * Delete the player currently selected in the tables 
	 */
	public void doDelete(){
		if (!playerName.getText().equals("")){
			if (playerRepository.findByName(playerName.getText()) != null){
				playerRepository.delete(Long.valueOf(id.getText()));	
			}else{
				simPlayerRepository.delete(Long.valueOf(id.getText()));
			}
			refreshTable();
		}
	}
	
	/**
	 * Initialize the empty bracket to the correct size 
	 */
	public void initializeEmptyBracket(){
		bracket = new ArrayList<List<PlayerEntity>>();
		int roundSize = BRACKET_SIZE;				
		while( roundSize >= 1){
			bracket.add(new ArrayList<PlayerEntity>(roundSize));			
			roundSize /= 2; 
		}
	}
	
	/**
	 * Display a intro/help message
	 */
	public void displayHelp(){
		JOptionPane.showMessageDialog(null,"Welcome to Rock Paper Scissors Lizard Spock!\n\n"
				+ " -Create up to eight live players by typing a new name in the \"Player Name\" box and pressing \"New Player\".\n\n"
				
				+ " -You can edit and delete players in the tournament roster with the \"Delete Player\" and \"Update Player\" buttons.\n\n"
				
				+ " -When you are ready to start press the \"PLAY\" button and the tournament will begin, any extra spots will be filled \n"
				+ "  with simulated players.\n\n"
				
				+ " To see this information again press the HELP button. Enjoy!","Help", 1);
	}
	
	/**
	 * Initialize the swing view and build the frame
	 */
	public void initializeView(){
		
		setTitle("Rock Paper Scissors Lizard Spock");		
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		tables.add(0, new BracketTableModel());		
		contentPane.setLayout(null);
		tableRounds.add(0, new JTable(tables.get(0)));
		tableRounds.get(0).setBounds(10, 30, 148, 248);
		tableRounds.get(0).setFillsViewportHeight(true);
		getContentPane().add(tableRounds.get(0));
		JScrollPane scrollPane = new JScrollPane(tableRounds.get(0));
		scrollPane.setBounds(10, 30, 150, 250);
		getContentPane().add(scrollPane);			
		
		tables.add(1, new BracketTableModel());	
		tableRounds.add(1, new JTable(tables.get(1)));
		tableRounds.get(1).setBounds(170, 30, 150, 250);
		tableRounds.get(1).setFillsViewportHeight(true);	
		getContentPane().add(tableRounds.get(1));
		JScrollPane scrollPaneOne = new JScrollPane(tableRounds.get(1));
		scrollPaneOne.setBounds(170, 30, 150, 250);
		getContentPane().add(scrollPaneOne);
		
		tables.add(2, new BracketTableModel());	
		tableRounds.add(2, new JTable(tables.get(2)));
		tableRounds.get(2).setBounds(330, 30, 150, 250);
		tableRounds.get(2).setFillsViewportHeight(true);		
		getContentPane().add(tableRounds.get(2));
		JScrollPane scrollPaneTwo = new JScrollPane(tableRounds.get(2));
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
		
		JButton btnCreate = new JButton("New Player");
		btnCreate.setBounds(492, 145, 209, 25);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					doCreate();
				}catch(RuntimeException error){					
					JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
				}							
			}
		});
		getContentPane().add(btnCreate);
				
		JButton btnUpdate = new JButton("Update Selected Player");
		btnUpdate.setBounds(492, 200, 209, 25);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					doUpdate();					
				}catch(RuntimeException error){
					JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
				}					
			}
		});
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete Selected Player");
		btnDelete.setBounds(492, 255, 209, 25);
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
		btnPlay.setBounds(492, 92, 209, 23);
		contentPane.add(btnPlay);
		btnPlay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		
		txtWinner = new JTextField();
		txtWinner.setBounds(492, 30, 209, 20);
		contentPane.add(txtWinner);
		txtWinner.setColumns(10);
		
		JLabel lblRoundOneWinners = new JLabel("Round Two");
		lblRoundOneWinners.setBounds(170, 11, 148, 14);
		contentPane.add(lblRoundOneWinners);
		
		JLabel lblRoundTwoWinners = new JLabel("Round Three");
		lblRoundTwoWinners.setBounds(330, 11, 148, 14);
		contentPane.add(lblRoundTwoWinners);
		
		JLabel lblWinner = new JLabel("Tournament Winner");
		lblWinner.setBounds(492, 11, 131, 14);
		contentPane.add(lblWinner);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(612, 318, 89, 23);
		contentPane.add(btnHelp);
		btnHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				displayHelp();
			}
		});
	}	
}
