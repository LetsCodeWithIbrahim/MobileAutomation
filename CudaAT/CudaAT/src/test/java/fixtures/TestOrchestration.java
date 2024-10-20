package fixtures;

import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestOrchestration {
    private static String forwardRefNumber;

    // Run Cypress tests with dynamic script name and return whether they succeeded
    public static boolean runCypressTests(String cypressCommand) throws IOException, InterruptedException {
        System.out.println("Running Cypress tests using script: " + cypressCommand);
        ProcessBuilder cypressBuilder = new ProcessBuilder("cmd", "/c", cypressCommand);
        cypressBuilder.directory(new File("C:\\TestAutomation")); // Set working directory
        cypressBuilder.inheritIO(); // Show Cypress output in the console

        Process cypressProcess = cypressBuilder.start();
        cypressProcess.waitFor(); // Wait for Cypress to complete

        return cypressProcess.exitValue() == 0;
    }

    // Read the results from the JSON file and extract the forward ref number
    public static String readResults() throws IOException {
        System.out.println("Cypress tests passed. Reading results...");
        String content = new String(Files.readAllBytes(Paths.get("C:\\TestAutomation\\cypress\\results\\results.json")));
        JSONObject jsonObject = new JSONObject(content);

        forwardRefNumber = jsonObject.optString("forwardRefNumber", "Default Forward Ref Number");

        System.out.println("Fetched Forward Ref Number: " + forwardRefNumber);
        
        return forwardRefNumber;
    }

    // Get the forward ref number
    public static String getForwardRefNumber() {
        return forwardRefNumber;
    }
}
