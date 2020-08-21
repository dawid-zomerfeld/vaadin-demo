package com.nextbuy.demo.ui.views.treegrid;

import com.nextbuy.demo.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropEffect;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.grid.dnd.GridDropMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@PageTitle("TreeGrid | Vaadin CRM")
@Route(value = "treegrid", layout = MainLayout.class)
public class TreeGridView extends HorizontalLayout {


    public void setRowsDraggable (boolean rowsRraggable) {
        DragSource.create(this).setDraggable(false);
        getElement().setProperty("rowsDraggable", rowsRraggable);
    }

    public void setDropMode(GridDropMode dropMode) {
        DropTarget.create(this).setActive(false);
        getElement().setProperty("dropMode",
                dropMode == null ? null : dropMode.getClientName());
    }



    public TreeGridView() {
        TreeGrid<TreeItem> grid = new TreeGrid<>();
        grid.setRowsDraggable(true);

        DropTarget<TreeGrid> dropTarget = DropTarget.create(grid);

        dropTarget.addDropListener(event -> {
            // move the dragged component to inside the drop target component
            if (event.getDropEffect() == DropEffect.MOVE) {
                // the drag source is available only if the dragged component is from
                // the same UI as the drop target
//                event.getDragSourceComponent().ifPresent(box::add);

                event.getDragData().ifPresent(data -> {
                    // the server side drag data is available if it has been set and the
                    // component was dragged from the same UI as the drop target
                });
            }
        });

        TreeItem rootItem = new TreeItem("root");


        TreeItem parent1 = new TreeItem("1");
        TreeItem child11 = new TreeItem("1-1");
        TreeItem child12 = new TreeItem("1-2");

        List<TreeItem> children1 = parent1.getChildren();
        children1.add(child11);
        children1.add(child12);

        TreeItem child111 = new TreeItem("1-1-1");
        TreeItem child112 = new TreeItem("1-1-2");

        List<TreeItem> children4 = child11.getChildren();
        children4.add(child111);
        children4.add(child112);






        TreeItem parent2 = new TreeItem("2");
        TreeItem child21 = new TreeItem("2-1");
        TreeItem child22 = new TreeItem("2-2");



        List<TreeItem> children2 = parent2.getChildren();
        children2.add(child21);
        children2.add(child22);

        TreeItem child211 = new TreeItem("2-1-1");
        TreeItem child212 = new TreeItem("2-1-2");



        List<TreeItem> children3 = child21.getChildren();
        children3.add(child211);
        children3.add(child212);

        List<TreeItem> rootChildren = rootItem.getChildren();
        rootChildren.add(parent1);
        rootChildren.add(parent2);

        grid.addHierarchyColumn(treeItem -> treeItem.getName()).setHeader("TreeItem");


        LinkedList<TreeItem> rootElements = new LinkedList<>();
        rootElements.add(rootItem);



        grid.setItems(rootElements.stream(), new ValueProvider<TreeItem, Stream<TreeItem>>() {
            @Override
            public Stream<TreeItem> apply(TreeItem treeItem) {
                List<TreeItem> children = treeItem.getChildren();
                return children.stream();
            }
        });



        add(grid);

    }


}
