package top.weiyuexin.service;

import javax.tools.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: JavaRunTest
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/27 23:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JavaRunTest {
    private static String filePath = "F:\\IDEA_WorkSpace\\Java\\Hello.java";
    private static String classPath = "F:\\IDEA_WorkSpace\\Java\\Hello";
    private static String binDir = "F:\\IDEA_WorkSpace\\Java";

    public static void main1(String[] args) {
        File binOutDir = new File(binDir);
        if (!binOutDir.exists()){
            binOutDir.mkdirs();
        }
        ProcessBuilder pb = new ProcessBuilder("javac","-encoding","UTF-8","-d",binDir,filePath);
        try {
            final Process prco = pb.start();
            System.out.println(prco);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws IOException {
        File binOutDir = new File(binDir);
        if (!binOutDir.exists()){
            binOutDir.mkdirs();
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "-encoding", "UTF-8", "-d", binDir, filePath);

        //通过Runtime运行新的进程
        Runtime run =Runtime.getRuntime();
        Process process = run.exec("java -cp F:\\IDEA_WorkSpace\\Java Hello");
        InputStream in=process.getInputStream();
        BufferedReader bw1=new BufferedReader(new InputStreamReader(in));
        String info="";
        while((info=bw1.readLine())!=null){
            System.out.println(info);
        }


    }
}
