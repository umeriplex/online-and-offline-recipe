package com.example.mrcook.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity
public class FavList implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "cat")
    private String cat;

    @ColumnInfo(name = "loc")
    private String loc;

    @ColumnInfo(name = "ins")
    private String ins;

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "inglist")
    private List<String> ingList;

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "meslist")
    private List<String> mesList;

    @ColumnInfo(name = "link")
    private String link;

    @ColumnInfo(name = "pid")
    private int pid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public List<String> getIngList() {
        return ingList;
    }

    public void setIngList(List<String> ingList) {
        this.ingList = ingList;
    }

    public List<String> getMesList() {
        return mesList;
    }

    public void setMesList(List<String> mesList) {
        this.mesList = mesList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public FavList(int id, String image, String name, String cat, String loc, String ins, List<String> ingList, List<String> mesList, String link, int pid) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cat = cat;
        this.loc = loc;
        this.ins = ins;
        this.ingList = ingList;
        this.mesList = mesList;
        this.link = link;
        this.pid = pid;
    }
}
