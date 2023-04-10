package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.CodeMapper;
import top.weiyuexin.pojo.Code;
import top.weiyuexin.pojo.TestCase;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.CodeService;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: CodeServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/16 21:03
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {
    /**
     * 编译Java程序
     *
     * @param code
     * @return
     */
    @Override
    public R compileJava(Code code) {
        StringBuffer errorInfo = new StringBuffer();
        final String[] errorMsg = {""};
        Process p = null;
        try {
            //1.编译java文件
            p = Runtime.getRuntime().exec("javac Main.java", null, new File(code.getCodePath()));
            // 获取进程的错误流
            final InputStream is = p.getErrorStream();
            // 开一个线程,读标准错误流
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            if (line != null) {
                                errorInfo.append(line + "\n");
                            }
                        }
                        if (!errorInfo.toString().equals("")) {
                            errorMsg[0] = errorInfo.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (errorMsg[0].equals("")) {
            return R.success("编译成功");
        } else {
            return R.error(errorMsg[0], "编译失败");
        }
    }

    /**
     * 运行编译好的Java程序
     *
     * @param code
     * @param testCase
     * @return
     */
    @Override
    public R runJava(Code code, TestCase testCase) {
        String inputData = testCase.getInput();
        String outputData = "";
        try {
            Process process = Runtime.getRuntime().exec("java Main", null, new File(code.getCodePath()));
            if (!inputData.equals("")) {
                BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                bout.write(inputData);
                bout.close();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line = br.readLine()) != null) {
                //b.append(line + "\n");
                b.append(line);
            }
            outputData = b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("Java程序执行时发送错误");
        }
        return R.success(outputData, "Java程序执行成功");
    }

    /**
     * 编译c程序，返回编译结果
     *
     * @param code
     * @return
     */
    @Override
    public R compileC(Code code) {
        StringBuffer errorInfo = new StringBuffer();
        final String[] errorMsg = {""};
        Process p = null;
        try {
            //1.编译c文件
            p = Runtime.getRuntime().exec("gcc main.c" + " -o " + "main", null, new File(code.getCodePath()));
            // 获取进程的错误流
            final InputStream is = p.getErrorStream();
            // 开一个线程,读标准错误流
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            if (line != null) {
                                errorInfo.append(line + "\n");
                            }
                        }
                        if (!errorInfo.toString().equals("")) {
                            errorMsg[0] = errorInfo.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (errorMsg[0].equals("")) {
            return R.success("编译成功");
        } else {
            return R.error(errorMsg[0], "编译失败");
        }
    }

    /**
     * 执行c程序，返回执行结果
     *
     * @param code
     * @param testCase
     * @return
     */
    @Override
    public R runC(Code code, TestCase testCase) {
        String inputData = testCase.getInput();
        String outputData = "";
        try {
            Process process = Runtime.getRuntime().exec("./main", null, new File(code.getCodePath()));
            if (!inputData.equals("")) {
                BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                bout.write(inputData);
                bout.close();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line = br.readLine()) != null) {
                //b.append(line + "\n");
                b.append(line);
            }
            outputData = b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("c程序执行时发送错误");
        }
        return R.success(outputData, "c程序执行成功");
    }

    /**
     * 编译C++程序
     *
     * @param code
     * @return
     */
    @Override
    public R compileCpp(Code code) {
        StringBuffer errorInfo = new StringBuffer();
        final String[] errorMsg = {""};
        Process p = null;
        try {
            //1.编译c文件
            p = Runtime.getRuntime().exec("g++ main.cpp" + " -o " + "main", null, new File(code.getCodePath()));
            // 获取进程的错误流
            final InputStream is = p.getErrorStream();
            // 开一个线程,读标准错误流
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            if (line != null) {
                                errorInfo.append(line + "\n");
                            }
                        }
                        if (!errorInfo.toString().equals("")) {
                            errorMsg[0] = errorInfo.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (errorMsg[0].equals("")) {
            return R.success("编译成功");
        } else {
            return R.error(errorMsg[0], "编译失败");
        }
    }

    /**
     * 运行C++程序
     *
     * @param code
     * @param testCase
     * @return
     */
    @Override
    public R runCpp(Code code, TestCase testCase) {
        String inputData = testCase.getInput();
        String outputData = "";
        try {
            Process process = Runtime.getRuntime().exec("./main", null, new File(code.getCodePath()));
            if (!inputData.equals("")) {
                BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                bout.write(inputData);
                bout.close();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line = br.readLine()) != null) {
                //b.append(line + "\n");
                b.append(line);
            }
            outputData = b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("C++程序执行时发送错误");
        }
        return R.success(outputData, "C++程序执行成功");
    }

    /**
     * 编译GO程序
     *
     * @param code
     * @return
     */
    @Override
    public R compileGolang(Code code) {
        StringBuffer errorInfo = new StringBuffer();
        final String[] errorMsg = {""};
        Process p = null;
        try {
            //1.编译go文件
            p = Runtime.getRuntime().exec("go build main.go", null, new File(code.getCodePath()));
            // 获取进程的错误流
            final InputStream is = p.getErrorStream();
            // 开一个线程,读标准错误流
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("GBK")));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            if (line != null) {
                                errorInfo.append(line + "\n");
                            }
                        }
                        if (!errorInfo.toString().equals("")) {
                            errorMsg[0] = errorInfo.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (errorMsg[0].equals("")) {
            return R.success("编译成功");
        } else {
            return R.error(errorMsg[0], "编译失败");
        }
    }

    /**
     * 运行GO程序
     *
     * @param code
     * @param testCase
     * @return
     */
    @Override
    public R runGolang(Code code, TestCase testCase) {
        String inputData = testCase.getInput();
        String outputData = "";
        try {
            Process process = Runtime.getRuntime().exec("./main", null, new File(code.getCodePath()));
            if (!inputData.equals("")) {
                BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                bout.write(inputData);
                bout.close();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line = br.readLine()) != null) {
                //b.append(line + "\n");
                b.append(line);
            }
            outputData = b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("GO程序执行时发送错误");
        }
        return R.success(outputData, "GO程序执行成功");
    }

    /**
     * 运行Python程序
     *
     * @param code
     * @param testCase
     * @return
     */
    @Override
    public R runPython(Code code, TestCase testCase) {
        String inputData = testCase.getInput();
        String outputData = "";
        try {
            Process process = Runtime.getRuntime().exec("python3 main.py", null, new File(code.getCodePath()));
            if (!inputData.equals("")) {
                BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                bout.write(inputData);
                bout.close();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line = br.readLine()) != null) {
                //b.append(line + "\n");
                b.append(line);
            }
            outputData = b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("python程序执行时发送错误");
        }
        return R.success(outputData, "python程序执行成功");
    }

    /**
     * 运行JavaScript程序
     *
     * @param code
     * @param testCase
     * @return
     */
    @Override
    public R runJavaScript(Code code, TestCase testCase) {
        String inputData = testCase.getInput();
        String outputData = "";
        try {
            Process process = Runtime.getRuntime().exec("node main.js", null, new File(code.getCodePath()));
            if (!inputData.equals("")) {
                BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                bout.write(inputData);
                bout.close();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line = br.readLine()) != null) {
                //b.append(line + "\n");
                b.append(line);
            }
            outputData = b.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("JavaScript程序执行时发送错误");
        }
        return R.success(outputData, "JavaScript程序执行成功");
    }
}
