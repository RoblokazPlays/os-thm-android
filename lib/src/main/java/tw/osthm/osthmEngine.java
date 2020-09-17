/*
 * Copyright ThatCakeID 2020
 *
 * Contributors:
 *  - リェンーゆく (ryenyuku) <teamworks1732@gmail.com> 2017 - present
 *  - Iyxan23 <nurihsanalghifari@gmail.com> 2019 - present
 *
 * NOTE: You shouldn't modify any codes in this engine yourself
 * as it might will conflicts with other themes.
 */
package tw.osthm;

import android.graphics.Color;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * <h1>os-thm Engine</h1>
 * os-thm Engine is a theme engine library for
 * creating, using, and managing os-thm theme
 * module file format
 * <p>
 *
 * @author  ThatCakeID Team
 * @version 3.0
 * @since   2019
 */

public class osthmEngine {

    public static final int metadataVersion = 3;
    public static final String codename = "Cheese";
    private static ArrayList<HashMap<String, Object>> defaultThemes;

    /**
     * This method is used to initialize components used by the library
     */

    private static void initializeData() {
        defaultThemes = new ArrayList<>();
        defaultThemes.add(0, addKeyToHashMap("themesname", "Vanilla"));
        defaultThemes.get(0).put("themesjson", "{\"colorPrimary\":-14575885,\"colorBackgroundCardTint\":-16777216,\"colorPrimaryDark\":-15242838,\"colorBackgroundText\":-16777216,\"colorBackground\":-1,\"shadow\":1,\"colorPrimaryTint\":-1,\"colorHint\":-5723992,\"colorStatusbarTint\":1,\"colorPrimaryCardTint\":-16777216,\"colorAccent\":-720809,\"colorPrimaryText\":-1,\"colorBackgroundCardText\":-16777216,\"colorBackgroundTint\":-14575885,\"colorControlHighlight\":1073741824,\"colorAccentText\":-1,\"colorBackgroundCard\":-1,\"colorPrimaryCardText\":-16777216,\"colorPrimaryCard\":-1}");
        defaultThemes.get(0).put("themesinfo", "The default style theme of os-thm");
        defaultThemes.get(0).put("themesauthor", "リェンーゆく");
        defaultThemes.get(0).put("os-thm-version", metadataVersion);
        defaultThemes.get(0).put("uuid", "default");
        defaultThemes.get(0).put("theme-version", 2);
        defaultThemes.add(1, addKeyToHashMap("themesname", "Dark"));
        defaultThemes.get(1).put("themesjson", "{\"colorPrimary\":-14575885,\"colorBackgroundCardTint\":-6774616,\"colorPrimaryDark\":-14342875,\"colorBackgroundText\":-1,\"colorBackground\":-14342875,\"shadow\":1,\"colorPrimaryTint\":-1,\"colorHint\":-8355712,\"colorStatusbarTint\":1,\"colorPrimaryCardTint\":-6774616,\"colorAccent\":-720809,\"colorPrimaryText\":-1,\"colorBackgroundCardText\":-6774616,\"colorBackgroundTint\":-14575885,\"colorControlHighlight\":1090519039,\"colorAccentText\":-1,\"colorBackgroundCard\":-12566464,\"colorPrimaryCardText\":-6774616,\"colorPrimaryCard\":-12566464}");
        defaultThemes.get(1).put("themesinfo", "A Material dark theme for os-thm");
        defaultThemes.get(1).put("themesauthor", "thatcakepiece");
        defaultThemes.get(1).put("os-thm-version", metadataVersion);
        defaultThemes.get(1).put("uuid", "dark");
        defaultThemes.get(1).put("theme-version", 3);
    }

    /**
     * This method is used to get list of themes, private method
     * @return ListOfThemes
     */

    private static ArrayList<HashMap<String, Object>> getThemeListPrivate() {
        ArrayList<HashMap<String, Object>> metadataarray = osthmManager.getThemes();

        metadataarray.addAll(0, defaultThemes);

        return metadataarray;
    }

