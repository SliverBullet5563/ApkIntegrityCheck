package com.smile.gifmaker;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static final String TAG = "ApkIntegrityCheck";
    public static final String fakeAPK = "real_ks.apk";


    /** 将assets文件夹里文件，写入/data/data/包名/files/ 目录
     *
     * @param fileName 写入文件的文件名
     * @param context
     * @return
     **/
    public static boolean copyAssetAndWrite(String fileName, Context context) {
        try {
            File fileDir = context.getFilesDir();
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File outFile = new File(fileDir, fileName);
            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (!res) {
                    return false;
                }
            } else {
                if (outFile.length() > 10) {//表示已经写入一次
                    return true;
                }
            }
            InputStream is = context.getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
