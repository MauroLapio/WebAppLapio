package it.itismeucci.webapplapio.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetClassi extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Utenti</title>");            
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n");
            out.println("</head>");
            out.println("<body>");
            out.println("<button class=\"btn btn-primary\" onclick=\"history.go(-1)\">BACK</button>");
            out.println("<h1>Classi</h1>");
            out.println("<table class='table'>\n");
            out.println("<thead>\n");
            out.println("<tr>\n");
            out.println("<th>ID</th>\n");
            out.println("<th>Anno</th>\n");
            out.println("<th>Sezione</th>\n");
            out.println("</tr>\n");
            out.println("</thead>\n");
            out.println("<tbody>\n");
            
            String query = "SELECT * FROM class";
            ResultSet rs = stmt.executeQuery(query);
            
            String idClasse;
            String annoClasse;
            String sezioneClasse;
            
            while(rs.next())
            {
                idClasse = rs.getString(1);
                annoClasse = rs.getString(2);
                sezioneClasse = rs.getString(3);
                
                out.println("<tr>\n<td>" + idClasse +"</td>\n<td>" + annoClasse + "</td>\n<td>" + sezioneClasse + "</td>\n</tr>\n");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e)
        {
            System.out.println("Errore: "+e);
        }
    }
    
    protected Connection getConnection()
    {
        String dbDRIVER = "com.mysql.jdbc.Driver";
        Connection con = null;

        String dbName = "fi_itis_meucci";
        String dbUser = "root";
        String dbPass = "password";

        String dbURL = "jdbc:mysql://localhost:3306/"+dbName; //sarà completato con il nome del db alla connessione

        try
        {
            Class.forName(dbDRIVER);
            con = DriverManager.getConnection(dbURL+"?serverTimezone=America/New_York", dbUser, dbPass); //viene aggiunta una qualsiasi timezone (in questo caso America/New_York) per eliminare errori
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Errore: " + e);
        }
            
        return con;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo()
    {
        return "Output delle classi";
    }

}
