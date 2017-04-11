package com.example.zc_ch.login;

/**
 * Created by ZOU Zijie on 2017/4/9.
 */

class FoodIconItem {

    private int restrictionType;
    private int type;
    private int nameId;
    private int iconId;

    public FoodIconItem() {

    }



    public int getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(int restrictionType) {
        this.restrictionType = restrictionType;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
