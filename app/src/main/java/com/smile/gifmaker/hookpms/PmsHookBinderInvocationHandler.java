package com.smile.gifmaker.hookpms;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jiangwei1-g on 2016/9/7.
 */
public class PmsHookBinderInvocationHandler implements InvocationHandler {

    private Object base;
    
    //应用正确的签名信息
    private String SIGN;
    private String appPkgName = "";

    public PmsHookBinderInvocationHandler(Object base, String sign, String appPkgName, int hashCode) {
        try {
            this.base = base;
            this.SIGN = sign;
            this.appPkgName = appPkgName;
        } catch (Exception e) {
            Log.d("jw", "error:"+ Log.getStackTraceString(e));
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	Log.i("jw invoke ", method.getName());
        if("getPackageInfo".equals(method.getName())){
        	String pkgName = (String)args[0];
            Integer flag = (Integer)args[1];
            Log.i("jw", "invoke flag: "+flag);
            Log.i("jw", "invoke pkgName: "+pkgName);
            if(flag == PackageManager.GET_SIGNATURES && appPkgName.equals(pkgName)){
                Log.i("jw", "getPackageInfo versionName: ");
            	Signature sign = new Signature(SIGN);
                Log.i("jw", "getPackageInfo versionName: 1111 "+sign.toCharsString());
                Log.i("jw", "getPackageInfo versionName: 1111 "+SIGN);
            	PackageInfo info = (PackageInfo) method.invoke(base, args);
//            	info.signatures = new Signature[]{sign};
                info.signatures[0] = sign;
                Log.i("jw", "getPackageInfo versionName: "+info.versionName);
                Log.i("jw", "getPackageInfo packageName: "+info.packageName);
//                Log.i("jw", "getPackageInfo packageName: "+info.);
                Log.i("jw", "getPackageInfo signatures[0]: "+info.signatures[0].toCharsString()
                        +"getPackageInfo signatures length:"+info.signatures.length);
                return info;
            }
        }
        return method.invoke(base, args);
    }

}
