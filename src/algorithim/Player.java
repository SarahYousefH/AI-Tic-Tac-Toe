package algorithim;

/**
 * A class to represent a player object. Two players are compared based on the
 * symbols.
 */
public class Player implements Comparable<Player> {


	private String name;
	private int points; // number of wins
	private char symbol;

	private boolean isComputer;

	public Player(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	@Override
	public int compareTo(Player o) {
		return this.symbol - o.symbol;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object instanceof Player) {
			Player otherPlayer = (Player) object;

			return this.symbol == otherPlayer.symbol;
		}

		return false;
	}

}
