package sira.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sira.bean.Agence;
import sira.dao.AgenceDAO;
import sira.dao.ConnexionBD;
import sira.forms.Form_agence;

/**
 * Servlet implementation class Gestion_agance
 */
@WebServlet( "/Gestion_agence" )
@MultipartConfig
public class Gestion_agence extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String                    vue              = "/index.jsp";

    final String              VUE_DEF          = "/WEB-INF/vues/backOffice/";
    private final String      CHEMIN           = "C:\\FRMT\\JEE\\CDA_JEE\\projet_sira\\src\\main\\webapp\\utils\\images\\agences\\";
    AgenceDAO                 agenceDAO        = new AgenceDAO( ConnexionBD.getConnection() );

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gestion_agence() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String action = request.getParameter( "action" );
        vue = VUE_DEF + "gestion_agence.jsp";

        List<Agence> agences = agenceDAO.getAll();
        request.setAttribute( "agences", agences );

        if ( action != null ) {
            if ( action.equals( "update" ) ) {
                int id = Integer.valueOf( request.getParameter( "id" ) );
                Agence agence = agenceDAO.getOne( id );
                request.setAttribute( "agence", agence );

            }
        }

        getServletContext().getRequestDispatcher( vue ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String action = request.getParameter( "action" );
        String id_agence = request.getParameter( "id_agence" );

        Form_agence form_agence = new Form_agence();

        if ( action != null ) {

            Part part = request.getPart( "photo" );
            String fileName = part.getSubmittedFileName();
            String path = CHEMIN + File.separator + fileName;
            InputStream is = part.getInputStream();

            if ( action.equals( "Ajouter" ) && id_agence.length() == 0 ) {

                boolean success = upload( is, path );

                Agence agence = form_agence.editerAgence( request );

                if ( agence != null && success ) {
                    agence.setPhoto( fileName );
                    agenceDAO.inserer( agence );
                    response.sendRedirect( "Gestion_agence" );
                    return;
                }
                request.setAttribute( "erreur_champ", form_agence );

            } else if ( action.equals( "Ajouter" ) && id_agence != null ) {
                int id = Integer.valueOf( id_agence );
                Agence agenceActuelle = agenceDAO.getOne( id );

                if ( fileName.length() > 0 ) {
                    File file = new File( CHEMIN + agenceActuelle.getPhoto() );
                    file.delete();
                    upload( is, path );
                } else
                    fileName = agenceActuelle.getPhoto();

                Agence agence = form_agence.editerAgence( request );

                if ( agence != null ) {
                    agence.setPhoto( fileName );
                    agence.setId_agence( id );
                    agenceDAO.update( agence );
                    response.sendRedirect( "Gestion_agence" );
                    return;
                }
                request.setAttribute( "erreur_champ", form_agence );

            }
        }

        doGet( request, response );
    }

    public boolean upload( InputStream is, String path ) {
        boolean bool = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read( byt );
            FileOutputStream fos = new FileOutputStream( path );
            fos.write( byt );
            fos.flush();
            fos.close();
            bool = true;
        } catch ( Exception e ) {
            System.out.println( e.getMessage() );
        }
        return bool;
    }

}
