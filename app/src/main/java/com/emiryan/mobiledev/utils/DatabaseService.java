package com.emiryan.mobiledev.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class DatabaseService extends Service {

    public DatabaseService() { }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceLocator.getInstance().setListStudents(DBparser.loadData(this));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        DBparser.saveData(this, ServiceLocator.getInstance().getListStudents());
        super.onDestroy();
    }
}