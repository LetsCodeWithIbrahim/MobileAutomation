package fixtures;

import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class DriverData {
	private String firstName;
	private String PRONumber;

	public DriverData() {}
	
	public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getPRONumber() {
    	return PRONumber;
    }
    
    public void setPRONumber(String PRONumber) {
		this.PRONumber = PRONumber;
	}
	
	
	public static DriverData readFromJson() throws IOException {
        Gson gson = new Gson();
        try (InputStreamReader reader = new InputStreamReader(DriverData.class.getClassLoader().getResourceAsStream("driverData.json"))) {
            return gson.fromJson(reader, DriverData.class);
        }
    }
}


