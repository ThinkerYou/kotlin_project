package com.kotlin.github_test.mvp_test.mvp_bean;

import java.util.HashMap;
import java.util.Map;

public class InputBean {
    public String title;
    public String body;

    public InputBean(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public static Map<String,InputBean> VALUES_MAP = new HashMap<>();
    static {
        VALUES_MAP.put("study",new InputBean("study","Studying for job!"));
    }
}
