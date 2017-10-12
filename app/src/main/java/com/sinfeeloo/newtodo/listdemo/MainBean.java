package com.sinfeeloo.newtodo.listdemo;

/**
 * @author: mhj
 * @date: 2017/10/9 17:14
 * @desc:
 */

public class MainBean {
    private String id;
    private String name;
    private String content;
    private boolean isChecked = false;

    public MainBean(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
