import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunJava {
    public static void main(String[] args) {
        String javaFileName = "Hello.java"; 
        String className = "Hello"; 
        String command = "javac " + javaFileName;
        try {
            Process process = Runtime.getRuntime().exec(command); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); 
            }
            process.waitFor(); 
            command = "java " + className;
            process = Runtime.getRuntime().exec(command); 
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor(); // 
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
