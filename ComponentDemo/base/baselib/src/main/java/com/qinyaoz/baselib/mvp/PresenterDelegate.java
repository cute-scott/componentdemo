package com.qinyaoz.baselib.mvp;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by haoyuew on 16/3/30.
 */
public interface PresenterDelegate {

    void onCreate(Intent mIntent);

    void onCreate(Bundle bundle);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
