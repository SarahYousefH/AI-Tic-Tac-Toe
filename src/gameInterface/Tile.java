package gameInterface;

import javafx.scene.control.Button;

public class Tile extends Button {

	private int cellNum;

	/**
	 * constructor that assign each tile a cell number from 0-9
	 * 
	 * @param cellNum
	 */
	public Tile(int cellNum) {
		super();
		this.cellNum = cellNum;

		if (Main.withMessages)
			this.setText(cellNum + ""); // to show number of cells

	}

	public void reset() {

		if (Main.withMessages)
			this.setText(cellNum + ""); 
		else
			this.setText(null);; 

			
		this.setDisable(false);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Tile otherTile = (Tile) obj;
		return cellNum == otherTile.cellNum;
	}

	// setters and getters

	public int getCellNum() {
		return cellNum;
	}

}
