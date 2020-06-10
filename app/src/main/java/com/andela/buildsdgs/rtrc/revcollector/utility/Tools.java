package com.andela.buildsdgs.rtrc.revcollector.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.widget.NestedScrollView;

import com.andela.buildsdgs.rtrc.revcollector.R;
import com.andela.buildsdgs.rtrc.revcollector.models.User;
import com.andela.buildsdgs.rtrc.revcollector.models.UserDetail;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Tools {

    private SharedPreferences preferences;
    public Tools(Context context) {
        //this.context = context;
        preferences = context.getSharedPreferences("com.andela.buildsdgs.rtrc.PREFERENCES", 0);
    }

    public static void setSystemBarColor(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public  boolean saveUserProfile(UserDetail userDetail){
        try {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name",userDetail.getUser().getName());
            editor.putString("pk",userDetail.getUser().getPk());
            editor.putString("date_joined",userDetail.getUser().getDateJoined());
            editor.putString("is_active",userDetail.getUser().getIsActive());
            editor.putString("email",userDetail.getUser().getEmail());
            editor.putString("username",userDetail.getUser().getUsername());
            editor.putString("phone",userDetail.getUser().getPhone());
            editor.putString("is_active",userDetail.getUser().getIsActive());
            editor.putString("is_staff",userDetail.getUser().getIsStaff());
            editor.putString("is_collector",userDetail.getUser().getIsCollector());
            editor.putString("is_user",userDetail.getUser().getIsUser());
            editor.putString("token",userDetail.getToken());

            editor.apply();
            return true;
        }catch (Exception e){
            System.out.println("Shared Prefs Exception : " + e.toString());
        }
        return false;
    }

    public UserDetail retrieveUserProfile(){
        UserDetail userDetail = new UserDetail();
        User user = new User();
        user.setDateJoined(preferences.getString("date_joined",null));
        user.setEmail(preferences.getString("email",null));
        user.setUsername(preferences.getString("username",null));
        user.setIsActive(preferences.getString("is_active",null));
        user.setIsCollector(preferences.getString("is_collector",null));
        user.setIsStaff(preferences.getString("is_staff",null));
        user.setIsUser(preferences.getString("is_user",null));
        user.setName(preferences.getString("name",null));
        user.setPk(preferences.getString("pk",null));
        userDetail.setToken(preferences.getString("token",null));
        userDetail.setUser(user);
        return userDetail;
    }


    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(new Runnable() {
            @Override
            public void run() {
                nested.scrollTo(500, targetView.getBottom());
            }
        });
    }

    public static String getFormattedDateShort(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM dd, yyyy");
        return newFormat.format(new Date(dateTime));
    }

    public static String getFormattedDateSimple(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return newFormat.format(new Date(dateTime));
    }

    public static String getFormattedDateEvent(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("EEE, MMM dd yyyy");
        return newFormat.format(new Date(dateTime));
    }

    public static String getFormattedTimeEvent(Long time) {
        SimpleDateFormat newFormat = new SimpleDateFormat("h:mm a");
        return newFormat.format(new Date(time));
    }

    public static String getEmailFromName(String name) {
        if (name != null && !name.equals("")) {
            String email = name.replaceAll(" ", ".").toLowerCase().concat("@mail.com");
            return email;
        }
        return name;
    }

}
