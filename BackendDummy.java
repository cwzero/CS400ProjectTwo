import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BackendDummy implements BackendInterface {
    public BackendDummy() {

    }

    private static final String FORMAT_STRING = "EEE MMM dd HH:mm:ss Z yyyy";

    @Override
    public List<HighScoreInterface> getAllScores() {
        return Arrays.asList(
                new HighScoreInterface() {
                    @Override
                    public int compareTo(HighScoreInterface o) {
                        return 0;
                    }

                    @Override
                    public String getPlayer() {
                        return "siglemic";
                    }

                    @Override
                    public double getTime() {
                        return 6233;
                    }

                    @Override
                    public boolean isVerified() {
                        return true;
                    }

                    @Override
                    public String getPlatform() {
                        return "Nintendo 64";
                    }

                    @Override
                    public Date getDateRunCompleted() {
                        try {
                            return new SimpleDateFormat(FORMAT_STRING).parse("Tue Apr 22 19:00:00 CDT 2014");
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                },                
                new HighScoreInterface() {
                    @Override
                    public int compareTo(HighScoreInterface o) {
                        return 0;
                    }

                    @Override
                    public String getPlayer() {
                        return "cheese";
                    }

                    @Override
                    public double getTime() {
                        return 6305;
                    }

                    @Override
                    public boolean isVerified() {
                        return true;
                    }

                    @Override
                    public String getPlatform() {
                        return "Nintendo 64";
                    }

                    @Override
                    public Date getDateRunCompleted() {
                        try {
                            return new SimpleDateFormat(FORMAT_STRING).parse("Sun Nov 23 18:00:00 CST 2014");
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                },
                new HighScoreInterface() {
                    @Override
                    public int compareTo(HighScoreInterface o) {
                        return 0;
                    }

                    @Override
                    public String getPlayer() {
                        return "toastrider";
                    }

                    @Override
                    public double getTime() {
                        return 6314;
                    }

                    @Override
                    public boolean isVerified() {
                        return true;
                    }

                    @Override
                    public String getPlatform() {
                        return "Nintendo 64";
                    }

                    @Override
                    public Date getDateRunCompleted() {
                        try {
                            return new SimpleDateFormat(FORMAT_STRING).parse("Mon May 30 19:00:00 CDT 2016");
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
        );
    }

    @Override
    public List<HighScoreInterface> getHighScores(double time) {
        return getAllScores().stream()
                .filter(it -> it.getTime() <= time)
                .collect(Collectors.toList());
    }

    @Override
    public HighScoreInterface getFastestScore() {
        return getAllScores().stream()
                .sorted((a, b) -> ((Double)a.getTime()).compareTo(b.getTime()))
                .findFirst().orElse(null);
    }

    @Override
    public HighScoreInterface getSlowestScore() {
        return getAllScores().stream()
                .sorted((a, b) -> ((Double)b.getTime()).compareTo(a.getTime()))
                .findFirst().orElse(null);
    }

    @Override
    public HighScoreInterface getPlayerScore(String player) {
        return getAllScores().stream()
                .filter(it -> it.getPlayer().equals(player))
                .findFirst().orElse(null);
    }
}
