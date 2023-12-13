package com.fileprocessing.FileProcessing;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.exif.PanasonicRawIFD0Descriptor;
import com.fileprocessing.FileProcessing.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@Service
public class FileProcessingApplication {
	@Resource(name="modulesMap")
	private Map<TypeModules, AbstractModule> _modules;
	private static String path = "dirproject/";
	public FileProcessingApplication(){
	}

	FileProcessingApplication(Map<TypeModules, AbstractModule> modules){
		_modules = modules;
	}
	public static void main(String[] args) throws IOException, ImageProcessingException {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ModuleController.class);
		ctx.scan("com.fileprocessing.FileProcessing");
		ctx.refresh();
		FileProcessingApplication app = (FileProcessingApplication) ctx.getBean("fileProcessingApplication");

		Scanner in = new Scanner(System.in);
		String nameFile = "";

		while(!nameFile.equals("exit")){
			System.out.println(((DirModule)app._modules.get(TypeModules.dir)).getFiles(path));
			nameFile = in.nextLine();
			app.checkFile(path + nameFile);
		}

		ctx.close();
	}
	private void checkFile(String name) throws IOException, ImageProcessingException {
		if(_modules.get(TypeModules.txt).checkFormatFile(name)){
			moduleEvent(name, TypeModules.txt);
		}
		else if (_modules.get(TypeModules.png).checkFormatFile(name)) {
			moduleEvent(name, TypeModules.png);
		}
		else if (_modules.get(TypeModules.mp3).checkFormatFile(name)) {
			moduleEvent(name, TypeModules.mp3);
		}
		else if (_modules.get(TypeModules.dir).checkFormatFile(name)) {
			moduleEvent(name, TypeModules.dir);
		}
		else
			System.out.println("Неверный формат");
	}
	private void moduleEvent(String fileName, TypeModules typeModules) throws ImageProcessingException, IOException {
		AbstractModule module = _modules.get(typeModules);
		Scanner in = new Scanner(System.in);
		String func = "";
		while(!func.equals("exit")){
			System.out.println(module.getDeskFunctions());
			System.out.println("Выберите функцию");
			func = in.nextLine();
			System.out.println(module.useFunction(func, fileName, path));

		}

	}

}
