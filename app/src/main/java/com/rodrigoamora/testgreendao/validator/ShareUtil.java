package com.rodrigoamora.testgreendao.validator;

import android.content.Context;
import android.content.Intent;

public class ShareUtil {

    public static void directShare(Context context, String title) {
        String textShare = "APP Test-GreenDao\n"+
                "URL: https://github.com/RodrigoAmora/test-greendao";

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, textShare);
        context.startActivity(Intent.createChooser(sharingIntent, title));
    }

}
