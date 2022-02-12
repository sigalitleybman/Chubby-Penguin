package com.example.worldwideski2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * LanguageSetter is responsible for setting the language.
 */
public class LanguageSetter {
    private Context context;
    private SharedPreferences sharedPreferences;

    public LanguageSetter(Context context)
    {
        setContext(context);
        setSharedPreferences(context);
    }

    private void setContext(Context context) {
        if (context != null) {
            this.context = context;
        }
    }

    private void setSharedPreferences(Context context) {
        if (context != null) {
            this.sharedPreferences = context.getSharedPreferences("LANG", Context.MODE_PRIVATE);
        }
    }

    private void setLang(String code)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang",code);
        editor.commit();
    }

    public void updateResource(String code)
    {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        setLang(code);
    }
}