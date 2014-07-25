package ca.bcit.comp2613.rockpaperscissorslizardspocksim;

import javax.swing.table.DefaultTableModel;

public class RoundOneModel  extends DefaultTableModel {
	
		 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
}




