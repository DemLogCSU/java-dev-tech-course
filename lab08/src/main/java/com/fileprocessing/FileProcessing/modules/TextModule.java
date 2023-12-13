package com.fileprocessing.FileProcessing.modules;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.drew.imaging.ImageProcessingException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

@Component
public class TextModule implements AbstractModule {

        public Integer getCountLine(String name) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(name));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
    public String getCountLineDesk(){
        return "Получение количество строк в файле";
    }
    public String getUniqueLetters(String name) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(new File(name)));
        HashMap<Character, Integer> map = new HashMap<>();

        String result = "";

        char x;
        int size = 0;

        while (bfr.ready()) {
            if (!Character.isLetter(x = (char) bfr.read())) continue;
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else map.put(x, 1);
            size++;
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            result += (entry.getKey() + " -> " + Math.ceil((entry.getValue() * 100. / size) * 100) / 100  + "%" + "\n");
        }

        return  result;
    }
    public String getUniqueLettersDesk(){
        return "Вывод частоты вхождения каждого символа";
    }
    public String renameFile(String filename, String path){
        File file = new File(filename);
        Scanner in = new Scanner(System.in);
        String newName = in.nextLine();
        File newFile = new File(path + newName);
        if(file.renameTo(newFile))
            return "Файл переименован в " + newName;
        else
            return "Ошибка в новом имени";
    }
    public String renameFileDesk(){
            return "переименовать файл";
    }

    @Override
    public boolean checkFormatFile(String name) {
        if(FilenameUtils.getExtension(name).equals(TypeModules.txt.toString()))
            return true;
        return false;
    }

    @Override
    public TypeModules getTypeModule() {
        return TypeModules.txt;
    }

    @Override
    public String getDeskFunctions() {
        return "getCountLine - " + getCountLineDesk() + "\n" + "getUniqueLetters - " + getUniqueLettersDesk() + "\n"
                + "renameFile - " + renameFileDesk();
    }

    @Override
    public String useFunction(String nameFuction, String namefile, String path) throws IOException, ImageProcessingException {
        if(nameFuction.equals("getCountLine"))
            return getCountLine(namefile).toString();
        else if(nameFuction.equals("getUniqueLetters"))
            return getUniqueLetters(namefile);
        else if(nameFuction.equals("renameFile"))
            return renameFile(namefile, path);
        else
            return "Неверная команда";
    }
}
