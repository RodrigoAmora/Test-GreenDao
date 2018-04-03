package com.rodrigoamora.testgreendao.dao;

import android.content.Context;

import com.rodrigoamora.testgreendao.entity.DaoMaster;
import com.rodrigoamora.testgreendao.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DaoFactory {

    private static DaoSession daoSession;

    public static DaoSession createSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "test-greendao");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        return daoSession;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
