package com.example.FilesManager.servlets;

import com.example.FilesManager.models.User;
import com.example.FilesManager.services.CookieUtil;
import com.example.FilesManager.services.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilesServlet extends HttpServlet {
    private static String storage_path = "C:/Work/java/temp/Files-Manager/";
    private static String start_path = "files";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = DbService.USER_SERVICE.getUserByCookies(req.getCookies());

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("currentTime", DATE_FORMAT.format(new Date()));

        String path = req.getParameter("path");
        String currentPath = start_path + '/' + user.getLogin() + '/';

        if (path == null || !path.contains(currentPath)) {
            path = currentPath;
        }

//        if(req.getParameter("path") != null)
//            currentPath = getPath(req.getParameter("path"));



        System.out.println(path);
        String newPath = (storage_path+ path).replaceAll("//", "/");
        File storage = new File(newPath);

        System.out.println(newPath);

        if (!storage.exists()) {
            storage.mkdirs();
        }


        if(storage.isDirectory()) {

            showFiles(req, storage);
            req.setAttribute("currentPath", path.replaceAll("//", "/"));
            req.getRequestDispatcher("files.jsp").forward(req, resp);
        }
        else {
            downloadFile(resp, storage);
        }
    }
    private String getPath(String path){
        String[] arrPath = path.split("/");
        System.out.println(path);
        String res = "";

        for(Integer i = 0; i< arrPath.length; i++){
            if(i == arrPath.length - 1)
                res =res +  arrPath[i];
            else
                res =res +  arrPath[i] + '/';
        }

        return res;
    }
    private void showFiles(HttpServletRequest req, File dir) {
        List<String> directories = new ArrayList<>();
        List<String> files = new ArrayList<>();
        for ( File file : dir.listFiles() ){
            if ( file.isFile())
                files.add(file.getName());

            if(file.isDirectory())
                directories.add(file.getName());
        }
        req.setAttribute("files", files);
        req.setAttribute("directories", directories);
    }
    private void downloadFile(HttpServletResponse resp, File file) throws IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=" + file.getName());

        try (InputStream in = Files.newInputStream(file.toPath()); OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[1048];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("exit") != null) {
            CookieUtil.addCookie(resp, "login", null);
            CookieUtil.addCookie(resp, "password", null);
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
