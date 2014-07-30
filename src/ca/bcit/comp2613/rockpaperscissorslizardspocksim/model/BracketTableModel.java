package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

import javax.swing.table.DefaultTableModel;

/**
 * A model class for the tables which will hold our players 
 * @author Tyler Wardle
 * @version July 30 2014 
 */
public class BracketTableModel  extends DefaultTableModel {
	
		 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
}




