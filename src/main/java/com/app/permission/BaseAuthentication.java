package com.app.permission;

import com.app.utils.Constant;
import com.app.utils.DesEncrypter;

public class BaseAuthentication {
    private static final DesEncrypter desEncrypter = new DesEncrypter(Constant.WATCH_WORD);

    public static DesEncrypter getDesencrypter() {
        return desEncrypter;
    }
}
