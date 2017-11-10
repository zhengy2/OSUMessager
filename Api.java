package com.example.ramak.myapplication;

/**
 * Created by ramak on 10/17/2017.
 */

public class Api {

    //private static final String ROOT_URL = "http://10.249.88.58:3000/osu/";
    //private static final String ROOT_URL = "http://10.0.0.226:5000/";
    private static final String ROOT_URL = "https://osumsg.herokuapp.com/";

    public static final String URL_REGISTER_USER = ROOT_URL + "regUser";
    public static final String URL_VALIDATE_USER = ROOT_URL + "valUser";
    public static final String URL_LISTLOCATIONS_USER = ROOT_URL + "listLocations";
    public static final String URL_INSERTUSERLOCATION_USER = ROOT_URL + "insertUserLocation";
    public static final String URL_UPDATEUSERLOCATION_USER = ROOT_URL + "updateUserLocation";
    public static final String URL_GET_USER = ROOT_URL + "getUser";
    public static final String URL_UPDATE_USER = ROOT_URL + "updateUser";
    public static final String URL_DELETE_USER = ROOT_URL + "deletehero&id=";

}