    /**
     * This method used to check if the requested theme UUID is exist in the defaultThemes entry
     * @param themeUUID Requested Theme UUID
     * @return Does theme exist in the default theme
     */
    private static boolean isExistInDefaultTheme(String themeUUID) {
        boolean isExist = false;

        for (HashMap<String, Object> theme: defaultThemes)
            if (theme.get("uuid").equals(themeUUID)) {
                isExist = true;
                break;
            }

        return isExist;
    }

    /**
     * This method is used to convert older version theme of os-thm from v2
     * into the current os-thm version
     * @param metadataarray Old Theme
     * @return Converted Theme (Usable Theme)
     */

    private static HashMap<String, Object> migrateOlderThemePrivate(HashMap<String, Object> metadataarray) {
        ArrayList<HashMap<String, Object>> oldTheme =
                new Gson().fromJson(metadataarray.get("themesjson").toString(),
                                   new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        HashMap<String, Integer> newShinyFancyTheme = new HashMap<>();

        newShinyFancyTheme.put("colorPrimary", Color.parseColor(oldTheme.get(0).get("colorPrimary").toString()));
        newShinyFancyTheme.put("colorPrimaryDark", Color.parseColor(oldTheme.get(0).get("colorPrimaryDark").toString()));
        newShinyFancyTheme.put("colorStatusbarTint", Integer.valueOf(oldTheme.get(0).get("statusbarIcon").toString()));
        newShinyFancyTheme.put("colorBackground", Color.parseColor(oldTheme.get(0).get("colorBackground").toString()));
        newShinyFancyTheme.put("colorAccent", Color.parseColor(oldTheme.get(0).get("colorButton").toString()));
        newShinyFancyTheme.put("shadow", Integer.valueOf(oldTheme.get(0).get("shadow").toString()));
        newShinyFancyTheme.put("colorControlHighlight", Color.parseColor(oldTheme.get(0).get("colorRipple").toString()));
        newShinyFancyTheme.put("colorHint", Color.parseColor(oldTheme.get(0).get("colorHint").toString()));
        newShinyFancyTheme.put("colorPrimaryTint", Color.parseColor(oldTheme.get(0).get("colorPrimaryImage").toString()));
        newShinyFancyTheme.put("colorBackgroundTint", Color.parseColor(oldTheme.get(0).get("colorBackgroundImage").toString()));
        newShinyFancyTheme.put("colorPrimaryCard", Color.parseColor(oldTheme.get(0).get("colorPrimaryCard").toString()));
        newShinyFancyTheme.put("colorBackgroundCard", Color.parseColor(oldTheme.get(0).get("colorBackgroundCard").toString()));
        newShinyFancyTheme.put("colorPrimaryCardText", Color.parseColor(oldTheme.get(0).get("colorPrimaryCardText").toString()));
        newShinyFancyTheme.put("colorBackgroundCardText", Color.parseColor(oldTheme.get(0).get("colorBackgroundCardText").toString()));
        newShinyFancyTheme.put("colorPrimaryCardTint", Color.parseColor(oldTheme.get(0).get("colorPrimaryCardImage").toString()));
        newShinyFancyTheme.put("colorBackgroundCardTint", Color.parseColor(oldTheme.get(0).get("colorBackgroundCardImage").toString()));
        newShinyFancyTheme.put("colorPrimaryText", Color.parseColor(oldTheme.get(0).get("colorPrimaryText").toString()));
        newShinyFancyTheme.put("colorBackgroundText", Color.parseColor(oldTheme.get(0).get("colorBackgroundText").toString()));
        newShinyFancyTheme.put("colorAccentText", Color.parseColor(oldTheme.get(0).get("colorButtonText").toString()));

        HashMap<String, Object> newmetadata = new HashMap<>();
        newmetadata.put("themesjson", new Gson().toJson(newShinyFancyTheme));
        newmetadata.put("os-thm-version", metadataVersion);
        newmetadata.put("uuid", UUID.randomUUID().toString());
        newmetadata.put("theme-version", 1);

        return newmetadata;
    }

    // Unfinished
    private static HashMap<String, Object> migrateOldThemePrivate(HashMap<String, Object> metadataarray) {
        return null;
    }

    /**
     * This method is used to get list of themes, public method
     * @return List Of Themes
     */

    public static ArrayList<HashMap<String, Object>> getThemeList() {
        initializeData();

        return getThemeListPrivate();
    }

    // Unfinished
    public static void migrateOldTheme(String UUIDvar) {
        // Migrate specified old theme to newer version
        initializeData();
    }

    // Unfinished
    public static void migrateAllOldThemes() {
        // Migrate all old themes to newer version
        initializeData();
    }


    /**
     * This method is used to add theme into the theme list using the OsThmTheme Object
     * with randomly generated UUID
     * @param themeData OsThmTheme object
     * @param themeName Theme name
     * @param themeInfo Theme info/ description
     * @param themeAuthor Theme Author
     * @param themeVersion Theme version
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(OsThmTheme themeData, String themeName, String themeInfo,
                                String themeAuthor,   int themeVersion) throws osthmException {
        // Add new theme using given OsThmTheme Object and generate a random UUID

        addTheme(themeData.colorPrimary, themeData.colorPrimaryText, themeData.colorPrimaryDark, themeData.colorStatusbarTint, themeData.colorBackground,
                themeData.colorBackgroundText, themeData.colorAccent, themeData.colorAccentText, themeData.shadow, themeData.colorControlHighlight, themeData.colorHint,
                themeData.colorPrimaryTint, themeData.colorBackgroundTint, themeData.colorPrimaryCard, themeData.colorBackgroundCard,
                themeData.colorPrimaryCardText, themeData.colorBackgroundCardText, themeData.colorPrimaryCardTint, themeData.colorBackgroundCardTint,
                themeName, themeInfo, themeAuthor, themeVersion, UUID.randomUUID().toString());
    }


    /**
     * This method is used to add theme into the theme list using the OsThmTheme Object
     * with defined UUID
     * @param themeData OsThmTheme object
     * @param themesname Theme name
     * @param themesinfo Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUID Theme UUID
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(OsThmTheme themeData, String themesname, String themesinfo,
                                String themesauthor,  int themeversion,  String UUID)
                                throws osthmException {
        // Add new theme using given OsThmTheme Object

        addTheme(themeData.colorPrimary, themeData.colorPrimaryText, themeData.colorPrimaryDark, themeData.colorStatusbarTint, themeData.colorBackground,
                themeData.colorBackgroundText, themeData.colorAccent, themeData.colorAccentText, themeData.shadow, themeData.colorControlHighlight, themeData.colorHint,
                themeData.colorPrimaryTint, themeData.colorBackgroundTint, themeData.colorPrimaryCard, themeData.colorBackgroundCard,
                themeData.colorPrimaryCardText, themeData.colorBackgroundCardText, themeData.colorPrimaryCardTint, themeData.colorBackgroundCardTint,
                themesname, themesinfo, themesauthor, themeversion, UUID);
    }

    /**
     * This method is used to add theme into the theme list, and generate a random uuid
     * @param colorPrimary Primary Color
     * @param colorPrimaryText Primary Text Color
     * @param colorPrimaryDark Primary Dark Color
     * @param colorStatusbarTint Statusbar Color
     * @param colorBackground Background color for root
     * @param colorBackgroundText Background color for text
     * @param colorAccent Color Accent
     * @param colorAccentText Color Accent for text
     * @param shadow Is shadow enabled
     * @param colorControlHighlight Color on highlight
     * @param colorHint Color Hint for EditText
     * @param colorPrimaryTint Imageview tint color
     * @param colorBackgroundTint Background Tint color
     * @param colorPrimaryCard Card Color
     * @param colorBackgroundCard Card Background Color
     * @param colorPrimaryCardText Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint Tint for imageview on card
     * @param colorBackgroundCardTint Background color for card tint
     * @param themesname Theme name
     * @param themesinfo Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(int colorPrimary,          int colorPrimaryText, int colorPrimaryDark,
                                int colorStatusbarTint,    int colorBackground,  int colorBackgroundText,
                                int colorAccent,           int colorAccentText,  int shadow,
                                int colorControlHighlight, int colorHint,        int colorPrimaryTint,
                                int colorBackgroundTint,   int colorPrimaryCard, int colorBackgroundCard,
                                int colorPrimaryCardText,  int colorBackgroundCardText,
                                int colorPrimaryCardTint,  int colorBackgroundCardTint,

                                String themesname,         String themesinfo,
                                String themesauthor,       int themeversion) throws osthmException {
        // Add new theme using given hex colors and generate new UUID

        addTheme(colorPrimary, colorPrimaryText, colorPrimaryDark, colorStatusbarTint, colorBackground,
                colorBackgroundText, colorAccent, colorAccentText, shadow, colorControlHighlight, colorHint,
                colorPrimaryTint, colorBackgroundTint, colorPrimaryCard, colorBackgroundCard,
                colorPrimaryCardText, colorBackgroundCardText, colorPrimaryCardTint, colorBackgroundCardTint,
                themesname, themesinfo, themesauthor, themeversion, UUID.randomUUID().toString());
    }

    /**
     * This method is used to add theme into the theme list
     * @param colorPrimary Primary Color
     * @param colorPrimaryText Primary Text Color
     * @param colorPrimaryDark Primary Dark Color
     * @param colorStatusbarTint Statusbar Color
     * @param colorBackground Background color for root
     * @param colorBackgroundText Background color for text
     * @param colorAccent Color Accent
     * @param colorAccentText Color Accent for text
     * @param shadow Is shadow enabled
     * @param colorControlHighlight Color on highlight
     * @param colorHint Color Hint for EditText
     * @param colorPrimaryTint Imageview tint color
     * @param colorBackgroundTint Background Tint color
     * @param colorPrimaryCard Card Color
     * @param colorBackgroundCard Card Background Color
     * @param colorPrimaryCardText Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint Tint for imageview on card
     * @param colorBackgroundCardTint Background color for card tint
     * @param themesname Theme name
     * @param themesinfo Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUIDvar UUID for the Theme
     * @throws osthmException Os-Thm Exception
     */

    public static void addTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                int colorAccent, int colorAccentText, int shadow, int colorControlHighlight,
                                int colorHint, int colorPrimaryTint, int colorBackgroundTint,
                                int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                                int colorBackgroundCardText, int colorPrimaryCardTint, int colorBackgroundCardTint,

                                String themesname, String themesinfo, String themesauthor,
                                int themeversion, String UUIDvar) throws osthmException {

        initializeData();

        if (osthmManager.containsTheme(UUIDvar) || isExistInDefaultTheme(UUIDvar))
            throw new osthmException("Theme with same UUID is exist!");
        else {
            HashMap<String, Integer> themearray = new HashMap<>();

            themearray.put("colorPrimary",                   colorPrimary            );
            themearray.put("colorPrimaryText",               colorPrimaryText        );
            themearray.put("colorPrimaryDark",               colorPrimaryDark        );
            themearray.put("colorStatusbarTint",             colorStatusbarTint      );
            themearray.put("colorBackground",                colorBackground         );
            themearray.put("colorBackgroundText",            colorBackgroundText     );
            themearray.put("colorAccent",                    colorAccent             );
            themearray.put("colorAccentText",                colorAccentText         );
            themearray.put("shadow",                         shadow                  );
            themearray.put("colorControlHighlight",          colorControlHighlight   );
            themearray.put("colorHint",                      colorHint               );
            themearray.put("colorPrimaryTint",               colorPrimaryTint        );
            themearray.put("colorBackgroundTint",            colorBackgroundTint     );
            themearray.put("colorPrimaryCard",               colorPrimaryCard        );
            themearray.put("colorBackgroundCard",            colorBackgroundCard     );
            themearray.put("colorPrimaryCardText",           colorPrimaryCardText    );
            themearray.put("colorBackgroundCardText",        colorBackgroundCardText );
            themearray.put("colorPrimaryCardTint",           colorPrimaryCardTint    );
            themearray.put("colorBackgroundCardTint",        colorBackgroundCardTint );

            HashMap<String, Object> metadataarray = new HashMap<>();

            metadataarray.put("themesname", themesname);

            metadataarray.put("themesjson",       new Gson().toJson(themearray));
            metadataarray.put("themesinfo",       themesinfo          );
            metadataarray.put("themesauthor",     themesauthor        );
            metadataarray.put("os-thm-version",   metadataVersion     );
            metadataarray.put("uuid",             UUIDvar             );
            metadataarray.put("theme-version",    themeversion        );

            osthmManager.setTheme(metadataarray);
        }
    }

