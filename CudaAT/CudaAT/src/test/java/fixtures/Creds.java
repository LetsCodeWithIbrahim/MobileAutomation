package fixtures;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.io.IOException;

public class Creds {
    private String email;
    private String password;

    // Default constructor (needed for JSON deserialization)
    public Creds() {}

    // Getter and Setter methods
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Static method to read credentials from JSON file
    public static Creds readFromJson() throws IOException {
        Gson gson = new Gson();
        try (InputStreamReader reader = new InputStreamReader(Creds.class.getClassLoader().getResourceAsStream("creds.json"))) {
            return gson.fromJson(reader, Creds.class);
        }
    }
}
