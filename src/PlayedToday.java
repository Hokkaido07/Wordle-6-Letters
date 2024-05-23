import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayedToday {
    public boolean isPlayedToday(String filePath) {
        FileParse fileParser = new FileParse();
        String lastPlayedDate = fileParser.getLineFromFile(filePath);

        if (lastPlayedDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String currentDate = dateFormat.format(new Date());

            return currentDate.equals(lastPlayedDate);
        }

        return false;
    }
}