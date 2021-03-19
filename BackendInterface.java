import java.util.List;

public interface BackendInterface {
	List<HighScoreInterface> getAllScores();

	// Min, max or exact?
	List<HighScoreInterface> getHighScores(double time);

	// Shortest time
	HighScoreInterface getFastestScore();

	// Longest time
	HighScoreInterface getSlowestScore();

	// Get score using the player's name
	HighScoreInterface getPlayerScore(String player);
}
