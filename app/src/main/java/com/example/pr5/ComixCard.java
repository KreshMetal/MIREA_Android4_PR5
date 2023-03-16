package com.example.pr5;

import java.io.Serializable;

public class ComixCard implements Serializable
{
    private String name;
    private String engName;
    private String desc;
    private int logo;

    public ComixCard(String name, String desc, int logo, String engName)
    {
        this.name = name;
        this.desc = desc;
        this.logo = logo;
        this.engName = engName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public int getLogo()
    {
        return logo;
    }

    public void setLogo(int logo)
    {
        this.logo = logo;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }
}
