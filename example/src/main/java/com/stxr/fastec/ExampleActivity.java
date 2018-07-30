package com.stxr.fastec;


import com.stxr.latte.activities.ProxyActivity;
import com.stxr.latte.delegates.LatteDelegate;
import com.stxr.latte.ec.launcher.LauncherDelegate;
import com.stxr.latte.ec.launcher.LauncherScrollerDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollerDelegate();
    }

}
