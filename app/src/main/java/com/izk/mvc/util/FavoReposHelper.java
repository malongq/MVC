package com.izk.mvc.util;

import android.app.Application;
import android.content.Context;

import com.izk.mvc.model.Repo;


public class FavoReposHelper {


    static FavoReposHelper instance;

    public static synchronized FavoReposHelper getInstance() {
        return instance;
    }


    public static void init(Application application) {
        instance = new FavoReposHelper(application);
    }

    String favoReposJson = "";

    Context context;

    private FavoReposHelper(Context context) {
        this.context = context;
        favoReposJson = PreferenceManager.getString(context, "favo_repos", "");
    }

    public boolean contains(Repo repo) {

        if(repo != null) {
            return favoReposJson.contains(repo.getHref());
        }
        return false ;
    }

    public void addFavo(Repo repo) {
        favoReposJson = favoReposJson + "," + repo.getHref();
        saveToPref();
    }

    public void removeFavo(Repo repo) {
        favoReposJson = favoReposJson.replace(repo.getHref(),"");

        saveToPref();
    }

    private void saveToPref() {
        PreferenceManager.putString(context, "favo_repos", favoReposJson);
    }
}
