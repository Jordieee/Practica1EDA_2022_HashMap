package p1;

/**
 * Position class used in Main code to use an Array of Positions
 * 
 * @author Jordi Linares
 *
 */
public class Position {
	// Attributes
	private int row, column;

	// Constructor
	/**
	 * Position Constructor given row and column
	 * 
	 * @param row
	 * @param column
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	// Getters & Setters
	/**
	 * Row Getter
	 * 
	 * @return returns row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Row Setter
	 * 
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Column Getter
	 * 
	 * @return returns column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Column Setter
	 * 
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Position toString, prints Position in the appropriate format
	 */
	@Override
	public String toString() {
		return "(" + row + ":" + column + ")";
	}

}
