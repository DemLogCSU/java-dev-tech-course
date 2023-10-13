package site.dlsky;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class MainServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathParam = req.getParameter("path");

        System.out.println(pathParam);
        Path path = null;
        if (pathParam != null) path = Paths.get(pathParam).toAbsolutePath();

        if (path == null || pathParam.equals("null") || !path.toString().startsWith(System.getProperty("user.home")))
        {
            pathParam = System.getProperty("user.home");
        }

        path = Paths.get(pathParam).toAbsolutePath();

        if (!Files.exists(path))
        {
            resp.setStatus(404);
            resp.getWriter().write("Incorrect path");
            return;
        }

        File rootFile = path.toFile();
        if(!rootFile.isDirectory())
        {
            loadFile(resp,rootFile);
            return;
        }
        File[] subFiles = rootFile.listFiles();

        req.setAttribute("files", subFiles);
        req.setAttribute("rootFile", rootFile);
        req.setAttribute("attrs", Files.readAttributes(path, BasicFileAttributes.class));
        req.setAttribute("parentPath", req.getServletPath() + "?path=" + rootFile.getParent());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("main.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void loadFile(HttpServletResponse resp, File file) throws IOException {
        resp.setContentType("text/html");
        resp.setHeader("Content-disposition", "attachment; filename=" + file.getName());

        OutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[2048];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
