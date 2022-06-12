package sira.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sira.bean.Membre;

public class Form_membre {

    private static final String CHAMP_CIVILITY = "civility";
    private static final String CHAMP_PRENOM   = "prenom";
    private static final String CHAMP_NOM      = "nom";
    private static final String CHAMP_PSEUDO   = "pseudo";
    private static final String CHAMP_MDP      = "mdp";
    private static final String CHAMP_EMAIL    = "email";
    private static final String CHAMP_STATUT   = "statut";

    private Map<String, String> erreurs        = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Membre editerMembre( HttpServletRequest request ) {

        Membre user = new Membre();

        String sexe = getValueChamp( request, CHAMP_CIVILITY, 5 );
        String prenom = getValueChamp( request, CHAMP_PRENOM, 2 );
        String nom = getValueChamp( request, CHAMP_NOM, 2 );
        String pseudo = getValueChamp( request, CHAMP_PSEUDO, 2 );
        String mdp = getValueChamp( request, CHAMP_MDP, 4 );
        String mail = getValueChamp( request, CHAMP_EMAIL, 6 );

        String statut = request.getParameter( CHAMP_STATUT );
        if ( statut == null ) {
            statut = "client";
        }

        if ( erreurs.isEmpty() ) {
            user.setCivility( sexe );
            user.setPrenom( prenom );
            user.setNom( nom );
            user.setPseudo( pseudo );
            user.setMdp( mdp );
            user.setEmail( mail );
            user.setStatut( statut );

            return user;
        }

        return null;
    }

    private void validationChamp( String champ, int longueur ) throws Exception {
        if ( champ != null || champ.equals( CHAMP_CIVILITY ) )
            if ( champ.length() < longueur )
                throw new Exception( "msg" );

    }

    public String getValueChamp( HttpServletRequest request, String champ, int taille ) {
        String champ_input = request.getParameter( champ );
        try {
            validationChamp( champ_input, taille );
        } catch ( Exception e ) {
            erreurs.put( champ, "remplir le champ" );
            return null;
        }
        return champ_input;
    }

}