    /**
     * This method is used to edit theme using OsThmTheme Object
     * @param themeData OsThmTheme Object
     * @param themesname Theme Name
     * @param themesinfo Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUIDvar Requested Theme UUID that you want to edit
     * @throws osthmException Os-Thm Exception
     */

    public static void editTheme(OsThmTheme themeData, String themesname, String themesinfo,
                                 String themesauthor,  int themeversion,  String UUIDvar)
                                 throws osthmException {
        editTheme(themeData.toHashMap(), themesname, themesinfo, themesauthor, themeversion, UUIDvar);
    }

    /**
     * This method is used to edit theme
     * @param colorPrimary Primary Color
     * @param colorPrimaryText Primary Text Color
     * @param colorPrimaryDark Primary Dark Color
     * @param colorStatusbarTint Statusbar Color
     * @param colorBackground Background color for root
     * @param colorBackgroundText Background color for text
     * @param colorAccent Color Accent
     * @param colorAccentText Color Accent for text
     * @param shadow Is shadow enabled
     * @param colorControlHighlight Color on highlight
     * @param colorHint Color Hint for EditText
     * @param colorPrimaryTint Imageview tint color
     * @param colorBackgroundTint Background Tint color
     * @param colorPrimaryCard Card Color
     * @param colorBackgroundCard Card Background Color
     * @param colorPrimaryCardText Color for Text on card
     * @param colorBackgroundCardText Color for Text Background on card
     * @param colorPrimaryCardTint Tint for imageview on card
     * @param colorBackgroundCardTint Background color for card tint
     * @param themesname Theme name
     * @param themesinfo Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUIDvar Requested Theme UUID that you want to edit
     * @throws osthmException Os-Thm Exception
     */

