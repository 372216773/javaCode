package Day1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException {

        FileWriter fileWriter = new FileWriter("D:\\BaiduNetdiskDownload\\file.txt",false);

        fileWriter.write("我是追加的内容");

        fileWriter.close();

        File file = new File("");
        HashMap<Integer, String> hashMap = new HashMap<>();

    }
}
