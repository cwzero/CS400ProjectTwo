import java.util.Date;

// Implemented by the data wrangler as HighScore class
public interface HighScoreInterface extends Comparable<HighScoreInterface> {
	String getPlayer();
	double getTime();
	boolean isVerified();
	String getPlatform();
	Date getDateRunCompleted();
}