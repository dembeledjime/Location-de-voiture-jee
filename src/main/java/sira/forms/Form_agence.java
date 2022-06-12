package sira.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sira.bean.Agence;

public class Form_agence {

    private static final String CHAMP_TITRE       = "titre";
    private static final String CHAMP_ADRESSE     = "adresse";
    private static final String CHAMP_CP          = "cp";
    private static final String CHAMP_VILLE       = "ville";
    private static final String CHAMP_DESCRIPTION = "description";
    private static final String CHAMP_PHOTO       = "photo";

    private Map<String, String> erreurs           = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Agence editerAgence( HttpServletRequest request ) {

        Agence agence = new Agence();

        String titre = getValueChamp( request, CHAMP_TITRE, 5 );
        String adresse = getValueChamp( request, CHAMP_ADRESSE, 5 );
        String cp = getValueChamp( request, CHAMP_CP, 5 );
        String ville = getValueChamp( request, CHAMP_VILLE, 2 );
        String desc = getValueChamp( request, CHAMP_DESCRIPTION, 4 );
        String photo = getValueChamp( request, CHAMP_PHOTO, 4 );

        if ( erreurs.isEmpty() ) {
            agence.setTitre( titre );
            agence.setAdresse( adresse );
            agence.setCp( Integer.valueOf( cp ) );
            agence.setVille( ville );
            agence.setDescription( desc );
            agence.setPhoto( photo );

            return agence;
        }

        return null;
    }

    private void validationChamp( String champ, int longueur ) throws Exception {
        if ( champ != null )
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
