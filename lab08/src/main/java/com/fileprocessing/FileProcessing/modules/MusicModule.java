package com.fileprocessing.FileProcessing.modules;

import com.drew.imaging.ImageProcessingException;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.sound.sampled.AudioFileFormat;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

@Component
public class MusicModule implements AbstractModule{
    public String getName(String fileName) {
        String result ="";
        try {

            InputStream input = new FileInputStream(new File(fileName));
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();

            // List all metadata
            String[] metadataNames = metadata.names();

            for(String name : metadataNames){
                System.out.println(name + ": " + metadata.get(name));
            }

            // Retrieve the necessary info from metadata
            // Names - title, xmpDM:artist etc. - mentioned below may differ based
            result += ("Title: " + metadata.get("title") + "\n");
            result += ("Artists: " + metadata.get("xmpDM:artist") + "\n");
            result += ("Composer : "+metadata.get("xmpDM:composer") + "\n");
            result += ("Genre : "+metadata.get("xmpDM:genre") + "\n");
            result += ("Album : "+metadata.get("xmpDM:album") + "\n");

        } catch (SAXException | TikaException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    public String getNameDesk(){
        return "вывод названия трека из тегов";
    }
    public String getLenght(String fileName) throws IOException {
//        File file = new File("filename.mp3");
//        AudioFileFormat baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(file);
//        Map properties = baseFileFormat.properties();
//        return  (Long) properties.get("duration");
        return "";
    }
    public String getLenghtDesk(){
        return "вывод длительности в секундах";
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
        if(FilenameUtils.getExtension(name).equals(TypeModules.mp3.toString()))
            return true;
        return false;
    }

    @Override
    public TypeModules getTypeModule() {
            return TypeModules.mp3;
    }

    @Override
    public String getDeskFunctions() {
        return "getName - " + getNameDesk() + "\n" + "getLenght - " + getLenghtDesk() + "\n"
                + "renameFile - " + renameFileDesk();
    }

    @Override
    public String useFunction(String nameFuction, String namefile, String path) throws IOException, ImageProcessingException {
        if(nameFuction.equals("getName"))
            return getName(namefile);
        else if(nameFuction.equals("getLenght"))
            return getLenght(namefile);
        else if(nameFuction.equals("renameFile"))
            return renameFile(namefile, path);
        else
            return "Неверная команда";
    }
}