    public static void editTheme(int colorPrimary, int colorPrimaryText, int colorPrimaryDark,
                                 int colorStatusbarTint, int colorBackground, int colorBackgroundText,
                                 int colorAccent, int colorAccentText, int shadow, int colorControlHighlight,
                                 int colorHint, int colorPrimaryTint, int colorBackgroundTint,
                                 int colorPrimaryCard, int colorBackgroundCard, int colorPrimaryCardText,
                                 int colorBackgroundCardText, int colorPrimaryCardTint, int colorBackgroundCardTint,

                                 String themesname, String themesinfo, String themesauthor,
                                 int themeversion, String UUIDvar) throws osthmException {
        HashMap<String, Integer> themearray = new HashMap<>();

        themearray.put("colorPrimary",                   colorPrimary            );
        themearray.put("colorPrimaryText",               colorPrimaryText        );
        themearray.put("colorPrimaryDark",               colorPrimaryDark        );
        themearray.put("colorStatusbarTint",             colorStatusbarTint      );
        themearray.put("colorBackground",                colorBackground         );
        themearray.put("colorBackgroundText",            colorBackgroundText     );
        themearray.put("colorAccent",                    colorAccent             );
        themearray.put("colorAccentText",                colorAccentText         );
        themearray.put("shadow",                         shadow                  );
        themearray.put("colorControlHighlight",          colorControlHighlight   );
        themearray.put("colorHint",                      colorHint               );
        themearray.put("colorPrimaryTint",               colorPrimaryTint        );
        themearray.put("colorBackgroundTint",            colorBackgroundTint     );
        themearray.put("colorPrimaryCard",               colorPrimaryCard        );
        themearray.put("colorBackgroundCard",            colorBackgroundCard     );
        themearray.put("colorPrimaryCardText",           colorPrimaryCardText    );
        themearray.put("colorBackgroundCardText",        colorBackgroundCardText );
        themearray.put("colorPrimaryCardTint",           colorPrimaryCardTint    );
        themearray.put("colorBackgroundCardTint",        colorBackgroundCardTint );

        editTheme(themearray, themesname, themesinfo, themesauthor, themeversion, UUIDvar);
    }

