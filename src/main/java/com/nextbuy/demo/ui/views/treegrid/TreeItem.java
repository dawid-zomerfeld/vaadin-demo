package com.nextbuy.demo.ui.views.treegrid;

import com.vaadin.flow.component.Component;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreeItem{

    private String name;
    private TreeItem parent;
    private List<TreeItem> children = new ArrayList<>();

    public TreeItem(TreeItem parent, String name) {
        this.parent = parent;
        this.name = name;
    }


}
