package com.stxr.fastec;


import com.stxr.latte.activities.ProxyActivity;
import com.stxr.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
