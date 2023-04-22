package com.vanskarner.cleanmovie.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class FileUtils {

    public static String readFile(String filePath) throws IOException {
        String receiveString;
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        InputStream inputStream = Objects.requireNonNull(classLoader).getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while ((receiveString = bufferedReader.readLine()) != null)
            stringBuilder.append(receiveString);
        inputStream.close();
        return stringBuilder.toString();
    }

}