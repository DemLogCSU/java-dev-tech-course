package com.fileprocessing.FileProcessing.modules;

import com.drew.imaging.ImageProcessingException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DirModule implements AbstractModule{
    public String getFiles(String dirName){
        String res = "";
        File dir = new File(dirName); //path указывает на директорию
        for ( File file : dir.listFiles() ){
            if ( file.isFile() || file.isDirectory())
                res += file.getName() + "\n";
        }

        return res;
    }
    public String getFilesDesk(){
        return "вывод списка файлов в каталоге";
    }
    public Long getFilesSize(String dirName){
        Long res = (long)0;
        File dir = new File(dirName); //path указывает на директорию
        for ( File file : dir.listFiles() ){
            if ( file.isFile() )
                res += file.length();
        }

        return res;
    }
    public String getFilesSizeDesk(){
        return "подсчет размера всех файлов в каталоге";
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
        if(!name.contains("."))
            return true;
        return false;
    }

    @Override
    public TypeModules getTypeModule() {
        return TypeModules.dir;
    }

    @Override
    public String getDeskFunctions() {
        return "getFiles - " + getFilesDesk() + "\n" + "getFilesSize - " + getFilesSizeDesk() + "\n"
                + "renameFile - " + renameFileDesk();
    }

    @Override
    public String useFunction(String nameFuction, String namefile, String path) throws IOException, ImageProcessingException {
        if(nameFuction.equals("getFiles"))
            return getFiles(namefile);
        else if(nameFuction.equals("getFilesSize"))
            return getFilesSize(namefile).toString() + "bytes";
        else if(nameFuction.equals("renameFile"))
            return renameFile(namefile, path);
        else
            return "Неверная команда";
    }
}
