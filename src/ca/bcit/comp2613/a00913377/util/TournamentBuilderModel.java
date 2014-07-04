package ca.bcit.comp2613.a00913377.util;

import javax.swing.table.DefaultTableModel;

public class TournamentBuilderModel  extends DefaultTableModel {
	
		 @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
}




