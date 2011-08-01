package ar.org.promeba.util.init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ServletInicializacion extends HttpServlet
{
            // Caching the DataSource - It is obtained in the Servlet.init() method
            private javax.sql.DataSource ds = null;

            
            
            // This Happens Once and is Reused
            public void init(ServletConfig config) throws ServletException   {
              super.init(config);
              String path= config.getServletContext().getRealPath("/WEB-INF");
              String[] configFiles = new String[] { path +  "/datos.xml", path + "/servicios.xml"};
              BeanFactory factory = new FileSystemXmlApplicationContext(configFiles);
            }
}