    /**
     * This method is used to edit theme using HashMap
     * @param themeData Theme Data stored in HashMap
     * @param themesname Theme name
     * @param themesinfo Theme info/ description
     * @param themesauthor Theme Author
     * @param themeversion Theme version
     * @param UUIDvar Requested Theme UUID that you want to edit
     * @throws osthmException Os-Thm Exception
     */

    public static void editTheme(HashMap<String, Integer> themeData,
                                 String themesname, String themesinfo, String themesauthor,
                                 int themeversion, String UUIDvar) throws osthmException {
        initializeData();

        if (osthmManager.containsTheme(UUIDvar)) {
            HashMap<String, Object> metadataarray = new HashMap<>();
            metadataarray.put("themesname", new Gson().toJson(themesname));
            metadataarray.put("themesjson", themeData);
            metadataarray.put("themesinfo", themesinfo);
            metadataarray.put("themesauthor", themesauthor);
            metadataarray.put("os-thm-version", metadataVersion);
            metadataarray.put("uuid", UUIDvar);
            metadataarray.put("theme-version", themeversion);
            osthmManager.setTheme(metadataarray);
        } else {
            if (isExistInDefaultTheme(UUIDvar))
                throw new osthmException("You can't edit a default theme!");
            else
                throw new osthmException("Theme with given UUID doesn't exist!");
        }
    }

