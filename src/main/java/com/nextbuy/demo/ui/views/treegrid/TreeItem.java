package com.nextbuy.demo.ui.views.treegrid;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreeItem {

    private String name;
    private List<TreeItem> children = new ArrayList<>();

    public TreeItem(String name) {
        this.name = name;
    }


}
