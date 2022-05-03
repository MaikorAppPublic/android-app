package app.maikor.adapter;

import android.graphics.Bitmap;

public class VmInterface {
    static {
        System.loadLibrary("vm_interface_android");
    }

    private static native void render(Bitmap bitmap);
    public static native int getScreenWidth();
    public static native int getScreenHeight();

    public static void updateBitmap(Bitmap bitmap) {
        render(bitmap);
    }
}
