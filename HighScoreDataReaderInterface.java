import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface HighScoreDataReaderInterface {
	List<HighScoreInterface> readDataSet(Reader inputFileReader) throws IOException;
}
