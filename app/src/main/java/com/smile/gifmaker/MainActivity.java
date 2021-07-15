package com.smile.gifmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kuaishou.android.security.mainplugin.JNICLibrary;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("c++_shared");
        System.loadLibrary("kwsgmain");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        String s = testKwsgmain();
        if (s!=null)
            tv.setText("调用成功："+s);

    }

    private String testKwsgmain() {
        Context context = getApplicationContext();
        Log.d(Utils.TAG, "testKwsgmain getPackageCodePath: "+context.getPackageCodePath());
        Log.d(Utils.TAG, "execByRuntime pm path : "+execByRuntime("pm path com.smile.gifmaker"));

        int i = 10412;
        Object[] objArr = new Object[7];
        objArr[0] = null;
        objArr[1] = "d7b7d042-d4f2-4012-be60-d97ff2429c17";
        objArr[2] = null;
        objArr[3] = null;
        objArr[4] = getApplicationContext();
        objArr[5] = null;
        objArr[6] = null;

        Object o = JNICLibrary.doCommandNative(i, objArr);
        Log.d(Utils.TAG,"doCommandNative 10412: "+o);

        Log.d(Utils.TAG, "testKwsgmain--------------------: ");


        String trim = "KUAISHOU16114694450842WlTtzVlLDa65gfeYTHd2SW4De3sCa0tUZVFa2%2FK29HoFXrVRIlF6VENYENOiffuRxlAiXNtBYHcO%0AIR34YcJ6iVPibTrn0opfYiCOVYme3nP6iYCy5Ag0X8UswMZZ6h3yLA5Y55mk6vTYnMcJ4YWmVPBC%0AHipJYMnP%2FbWj5XICIicG4n4FaPyQAn0Ez%2F3xga7tNjQA0eXMab5Q4N4xqUjrr353ueKHU%2BdtmUbX%0AAPNnCjlITTv9prvEr%2BwDrqwTd0%2Bu7N6BQ8EnMwoK%2FmMEV%2BngZHu7ZrmxA7YtcUwfkz%2F5%2F3Ky9Vcd%0AMJAWjBynkRjXmuSpPcn0Hk1ioYX9rbF5%2FKOHkECxGWofhhLJrAAmlS3g3yyuE2TSKowXub45F2m1%0AWyvksHA5EnlmdaCynpSKOwTRZbuljxWmuf%2Bop0nt%2Fe1ilwr0e5NxpL%2B5lAWWgtlxdKAcRn%2BUypI8%0APXOddQ44lpVSSkaXsZL9YM0TGldez7Ti6FGnkfY8I5VyHPJcXOgQIIPLxnCN11fT%2BDGZAv6e4dGO%0AJm3kV7uyctVCAgTRszC4Q5Om8SHlm8XBEkbe51r%2FDeJGxLB0jZ8OajfZR%2BalNPV1iLCU3GBk1GuX%0AENbOLA98KTX2Pnnpa4%2Fj5trDKexKFxZlgrzOcNHTD2V1jlM5YeDl%2B4oHDJt4CB4g%2B7VTsu3mOJ0m%0A1tjcluauQpPV1hDtMKvT3pQVMhvHMBnrzISniCk18ACqGEuFhUSX%2FWzkKQSEls1O9sN0DuC0HlTA%0ANiFrZhqWx1vp0D%2BFHGyMNWe7i6YVEuFakQRmGl7pH6zVQUq0wXl45dPGm1I4SxIZhfZMdrEb1vN4%0AT5pLOHn1gZYVSjaPtfZjNO48myAB2O6SOg3PAgWC6YFi3ibaBCk7kmh8VkaA2VVeiuktzAmV%2Fob0%0Awg27N2vmD1%2FHoTRJd0ZOzDw2yvDbLYF%2BUGdyZFxO8Sm5jXql2%2Bn3zyR78QHwW9XGDF9n5m4mv3TB%0AFNIENnOHbMSTiMrFvEaoNDLUSUL4SbYL16tAp3eHth%2FvLzjxXRTEOwfCL2Z6xR7oAXjOfekxyqej%0ATnRMsBvBeQamo3X1AV5jPRyDlFvSYdGb2%2FqIgA4nNW6xYxb8rIIkdVCPj0Rm0bbNKHnny5FknTMO%0AjiACxgkSNtEYt0dfxttHS5%2Fzh29kkrp03TK7DDx0M5tTXoFTn7eWWBqS2Cd0%2Bw%2FwLFanRvzPSLTc%0A5sz3O5EpnP3FPFP8jTRkf6EBdu3c8zNmlshBkVgctitTasB4X5xpv%2BHALFUaBZPTkZmj4az3PgdD%0AZCLIRH1ElxUuMldElZArBuq3vsTbRxNWYHrag51dm2vyxchLGSs9rTwjW%2BI6MV13z1NgbHw%2FeK%2Bb%0AMcLo1mF2BxKxdVZuDRv2G9hb9dmz%2F45SG91uR4Jy8GN3EKv0u81Pa76rugeTmx8sSHfesRSRXfcL%0AGl50s2VCbghuxwTzZx535kQUJuauSDo3BY9mVdUsODLBto0xC4Z32eHMpocPywIoyPW45IQI98yV%0AUtRX4WLWAxagpuYauouTQMCa%2BXkHnnehSdrcCKiRzko6wp%2FT7rBr1P05OOjSwZ8mVPClIcBClr%2B4%0ASkjb6N2WLDdy%2Bz%2BiirE8hDpTmWGwA3kdYDTPHrgQaPw38X9lmD1vWO%2Fv1cva9gECXXXMz7Fs7JNq%0A09SqS2i51aTHvYaTLNkUuen3MKnK78cW0Ap1W%2Bsxec58lEhgCFA6YGQHqWAGjNl6bMrFkrejRqtL%0AJTy14g%2BELlm2kFyo5dl%2BG1zvzBTvxnOzFMWiMyoyS9Apo1RzYDMiaAaJHcxmK4VtdgtIZYeEZyX0%0AI0ATOY2Ly5BEqroPisuzAR31PstK9h8d58k2dVGIcLVVSNA1sFJY1QwrWm9jtSGkVeF1GKmLiIqv%0AwX96vKJMQpP1Z7fGKcK0Q8K%2FvNterH9NXv8lsvp%2BpHFp7f5DCDesO21U3aPQ%2Bxelx8aGnZ6onzRh%0AvPb46353PixYk09vqNOGf%2FbgXhXeJGD7ko8nFEutuXQrTY1k3vQxLBauS14yEeqHX2VKc2QizA20%0AgFfwv8j9s%2Fh2rC284f1djz6vLkHiZxGdgCoaB6rY3MR4JF0sprVUrV0FX8YbHoU%2BzBUGECQqceIv%0AKc5RatzE7KyV0vfe334c3respgHdgtRAgZ9L7xVtjA4R4dhXoqpkGmCF1VekHmmpJ7AEz27xmK3z%0AytJBb7xRr5HTr2IIWmB%2Fdzog3P7AFS%2Biopx04SiWTnJIg%2FZEKrMrTdtpE9qz%2Fvwjm4rYnC3t%2FC9A%0AlofadWzFCBCN8%2BSE1S4hN%2FGL7NOh0GDwKmLFxuMXGWdrpi9Rd1PY%2FsMBroYkS6sG273v2k09EAMM%0AQNa%2BxBLnyOXwwu55kMsS49b97awEX65vakqbad66EZF4a9oos9ttH8M4x811YcBhberIJFoeRBaN%0AkHLLFnPgk%2FM%2Fl6o4iLgy%2B1hdw%2FrrzEeKKPxF3XvLAw%2FrC6dz3HAbISKM6XBk%2FJ1FiYfP1zsGL7XS%0AQn46kVlOfsTMz68fa7wRruAOXfwtn%2BLugDi2ridxAbshNSi4JXXjkvOkBLKIG2ZlNCtvOfRSVpQx%0AbR41wQBlNc1kShUeSf6lEyYNVKsDEQs5VZL3%2BOF5bT7v%2BFYk4c9X35enmWTK9S1zgFDY13neCtvq%0APm7qO2nydHRoN9Y%2BVGl7Tni1Kew%2BkSFuCDojjqu3hb9d5Qhwz5mKWeIhrNBNDGhE%2B%2F2gdPbATE3S%0Ak2YiUFXFXaza0V9DIBfF3R9l5oJ1UvecFh5sMnIXevUFqNNWoTopuA9A8bUq0KPJm3%2BQADTaJSOp%0AUZAi1mkjJOoo7DPtjrd9g570y%2BFQ3jhf9rNo6fN0hSitgv%2FvxY4TBETj8CWuOZmJMO%2F8IXwCdNzu%0AZ%2BP0JEgEjSMm3K1UZHZBRx6Ff5AW3xae3CqIfptAlQObUA8gBOTr376vL8fNnTaMi1N3SRLx0piE%0AuVoNokWvtSr8kwwIvu6QbEuj7dgmdRFPhI%2BHlY57KvwCzMNJRVohOY4xXoUlqq4ghCuXtdRzb6eI%0AU%2BhVMzVDvuDHLdYJyNKkEBM6q73HBdIa0Pl0dvNNa8v7si8pYQW0U3807d1bQMFQ3D%2BEZCV4NOzV%0AW4UabwJNTBs6iE2BvBznUN%2FLztBfyeQ%3D%0A";
        int i2 = 10418;
        Object[] objArr2 = new Object[8];
        objArr2[0] = new String[]{trim};
        objArr2[1] = "d7b7d042-d4f2-4012-be60-d97ff2429c17";
        objArr2[2] = -1;
        objArr2[3] = false;
        objArr2[4] = getApplicationContext();
        objArr2[5] = null;
        objArr2[6] = true;
        objArr2[7] = "7e46b28a-8c93-4940-8238-4c60e64e3c81";

        Object o2 = JNICLibrary.doCommandNative(i2, objArr2);
        Log.d(Utils.TAG,"doCommandNative 10418: "+o2);

        return (String) o2;
    }

    public static String execByRuntime(String cmd) {
        Process process = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            inputStreamReader = new InputStreamReader(process.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            int read;
            char[] buffer = new char[4096];
            StringBuilder output = new StringBuilder();
            while ((read = bufferedReader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (Throwable t) {

                }
            }
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (Throwable t) {

                }
            }
            if (null != process) {
                try {
                    process.destroy();
                } catch (Throwable t) {

                }
            }
        }
    }

}