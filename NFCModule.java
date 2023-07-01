package com.blit3;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.nio.charset.StandardCharsets;

public class NFCModule extends ReactContextBaseJavaModule {

    private NfcAdapter nfcAdapter;

    public NFCModule(ReactApplicationContext reactContext) {
        super(reactContext);
        nfcAdapter = NfcAdapter.getDefaultAdapter(reactContext);
    }

    @Override
    public String getName() {
        return "NFCModule";
    }

    @ReactMethod
    public void sendUrl(String url, final Promise promise) {
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            nfcAdapter.setNdefPushMessageCallback(new NfcAdapter.CreateNdefMessageCallback() {
                @Override
                public NdefMessage createNdefMessage(NfcEvent event) {
                    Log.d("Ndef", "Sending PTP: ");
                    byte[] urlBytes = url.getBytes(StandardCharsets.UTF_8);
                    NdefRecord uriRecord = NdefRecord.createUri(new String(urlBytes));
                    Log.d("Ndef", "Sending PTP: ");
                    return new NdefMessage(new NdefRecord[]{uriRecord});
                }
            }, getCurrentActivity());
            promise.resolve("URL sent via NFC peer-to-peer");
        } else {
            promise.reject("NFC_DISABLED", "NFC is disabled or not supported on this device");
        }
    }
}
