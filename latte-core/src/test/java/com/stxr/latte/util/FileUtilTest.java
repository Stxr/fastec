package com.stxr.latte.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by stxr on 2018/7/25.
 */
public class FileUtilTest {
    @Test
    public void getExtension() throws Exception {
        assertEquals(FileUtil.getExtension("adfa.jpg"),"jpg");
    }

}