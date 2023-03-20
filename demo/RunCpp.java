import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunCpp {
    public static void main(String[] args) {
        String filePath = "./Hello.cpp";
        String exePath = "./a.exe";

        try {
            // Compile the CPP file
            Process compileProcess = Runtime.getRuntime().exec("g++ " + filePath);
            printOutput(compileProcess.getInputStream());
            printOutput(compileProcess.getErrorStream());

            // If compilation was successful, then run the executable file
            Process runProcess = Runtime.getRuntime().exec(exePath);
            printOutput(runProcess.getInputStream());
            printOutput(runProcess.getErrorStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printOutput(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}