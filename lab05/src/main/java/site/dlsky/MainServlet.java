package site.dlsky;

import site.dlsky.models.User;
import site.dlsky.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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

    private static final String FILES_ROOT = System.getProperty("user.home") + "/";

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
        User user = UserRepository.USER_REPOSITORY.getUserByCookies(req.getCookies());
        if(user == null) {
            resp.sendRedirect("/login");
        } else {
            String pathParam = req.getParameter("path");

            if (pathParam == null || !pathParam.startsWith(FILES_ROOT + user.getLogin())) {
                pathParam = createDirectory(user.getLogin());
            }

            System.out.println(pathParam);
            Path path = Paths.get(pathParam).toAbsolutePath();

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
    }

    private String createDirectory(String login) {
        String directoryPath = FILES_ROOT + login;
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return directoryPath;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("exitBtn") != null) {
            UserRepository.USER_REPOSITORY.removeUserBySession(MyCookie.getValue(req.getCookies(), "JSESSIONID"));
            MyCookie.addCookie(resp, "JSESSIONID", null);
            resp.sendRedirect("/");
        }
    }
}
