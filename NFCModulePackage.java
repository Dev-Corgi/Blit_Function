// MyNativeModulePackage.java

// 필요한 import문을 추가하세요.
package com.blit3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.ReactPackage;
import com.facebook.react.uimanager.ViewManager;


public class NFCModulePackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      List<NativeModule> modules = new ArrayList<>();
  
      modules.add(new NFCModule(reactContext));
  
      return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
      return Collections.emptyList();
    }
  
    // ...
  
  }
  