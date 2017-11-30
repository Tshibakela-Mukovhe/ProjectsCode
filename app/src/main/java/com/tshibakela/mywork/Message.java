package com.tshibakela.mywork;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tshibakela on 2017/07/18.
 */


public class Message {
    /**this is the model container that contains data that are to be saved in the database
     * and display in  the user interface
     */
    public static void message(Context context, String message) {


        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
