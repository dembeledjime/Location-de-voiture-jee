package sira.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sira.bean.Membre;
import sira.dao.ConnexionBD;
import sira.dao.MembreDAO;
import sira.forms.Form_membre;

/**
 * Servlet implementation class User
 */
@WebServlet( "/User" )
public class User extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String                    vue              = "/index.jsp";

    final String              VUE_DEF          = "/WEB-INF/vues/";
    MembreDAO                 dao              = new MembreDAO( ConnexionBD.getConnection() );

    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
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
        HttpSession session = request.getSession();

        if ( action != null ) {
            if ( action.equals( "inscription" ) ) {
                vue = VUE_DEF + "frontOffice/inscription.jsp";

            } else if ( action.equals( "connexion" ) ) {

            } else if ( action.equals( "deconnexion" ) ) {
                session.invalidate();
                response.sendRedirect( "Home" );
                return;
            } else if ( action.equals( "gestionM" ) ) {
                request.setAttribute( "users", dao.getAll() );
                vue = VUE_DEF + "backOffice/gestion_membre.jsp";
            } else if ( action.equals( "delete" ) ) {
                Membre user = dao.getOne( Integer.valueOf( request.getParameter( "id" ) ) );
                dao.delete( user );
                response.sendRedirect( "User?action=gestionM" );
                return;
            } else if ( action.equals( "update" ) ) {
                Membre user = dao.getOne( Integer.valueOf( request.getParameter( "id" ) ) );
                request.setAttribute( "users", dao.getAll() );
                request.setAttribute( "user", user );
                vue = VUE_DEF + "backOffice/gestion_membre.jsp";
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
        String id = request.getParameter( "id_membre" );
        Form_membre form_membre = new Form_membre();

        HttpSession session = request.getSession();

        if ( action != null ) {

            if ( action.equals( "Inscription" ) && id == null ) {
                Membre user = form_membre.editerMembre( request );
                if ( user != null ) {
                    dao.inserer( user );
                    if ( request.getParameter( "admin" ) != null
                            && request.getParameter( "admin" ).equals( "admin" ) ) {
                        response.sendRedirect( "User?action=gestionM" );
                        return;
                    }
                    response.sendRedirect( "Home" );
                    return;
                }

                request.setAttribute( "erreur_champ", form_membre );
                vue = VUE_DEF + "frontOffice/inscription.jsp";

            } else if ( action.equals( "connexion" ) ) {
                String login = request.getParameter( "pseudo" );
                String mdp = request.getParameter( "mdp" );

                Membre user = dao.connexion( login, mdp );
                if ( user != null ) {
                    session.setAttribute( "user", user );

                    response.sendRedirect( "Home" );
                    return;
                }
            } else if ( action.equals( "Inscription" ) && id != null ) {
                Membre user = form_membre.editerMembre( request );
                if ( user != null ) {
                    user.setId_membre( Integer.valueOf( id ) );
                    dao.update( user );
                    if ( request.getParameter( "admin" ) != null
                            && request.getParameter( "admin" ).equals( "admin" ) ) {
                        response.sendRedirect( "User?action=gestionM" );
                        return;
                    }
                    response.sendRedirect( "Home" );
                    return;
                }

                request.setAttribute( "erreur_champ", form_membre );
                vue = VUE_DEF + "frontOffice/inscription.jsp";

            }
        }

        getServletContext().getRequestDispatcher( vue ).forward( request, response );
    }

}
