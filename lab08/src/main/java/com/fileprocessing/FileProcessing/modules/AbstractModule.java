package com.fileprocessing.FileProcessing.modules;

import com.drew.imaging.ImageProcessingException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

public interface AbstractModule {

    public boolean checkFormatFile(String name);
    public TypeModules getTypeModule();
    public String getDeskFunctions();
    public String useFunction(String nameFuction, String namefile, String path) throws IOException, ImageProcessingException;
}
