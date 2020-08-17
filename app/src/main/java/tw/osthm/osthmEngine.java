//NOTE: You shouldn't modify any codes in this engine yourself
//      as it might will conflicts with other themes.
package tw.osthm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class osthmEngine {

    public int themesVersion = 2;
    public int metadataVersion = 3;
    private SharedPreferences data;

    private void initializeData(Context mContext) {
        //Initializes some data here
        data = mContext.getSharedPreferences("teamdata", Activity.MODE_PRIVATE);
        if (data.getString("themelists", "").equals("")) data.edit().putString("themelists", "[]").apply();
        if (data.getString("currentTheme", "").equals("")) data.edit().putString("currentTheme", "default").apply();
    }

    private ArrayList<HashMap<String, Object>> getThemeListPrivate() {
        //Get theme from sharedpreferences (private method)
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        metadataarray.add(0, (HashMap<String, Object>) new HashMap<String, Object>().put("themesname", "Vanilla"));
        metadataarray.get(0).put("themesjson", "[{\"colorPrimary\":\"#2196F3\",\"colorPrimaryDark\":\"#1769AA\",\"colorBackgroundText\":\"#000000\",\"colorBackground\":\"#FFFFFF\",\"colorButton\":\"#F50057\",\"shadow\":\"1\",\"colorHint\":\"#A8A8A8\",\"colorRipple\":\"#40000000\",\"colorPrimaryCardImage\":\"#000000\",\"version\":\"" + Integer.toString(themesVersion) + "\",\"colorPrimaryText\":\"#FFFFFF\",\"colorPrimaryImage\":\"#FFFFFF\",\"colorBackgroundImage\":\"#2196F3\",\"colorBackgroundCardText\":\"#000000\",\"statusbarIcon\":\"1\",\"colorBackgroundCard\":\"#FFFFFF\",\"colorButtonText\":\"#FFFFFF\",\"colorPrimaryCardText\":\"#000000\",\"colorPrimaryCard\":\"#FFFFFF\",\"colorBackgroundCardImage\":\"#000000\"}]");
        metadataarray.get(0).put("themesinfo", "The default style theme of os-thm");
        metadataarray.get(0).put("themesauthor", "リェンーゆく");
        metadataarray.get(0).put("os-thm-version", Integer.toString(metadataVersion));
        metadataarray.get(0).put("uuid", "default");
        metadataarray.add(1, (HashMap<String, Object>) new HashMap<String, Object>().put("themesname", "Vanilla"));
        metadataarray.get(1).put("themesjson", "[{\"colorPrimary\":\"#2196F3\",\"colorPrimaryDark\":\"#252525\",\"colorBackgroundText\":\"#FFFFFF\",\"colorBackground\":\"#252525\",\"colorButton\":\"#F50057\",\"shadow\":\"1\",\"colorHint\":\"#808080\",\"colorRipple\":\"#40FFFFFF\",\"colorPrimaryCardImage\":\"#98A0A8\",\"version\":\"" + Integer.toString(themesVersion) + "\",\"colorPrimaryText\":\"#FFFFFF\",\"colorPrimaryImage\":\"#FFFFFF\",\"colorBackgroundImage\":\"#2196F3\",\"colorBackgroundCardText\":\"#98A0A8\",\"statusbarIcon\":\"1\",\"colorBackgroundCard\":\"#404040\",\"colorButtonText\":\"#FFFFFF\",\"colorPrimaryCardText\":\"#98A0A8\",\"colorPrimaryCard\":\"#404040\",\"colorBackgroundCardImage\":\"#98A0A8\"}]");
        metadataarray.get(1).put("themesinfo", "A Material dark theme for os-thm");
        metadataarray.get(1).put("themesauthor", "thatcakepiece");
        metadataarray.get(1).put("os-thm-version", Integer.toString(metadataVersion));
        metadataarray.get(1).put("uuid", "dark");
        return metadataarray;
    }

    public ArrayList<HashMap<String, Object>> getThemeList(Context mContext) {
        //Get theme from sharedpreferences (public method)
        initializeData(mContext);
        return getThemeListPrivate();
    }

    public void migrateOldTheme(Context mContext, String UID) {
        //Migrate specified old theme to newer version
        initializeData(mContext);
    }

    public void migrateAllOldTheme(Context mContext) {
        //Migrate all old themes to newer version
        initializeData(mContext);
    }

    public void addTheme(Context mContext, String colorPrimary, String colorPrimaryText, String colorPrimaryDark,
                            String statusbarIcon, String colorBackground, String colorBackgroundText,
                            String colorButton, String colorButtonText, String shadow, String colorRipple,
                            String colorHint, String colorPrimaryImage, String colorBackgroundImage,
                            String colorPrimaryCard, String colorBackgroundCard, String colorPrimaryCardText,
                            String colorBackgroundCardText, String colorPrimaryCardImage, String colorBackgroundCardImage,
                            String themesname, String themesinfo, String themesauthor) throws Exception {
        //Add new theme using given hex colors and generate new UUID
        addTheme(mContext, colorPrimary, colorPrimaryText, colorPrimaryDark, statusbarIcon, colorBackground,
                colorBackgroundText, colorButton, colorButtonText, shadow, colorRipple, colorHint,
                colorPrimaryImage, colorBackgroundImage, colorPrimaryCard, colorBackgroundCard,
                colorPrimaryCardText, colorBackgroundCardText, colorPrimaryCardImage, colorBackgroundCardImage,
                themesname, themesinfo, themesauthor, UUID.randomUUID().toString());
    }

    public void addTheme(Context mContext, String colorPrimary, String colorPrimaryText, String colorPrimaryDark,
                         String statusbarIcon, String colorBackground, String colorBackgroundText,
                         String colorButton, String colorButtonText, String shadow, String colorRipple,
                         String colorHint, String colorPrimaryImage, String colorBackgroundImage,
                         String colorPrimaryCard, String colorBackgroundCard, String colorPrimaryCardText,
                         String colorBackgroundCardText, String colorPrimaryCardImage, String colorBackgroundCardImage,
                         String themesname, String themesinfo, String themesauthor, String UUIDvar) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i = 0; i < metadataarray.size(); i++) {
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        }
        if (indexUUID.contains(UUIDvar) || UUIDvar == "default" || UUIDvar == "dark") {
            throw new osthmException("Theme with same UUID is exist!");
        } else {
            ArrayList<HashMap<String, Object>> themearray = new ArrayList<>();
            themearray.add((HashMap<String, Object>) new HashMap<String, Object>().put("colorPrimary", colorPrimary));
            themearray.get(0).put("colorPrimaryText", colorPrimaryText);
            themearray.get(0).put("colorPrimaryDark", colorPrimaryDark);
            themearray.get(0).put("statusbarIcon", statusbarIcon);
            themearray.get(0).put("colorBackground", colorBackground);
            themearray.get(0).put("colorBackgroundText", colorBackgroundText);
            themearray.get(0).put("colorButton", colorButton);
            themearray.get(0).put("colorButtonText", colorButtonText);
            themearray.get(0).put("shadow", shadow);
            themearray.get(0).put("colorRipple", colorRipple);
            themearray.get(0).put("colorHint", colorHint);
            themearray.get(0).put("colorPrimaryImage", colorPrimaryImage);
            themearray.get(0).put("colorBackgroundImage", colorBackgroundImage);
            themearray.get(0).put("colorPrimaryCard", colorPrimaryCard);
            themearray.get(0).put("colorBackgroundCard", colorBackgroundCard);
            themearray.get(0).put("colorPrimaryCardText", colorPrimaryCardText);
            themearray.get(0).put("colorBackgroundCardText", colorBackgroundCardText);
            themearray.get(0).put("colorPrimaryCardImage", colorPrimaryCardImage);
            themearray.get(0).put("colorBackgroundCardImage", colorBackgroundCardImage);
            themearray.get(0).put("version", Integer.toString(themesVersion));
            metadataarray.add((HashMap<String, Object>) new HashMap<String, Object>().put("themesname", themesname));
            metadataarray.get(0).put("themesjson", new Gson().toJson(themearray));
            metadataarray.get(0).put("themesinfo", themesinfo);
            metadataarray.get(0).put("themesauthor", themesauthor);
            metadataarray.get(0).put("os-thm-version", Integer.toString(metadataVersion));
            metadataarray.get(0).put("uuid", UUIDvar);
            data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
        }
    }

    public void editTheme(Context mContext, String UUIDvar, String colorPrimary, String colorPrimaryText, String colorPrimaryDark,
                         String statusbarIcon, String colorBackground, String colorBackgroundText,
                         String colorButton, String colorButtonText, String shadow, String colorRipple,
                         String colorHint, String colorPrimaryImage, String colorBackgroundImage,
                         String colorPrimaryCard, String colorBackgroundCard, String colorPrimaryCardText,
                         String colorBackgroundCardText, String colorPrimaryCardImage, String colorBackgroundCardImage,
                         String themesname, String themesinfo, String themesauthor) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = new Gson().fromJson(data.getString("themelists", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        for (int i = 0; i < metadataarray.size(); i++) {
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        }
        if (indexUUID.contains(UUIDvar)) {
            if (metadataarray.get(indexUUID.indexOf(UUIDvar)).get("os-thm-version") == Integer.toString(metadataVersion)) {
                ArrayList<HashMap<String, Object>> themearray = new ArrayList<>();
                themearray.add((HashMap<String, Object>) new HashMap<String, Object>().put("colorPrimary", colorPrimary));
                themearray.get(0).put("colorPrimaryText", colorPrimaryText);
                themearray.get(0).put("colorPrimaryDark", colorPrimaryDark);
                themearray.get(0).put("statusbarIcon", statusbarIcon);
                themearray.get(0).put("colorBackground", colorBackground);
                themearray.get(0).put("colorBackgroundText", colorBackgroundText);
                themearray.get(0).put("colorButton", colorButton);
                themearray.get(0).put("colorButtonText", colorButtonText);
                themearray.get(0).put("shadow", shadow);
                themearray.get(0).put("colorRipple", colorRipple);
                themearray.get(0).put("colorHint", colorHint);
                themearray.get(0).put("colorPrimaryImage", colorPrimaryImage);
                themearray.get(0).put("colorBackgroundImage", colorBackgroundImage);
                themearray.get(0).put("colorPrimaryCard", colorPrimaryCard);
                themearray.get(0).put("colorBackgroundCard", colorBackgroundCard);
                themearray.get(0).put("colorPrimaryCardText", colorPrimaryCardText);
                themearray.get(0).put("colorBackgroundCardText", colorBackgroundCardText);
                themearray.get(0).put("colorPrimaryCardImage", colorPrimaryCardImage);
                themearray.get(0).put("colorBackgroundCardImage", colorBackgroundCardImage);
                themearray.get(0).put("version", Integer.toString(themesVersion));
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesname", themesname);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesjson", new Gson().toJson(themearray));
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesinfo", themesinfo);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("themesauthor", themesauthor);
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("os-thm-version", Integer.toString(metadataVersion));
                metadataarray.get(indexUUID.indexOf(UUIDvar)).put("uuid", UUIDvar);
                data.edit().putString("themelists", new Gson().toJson(metadataarray)).apply();
            } else {
                throw new osthmException("Unsupported metadata version!");
            }
        } else {
            if (UUIDvar == "default" || UUIDvar == "dark")
                throw new osthmException("You can't edit a default theme!");
            else
                throw new osthmException("Theme with given UUID isn't exist!");
        }
    }

    public void setCurrentTheme(Context mContext, String UUIDvar) throws Exception {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();
        for (int i = 0; i < metadataarray.size(); i++) {
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        }
        if (indexUUID.contains(UUIDvar)) {
            if (metadataarray.get(indexUUID.indexOf(UUIDvar)).get("os-thm-version") == Integer.toString(metadataVersion)) {

                data.edit().putString("currentTheme", UUIDvar).apply();
            }
        } else {
            throw new osthmException("No matching theme with the given UUID!");
        }
    }

    public HashMap<String, Object> getCurrentTheme(Context mContext) {
        initializeData(mContext);
        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();
        for (int i = 0; i < metadataarray.size(); i++) {
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());
        }
        ArrayList<HashMap<String, Object>> arraythm = new Gson().fromJson(metadataarray.get(indexUUID.indexOf(data.getString("currentTheme", ""))).get("themesjson").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        return arraythm.get(0);
    }

    public String getCurrentThemeUUID(Context mContext) {
        initializeData(mContext);
        return data.getString("currentTheme", "");
    }

    public void importThemes(Context mContext, String json) {
        initializeData(mContext);
    }

    public String exportThemes(Context mContext) {
        initializeData(mContext);
        return null;
    }

    public void removeTheme(Context mContext, String UUIDvar) {
        initializeData(mContext);
    }
}
