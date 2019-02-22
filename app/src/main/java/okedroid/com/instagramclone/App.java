package okedroid.com.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("vgrZy3dhEZYmPkenqpglezGbGhtXPcPMdONRC61T")
                .clientKey("Bdzm37KPhmU3KWLXSugJhhCAvzdSHS3ILJPQplmA")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