    /**
     * This method is used to apply current theme
     * using from the requested UUID
     * @param UUIDvar Theme UUID
     * @throws osthmException osThmException
     */

    public static void setCurrentTheme(String UUIDvar) throws osthmException {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        if (indexUUID.contains(UUIDvar)) {
            if ((int) metadataarray.get(indexUUID.indexOf(UUIDvar)).get("os-thm-version") == metadataVersion) {
                osthmManager.setConf("currentTheme", UUIDvar);
            } else
                throw new osthmException("Incompatible theme metadata version!");
        } else
            throw new osthmException("No matching theme with the given UUID!");
    }

    /**
     * This method is used to get the current theme
     * @return CurrentTheme
     */

    public static OsThmTheme getCurrentTheme() {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new OsThmTheme((HashMap<String, Integer>)
                new Gson().fromJson(metadataarray.get(indexUUID.indexOf(osthmManager
                                .getConf("currentTheme", "default")))
                                .get("themesjson").toString(),
                        new TypeToken<ArrayList<HashMap<String, Integer>>>() {}.getType()));
    }

    /**
     * This method is used to get the current theme as HashMap<String, Integer>
     * @return CurrentTheme
     */

    public static HashMap<String, Integer> getCurrentThemeAsHashMap() {
        initializeData();

        ArrayList<String> indexUUID = new ArrayList<>();
        ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

        for (int i = 0; i < metadataarray.size(); i++)
            indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

        return new Gson().fromJson(metadataarray.get(indexUUID.indexOf(osthmManager
                        .getConf("currentTheme", "default")))
                        .get("themesjson").toString(),
                new TypeToken<ArrayList<HashMap<String, Integer>>>() {}.getType());
    }

    /**
     * This method is used to get the current theme UUID
     * @return String of the current theme UUID
     */

    public static String getCurrentThemeUUID() {
        initializeData();

        return osthmManager.getConf("currentTheme", "default");
    }

    /**
     * This method is used to import
     * theme and save it
     * @param json Theme in string JSON
     * @throws osthmException osThmException
     */

    public static void importThemes(String json) throws osthmException {
        initializeData();

        ArrayList<HashMap<String, Object>> thmarray = new Gson().fromJson(
                json,
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());

        if (thmarray.size() > 0) {
            for (HashMap<String, Object> theme: thmarray) {
                if (theme.containsKey("os-thm-version")) {
                    if (theme.get("os-thm-version") instanceof Integer) {
                        if ((int) theme.get("os-thm-version") > metadataVersion) {
                            throw new osthmException("Sorry, this theme version is newer than the current theme engine can handle.");
                        } else {
                            if ((int) theme.get("os-thm-version") < metadataVersion) {
                                theme.putAll(migrateOldThemePrivate(theme));
                            }
                        }
                    } else {
                        theme.putAll(migrateOlderThemePrivate(theme));
                    }
                } else {
                    theme.putAll(migrateOlderThemePrivate(theme));
                }
                if (osthmManager.containsTheme(theme.get("uuid").toString()) || isExistInDefaultTheme(theme.get("uuid").toString())) {
                    throw new osthmException("Theme(s) can't be imported because the theme(s) are already exist!");
                } else {
                    osthmManager.setTheme(theme);
                }
            }
        } else {
            throw new osthmException("This JSON things is empty, what do you hope for? ._.");
        }
    }

