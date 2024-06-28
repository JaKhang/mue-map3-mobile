package com.mue.music.model_test_ui;

import com.mue.music.model_test_ui.abs.BaseSqliteModel;

public class Category extends BaseSqliteModel {
    private String description;
    private int colorId;

    public Category() {
    }

    public Category(long id, String name) {
        super(id, name);
    }

    public Category(long id, String name, int colorId) {
        super(id, name);
        this.colorId = colorId;
    }

    public Category(long id, String name, String description, int colorId) {
        super(id, name);
        this.description = description;
        this.colorId = colorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
