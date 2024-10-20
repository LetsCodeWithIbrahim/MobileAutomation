package fixtures;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.IOException;

public class OdometerData {
	private String initialReading;
	
	// Default constructor (needed for JSON deserialization)
    public OdometerData() {}

 // Getter and Setter methods
    public String getInitialtOdometerReading() {
        return initialReading;
    }
    
    public void setInitialtOdometerReading(String initialReading) {
        this.initialReading = initialReading;
    }
    
	// Static method to read credentials from JSON file
    public static OdometerData readFromJson() throws IOException {
        Gson gson = new Gson();
        try (InputStreamReader reader = new InputStreamReader(OdometerData.class.getClassLoader().getResourceAsStream("odometerData.json"))) {
            return gson.fromJson(reader, OdometerData.class);
        }
    }
}