    /**
     * This method is used to export themes as JSON string
     * @param UUIDvars List of themes UUID that you want to export
     * @return Exported theme as JSON format
     * @throws osthmException osthmException
     */

    public static String exportThemes(ArrayList<String> UUIDvars) throws osthmException {
        if (UUIDvars.size() > 0) {
            initializeData();

            ArrayList<String> indexUUID = new ArrayList<>();
            ArrayList<HashMap<String, Object>> metadataarray = getThemeListPrivate();

            for (int i = 0; i < metadataarray.size(); i++)
                indexUUID.add(metadataarray.get(indexUUID.size()).get("uuid").toString());

            ArrayList<HashMap<String, Object>> thmarray = new ArrayList<>();

            for (int i = 0; i < UUIDvars.size(); i++) {
                if (indexUUID.contains(UUIDvars.get(i))) {
                    thmarray.add(metadataarray.get(indexUUID.indexOf(UUIDvars.get(i))));
                } else
                    throw new osthmException("Theme(s) aren't exists!");
            }
            return new Gson().toJson(thmarray);
        } else {
            throw new osthmException("There is no UUID given, what do you hope for? ._.");
        }
    }

    /**
     * This method removes a theme specified by the given UUID
     * @param UUIDvar UUID of a theme that will be deleted
     * @throws osthmException osthmException
     */

    public static void removeTheme(String UUIDvar) throws osthmException {
        initializeData();

        if (osthmManager.containsTheme(UUIDvar)) {
            if (osthmManager.getConf("currentTheme", "default").equals(UUIDvar)) {
                throw new osthmException("Theme is in use!");
            }
            else {
                osthmManager.removeTheme(UUIDvar);
            }
        } else {
            if (isExistInDefaultTheme(UUIDvar))
                throw new osthmException("You can't delete a default theme!");
            else
                throw new osthmException("Theme with given UUID isn't exist!");
        }
    }

    /**
     * This method will clear all of your themes
     * @throws osthmException osthmException
     */

    public static void removeAllThemes() throws Exception {
        initializeData();

        if (isExistInDefaultTheme(osthmManager.getConf("currentTheme", "default")))
            osthmManager.clearThemes();
        else
            throw new osthmException("Theme is in use!");
    }

    // Utilites
    // =============================================================================================

    /**
     * This method returns a HashMap containing
     * the given key and object. Used as Util in
     * osthm
     * @param key Key
     * @param value Value
     * @return HashMap containing the given key and value
     */

    private static HashMap<String, Object> addKeyToHashMap(String key, Object value) {
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put(key, value);
        return hashmap;
    }

    /**
     * This method converts ARGB colors to HEX code
     * from given ARGB Integer value. Used as Util in
     * osthm
     * @param a Alpha color value
     * @param r Red color value
     * @param g Green color value
     * @param b Blue color value
     * @return String containing hex color code
     */

    public static String argbToHex(int a, int r, int g, int b) {
        if (a != 255) {
            return String.format("#%02x%02x%02x%02x", a, r, g, b).toUpperCase();
        } else {
            return String.format("#%02x%02x%02x", r, g, b).toUpperCase();
        }
    }

    /**
     * This method converts Integer color to HEX code
     * from a given Integer value. Used as Util in
     * osthm
     * @param color Integer color
     * @return String containing hex color code
     */

    public static String colorToHex(int color) {
        return argbToHex(Color.alpha(color),
                Color.red(color),
                Color.green(color),
                Color.blue(color));
    }
}