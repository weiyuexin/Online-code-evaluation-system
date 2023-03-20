import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompilerRunner {

    public static void main(String[] args) {

        String fileName = "Hello.java";
        String className = "Hello";

      
        Path sourcePath = Paths.get(".", fileName);
        Path targetPath = Paths.get(".", className + ".class");

      
        if (compileJavaFile(sourcePath)) {
            
            runJavaClass(className);
        } else {
            System.err.println("compile Java file failed");
        }

    }

  
    private static boolean compileJavaFile(Path sourcePath) {
        try {
           
            List<String> commandList = new ArrayList<>();
            commandList.add("javac");
            commandList.add(sourcePath.toString());

            
            ProcessBuilder processBuilder = new ProcessBuilder(commandList);
            Process process = processBuilder.start();

           
            int exitCode = process.waitFor();

          
            return (exitCode == 0);

        } catch (IOException | InterruptedException e) {
            System.err.println("compile java file error:" + e.getMessage());
            return false;
        }
    }

  
    private static void runJavaClass(String className) {
        try {
           
            List<String> commandList = new ArrayList<>();
            commandList.add("java");
            commandList.add(className);

           
            ProcessBuilder processBuilder = new ProcessBuilder(commandList);
            Process process = processBuilder.start();

          
            process.getInputStream().transferTo(System.out);

           
            int exitCode = process.waitFor();

         
            if (exitCode != 0) {
                System.err.println("run java error:" + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("run java error:" + e.getMessage());
        }
    }

}
