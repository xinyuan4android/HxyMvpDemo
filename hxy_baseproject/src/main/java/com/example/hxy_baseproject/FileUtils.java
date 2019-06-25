package com.example.hxy_baseproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.hxy_baseproject.utils.UIUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hxy on  2018/10/12.
 */
public class FileUtils {
    private static String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static String filePath = "";
    private final static String fileFolder = "ocr";
    private final static String OtherFileFolder = "zzh";
    private final static String ADFileFolder = "ad";
    private final static String LivenessFolder = "liveness";
    private static String ocrFilePath;
    private static String otherFilePath;
    private static String adFilePath;
    private static String livenessFilePath;

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;

    public static String getOcrFilePath() {
        return ocrFilePath;
    }

    private static Context context;

    public static void init(Context context) {
        FileUtils.context = context;
    }

    public static int saveFaveInfoToSdCard(byte[] bytes, String userId) {
        if (initOCRPath()) {
            String fileName = getFaceInfoFileName(userId);
            return writeToSDCard(bytes, fileName);
        }
        return FAIL;
    }

    private static void createOCRPath() {
        String packageName = context.getPackageName();
        filePath = rootPath + File.separator + packageName;
        ocrFilePath = filePath + File.separator + fileFolder;
        otherFilePath = filePath + File.separator + OtherFileFolder;
        adFilePath = filePath + File.separator + ADFileFolder;
        livenessFilePath = filePath + File.separator + LivenessFolder;

//        otherFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        File file = new File(ocrFilePath);
        File adFile = new File(adFilePath);
        File otherFile = new File(otherFilePath);
        File livenessFile = new File(livenessFilePath);
        file.mkdirs();
        livenessFile.mkdirs();
        otherFile.mkdirs();
    }

    public static boolean initOCRPath() {
        if (ocrFilePath == null) {
            createOCRPath();
        }
        File file = new File(ocrFilePath);
        File otherFile = new File(otherFilePath);
        if (file.exists() && otherFile.exists()) {
            return true;
        }
        return file.mkdirs() && otherFile.mkdirs();
//        if (!file.exists()) {
//            return file.mkdirs();
//        } else {
//            return true;
//        }
    }

    private static String getLivenessFileName(String userId) {
        return "liveness_" + userId + ".jpg";
    }

    private static String getIdCardHeadImageFileName(String userId) {
        return "IdCard_HeadImage_" + userId + ".txt";
    }


    private static String getFaceInfoFileName(String userId) {
        return "FaceInfo_" + userId + ".txt";
    }

    public static int saveTempImageToSdCard(byte[] bytes, String fileName) {
        if (initOCRPath()) {
            return writeToSDCard(bytes, fileName);
        }
        return FAIL;
    }

    public static String getOtherImagePath() {
        return otherFilePath;
    }

    public static int saveOtherImageToSdCard(byte[] bytes, String fileName) {
        if (initOCRPath()) {
            return writeToSDCard(bytes, otherFilePath, fileName);
        }
        return FAIL;
    }

    public static String getAdFilePath() {
        return adFilePath;
    }

    public static int saveLivenessImageToSdCard(byte[] bytes, String userId) {
        if (initOCRPath()) {
            String fileName = getLivenessFileName(userId);
            return writeLivenessToSDCard(bytes, fileName);
        }
        return FAIL;
    }

    public static int saveIdCardHeadImageToSdCard(byte[] bytes, String userId) {
        if (initOCRPath()) {
            String fileName = getIdCardHeadImageFileName(userId);
            return writeToSDCard(bytes, fileName);
        }
        return FAIL;
    }

    public static byte[] getFaceInfo(String userId) {
        if (initOCRPath()) {

            File file = new File(ocrFilePath + File.separator + getFaceInfoFileName(userId));
            if (file.exists()) {
                return getDataFromSDCard(file.getAbsolutePath());
            } else {
                return null;
            }
        }
        return null;
    }

    public static String getLivenessImagePath(String userId) {
        if (initOCRPath()) {
            return livenessFilePath + File.separator + getLivenessFileName(userId);
        }
        return null;
    }

    public static String getIdCardHeadImagePath(String userId) {
        if (initOCRPath()) {
            return ocrFilePath + File.separator + getIdCardHeadImageFileName(userId);
        }
        return null;
    }

    public static String getFaceInfoPath(String userId) {
        if (initOCRPath()) {
            return ocrFilePath + File.separator + getFaceInfoFileName(userId);
        }
        return null;
    }

    public static byte[] getIdCardHeadImage(String userId) {
        if (initOCRPath()) {
            File file = new File(ocrFilePath + File.separator + getIdCardHeadImageFileName(userId));
            if (file.exists()) {
                return getDataFromSDCard(file.getAbsolutePath());
            } else {
                return null;
            }
        }
        return null;
    }

    private static int writeToSDCard(byte[] bytes, String path, String fileName) {

        FileOutputStream fos = null;
        String filePath = path + File.separator + fileName;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(bytes);
            fos.flush();
            Log.e("write", fileName + "写入成功");
            return SUCCESS;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("write", fileName + "写入失败");
            deleteFile(filePath);
            return FAIL;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("write", fileName + "写入失败");
            deleteFile(filePath);
            return FAIL;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int writeToSDCard(byte[] bytes, String fileName) {
        return writeToSDCard(bytes, ocrFilePath, fileName);
    }

    private static int writeLivenessToSDCard(byte[] bytes, String fileName) {
        return writeToSDCard(bytes, livenessFilePath, fileName);
    }

    private static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    private static byte[] getDataFromSDCard(String filePath) {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        try {
            fis = new FileInputStream(filePath);
            baos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void saveImageToSDCard(final Bitmap bitmap, final String saveFileName, final boolean isRecyclerBitmap) {
        AppExecutor.getInstance().getDisIO().execute(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bitmap.getByteCount());
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                if (isRecyclerBitmap) {
                    bitmap.recycle();
                }
                byte[] bytes = outputStream.toByteArray();
                int i = FileUtils.saveOtherImageToSdCard(bytes, saveFileName + ".jpg");
                if (FileUtils.FAIL == i) {
                    //保存失败
                    UIUtils.showToastSafeLong("文件保存失败");
                } else if (FileUtils.SUCCESS == i) {
                    //保存成功
                    File file = new File(FileUtils.getOtherImagePath() + File.separator + saveFileName + ".jpg");
                    saveToGallery(file, context);
                }

            }
        });
    }


    /**
     * 把图片或者视频文件 插入到系统图库
     *
     * @param file
     * @param context
     */
    private static void saveToGallery(File file, Context context) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
            UIUtils.showToastSafeLong("保存成功" + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            UIUtils.showToastSafeLong("保存图库失败");
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));

    }
}
