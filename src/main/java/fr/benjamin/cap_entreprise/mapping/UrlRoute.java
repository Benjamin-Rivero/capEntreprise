package fr.benjamin.cap_entreprise.mapping;

public class UrlRoute {

    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTER = "/s-inscrire";

    public final static String URL_REVIEW = "/avis";
    public static final String URL_REVIEW_NEW = URL_REVIEW + "/nouveau";
    public static final String URL_REVIEW_SHOW = URL_REVIEW + "/{id}";

    public static final String URL_REVIEW_VALIDATE = URL_REVIEW_SHOW +"/validate";
    public static final String URL_REVIEW_REFUSE = URL_REVIEW_SHOW +"/refuse";
    public static final String URL_GAME ="/jeu" ;

    public static final String URL_GAME_EDIT = URL_GAME+"/editer";
    public static final String URL_GAME_NEW =URL_GAME+"/nouveau" ;

    public static final String URL_GAME_ID = URL_GAME + "/{id}";
    public static final String URL_GAME_DELETE =URL_GAME_ID+"/supprimer" ;
}
