package com.fileprocessing.FileProcessing.modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Component
public class ImageModule implements AbstractModule{
    public Integer getFileSize(String name) throws IOException {
        File file = new File(name);
        BufferedImage image = ImageIO.read(file);
        return image.getHeight() * image.getWidth();
    }
    public String getFileSizeDesk(){
        return "Вывод размера изображения";
    }
    public String getInformation(String name) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(new File(name));
        String result = "";
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                result += String.format("[%s] - %s = %s \n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    result += String.format("ERROR: %s", error);
                }
            }
        }

        return result;
    }
    public String getInformationDesk(){
        return "Вывод информации exif";
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
        if(FilenameUtils.getExtension(name).equals(TypeModules.png.toString()))
            return true;
        return false;
    }

    @Override
    public TypeModules getTypeModule() {
        return TypeModules.png;
    }

    @Override
    public String getDeskFunctions() {
        return "getFileSize - " + getFileSizeDesk() + "\n" + "getInformation - " + getInformationDesk() + "\n"
                + "renameFile - " + renameFileDesk();
    }

    @Override
    public String useFunction(String nameFuction, String namefile, String path) throws IOException, ImageProcessingException {
        if(nameFuction.equals("getFileSize"))
            return getFileSize(namefile).toString();
        else if (nameFuction.equals("getInformation"))
            return getInformation(namefile);
        else if(nameFuction.equals("renameFile"))
            return renameFile(namefile, path);
        else
            return "Неверная команда";
    }
}
