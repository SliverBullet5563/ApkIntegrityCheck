package com.yxcorp.gifshow;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.smile.gifmaker.Utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class ProxyApplication extends Application {
    private App app;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        boolean b = initFakeFiles(base);
        if (b){
            Log.d(Utils.TAG, "attachBaseContext: initFakeFiles success!");
            app = (App) makeApplication(App.class.getName());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (app != null)
            app.onCreate();
    }

    private boolean initFakeFiles(Context context) {
        return Utils.copyAssetAndWrite(Utils.fakeAPK, context);
    }

    public Application makeApplication(String appClassName){
        try {
            //获取ActivityThread全局
            Object currentActivityThread = getCurrentActivityThread();
            // hook LoadedApk
            Object loadedApkInfo = getLoadedApk(appClassName, currentActivityThread);
            //加载 application
            return makeApplication(loadedApkInfo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            t.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("makeApplication");
    }

    public Object getCurrentActivityThread() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取全局的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod =
                activityThreadClass.getDeclaredMethod("currentActivityThread");
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);
        return currentActivityThread;
    }

    public Object getLoadedApk(String appClassName,Object currentActivityThread) throws NoSuchFieldException, IllegalAccessException {
        //AppBindData
        Object mBoundApplication = getFieldValue(currentActivityThread,"mBoundApplication");
        //LoadedApk
        Object loadedApkInfo = getFieldValue(mBoundApplication, "info");
        //把当前进程的mApplication设置成null，否则调用makeApplication会直接返回mApplication
        setFieldValue(loadedApkInfo,"mApplication",null);

        //hook mAppDir
        String fakePath = new File(getFilesDir(), Utils.fakeAPK).getAbsolutePath();
        Log.d(Utils.TAG, "getLoadedApk fakePath: "+fakePath);
        setFieldValue(loadedApkInfo,"mAppDir",fakePath);

        //handleBindApplication的时候，调用LoadedApk中的makeApplication返回赋值给mInitialApplication
        Object oldApplicaiton = getFieldValue(currentActivityThread,"mInitialApplication");
        ArrayList<Application> mAllApplications = (ArrayList<Application>) getFieldValue(currentActivityThread,"mAllApplications");
        //删除oldApplicaiton
        mAllApplications.remove(oldApplicaiton);

        ApplicationInfo lodaedApk = (ApplicationInfo) getFieldValue(loadedApkInfo,"mApplicationInfo");
        ApplicationInfo appBindData = (ApplicationInfo) getFieldValue(mBoundApplication,"appInfo");
        //用于makeApplication中读取到的appClass名字
        lodaedApk.className = appClassName;
        appBindData.className = appClassName;
        lodaedApk.sourceDir = fakePath;
        appBindData.sourceDir = fakePath;

        return loadedApkInfo;

    }


    private static Application makeApplication(Object loadedApkInfo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (Application) loadedApkInfo.getClass().getMethod("makeApplication", boolean.class, Instrumentation.class)
                .invoke(loadedApkInfo,false,null);

    }

    private static void setFieldValue(Object className, String fieldName, Object value) throws IllegalAccessException, NoSuchFieldException {
        Field declaredField = className.getClass().getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        declaredField.set(className,value);
    }

    private static Object getFieldValue(Object className, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = className.getClass().getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        return declaredField.get(className);
    }
}
