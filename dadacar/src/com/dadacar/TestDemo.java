package com.dadacar;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestDemo {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(new File("D:\\wj\\javaCode\\dadacar\\resources\\mysql.properties"));
        char[] chars = new char[1024];
        fileReader.read(chars);
    }
}
