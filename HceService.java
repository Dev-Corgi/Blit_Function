// HceService.java
package com.blit3;
import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;
import android.nfc.cardemulation.CardEmulation;
import java.nio.charset.StandardCharsets;
import android.widget.Toast;
import android.os.Handler;

public class HceService extends HostApduService {
    private static final String TAG = "HceService";
    private static final String URL = "https://www.example.com"; // 에뮬레이션할 URL
    private static final String URL_DATA = "https://www.example.com"; // 변경 가능한 URL 데이터
    private Handler mHandler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Start APDU: ");
        // UI thread 핸들 얻음
        Bundle extras = intent.getExtras();

        // 서비스를 앱 종료시까지 계속 실행상태로
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Create APDU: ");
    }

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        Log.d(TAG, "APDU Received!: ");
        
        // Log.d(TAG, "Received APDU: " + ByteArrayToHexString(commandApdu));
        
        // if (ByteArrayToHexString(commandApdu).equalsIgnoreCase("00A404000E315041592E5359532E4444463031")) {
        //     // SELECT APDU command for the AID (Application Identifier) of the HCE service
        //     return HexStringToByteArray("9000");
        // } else if (ByteArrayToHexString(commandApdu).equalsIgnoreCase("00B0000000")) {
        //     // READ BINARY APDU command
        //     return HexStringToByteArray("D1" + String.format("%02X", URL.length()) + ByteArrayToHexString(URL.getBytes()));
        // } else {
        //     return HexStringToByteArray("6D00"); // Unknown command
        // }
        return URL_DATA.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "APDU Deactivated: " + reason);
    }

    // private String ByteArrayToHexString(byte[] bytes) {
    //     StringBuilder sb = new StringBuilder(bytes.length * 2);
    //     for (byte b : bytes) {
    //         sb.append(String.format("%02X", b));
    //     }
    //     return sb.toString();
    // }

    // private byte[] HexStringToByteArray(String s) {
    //     int len = s.length();
    //     byte[] data = new byte[len / 2];
    //     for (int i = 0; i < len; i += 2) {
    //         data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
    //     }
    //     return data;
    // }
}
