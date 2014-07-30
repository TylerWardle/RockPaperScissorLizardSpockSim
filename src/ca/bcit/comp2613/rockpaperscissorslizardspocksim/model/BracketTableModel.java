package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

import javax.swing.table.DefaultTableModel;

public class BracketTableModel  extends DefaultTableModel {
	
		 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
}




