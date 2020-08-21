package com.nextbuy.demo.ui.views.treegrid;

import com.nextbuy.demo.ui.MainLayout;
import com.vaadin.flow.component.grid.dnd.GridDropLocation;
import com.vaadin.flow.component.grid.dnd.GridDropMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@PageTitle("TreeGrid | Vaadin CRM")
@Route(value = "treegrid", layout = MainLayout.class)
public class TreeGridView extends HorizontalLayout {


//    public void setRowsDraggable (boolean rowsRraggable) {
//        DragSource.create(this).setDraggable(false);
//        getElement().setProperty("rowsDraggable", rowsRraggable);
//    }
//
//
//    public Registration addDropListener(
//            ComponentEventListener<GridDropEvent<TreeGrid>> listener) {
//        return addListener(GridDropEvent.class,
//                (ComponentEventListener) listener);
//    }
//
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public Registration addDragStartListener(
//            ComponentEventListener<GridDragStartEvent<TreeGrid>> listener) {
//        return addListener(GridDragStartEvent.class,
//                (ComponentEventListener) listener);
//    }
//
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public Registration addDragEndListener(
//            ComponentEventListener<GridDragEndEvent<TreeGrid>> listener) {
//        return addListener(GridDragEndEvent.class,
//                (ComponentEventListener) listener);
//    }


    public TreeGridView() {
        TreeItem rootItem = getTreeStructure();

        // Rysowanie
        TreeGrid<TreeItem> grid = new TreeGrid<>();
        grid.setRowsDraggable(true);
        grid.addHierarchyColumn(treeItem -> treeItem.getName()).setHeader("TreeItem");


//        TreeData<TreeItem> objectTreeData = new TreeData<>();
//        TreeDataProvider<TreeItem> treeItemTreeDataProvider = new TreeDataProvider<TreeItem>(new TreeData<>());


        grid.setItems(List.of(rootItem).stream(), (ValueProvider<TreeItem, Stream<TreeItem>>) treeItem -> treeItem.getChildren().stream());
        add(grid);

        // Logika Drag and drop
        AtomicReference<TreeItem> draggedItem = new AtomicReference<>();

        grid.addDragStartListener(event -> {
            draggedItem.set(event.getDraggedItems().get(0));
            grid.setDropMode(GridDropMode.ON_TOP_OR_BETWEEN);
        });
        grid.addDropListener(event -> {

            TreeItem sourceItem = draggedItem.get();
            TreeItem targetItem = event.getDropTargetItem().get();
            GridDropLocation dropLocation = event.getDropLocation();

            sourceItem.getParent().getChildren().remove(sourceItem);
            if(dropLocation == GridDropLocation.ON_TOP) {
                targetItem.getChildren().add(sourceItem);
                sourceItem.setParent(targetItem);
            }else {
                targetItem.getParent().getChildren().add(sourceItem);
                sourceItem.setParent(targetItem.getParent());
            }

            grid.getDataProvider().refreshAll();

        });
        grid.addDragEndListener(event -> {
            draggedItem.set(null);
            grid.setDropMode(GridDropMode.ON_TOP_OR_BETWEEN);
        });

//        grid.addHierarchyColumn(treeItem -> treeItem.getName()).setHeader("TreeItem");
//
//
//        LinkedList<TreeItem> rootElements = new LinkedList<>();
//        rootElements.add(rootItem);
//
//
//        grid.setItems(rootElements.stream(), new ValueProvider<TreeItem, Stream<TreeItem>>() {
//            @Override
//            public Stream<TreeItem> apply(TreeItem treeItem) {
//                List<TreeItem> children = treeItem.getChildren();
//                return children.stream();
//            }
//        });
//
//
//        add(grid);
//
//        DropTarget<TreeGrid> dropTarget = DropTarget.create(grid);
//
//        grid.setRowsDraggable(true);
//
//
//        dropTarget.addDropListener(event -> {
//            // move the dragged component to inside the drop target component
//            if (event.getDropEffect() == DropEffect.MOVE) {
//                // the drag source is available only if the dragged component is from
//                // the same UI as the drop target
//                event.getDragSourceComponent();
//
//                event.getDragData().ifPresent(data -> {
//                    // the server side drag data is available if it has been set and the
//                    // component was dragged from the same UI as the drop target
//                });
//            }
//        });
    }

    private TreeItem getTreeStructure() {
        TreeItem rootItem = new TreeItem(null, "root");

        TreeItem parent1 = new TreeItem(rootItem, "1");
        TreeItem child11 = new TreeItem(parent1,"1-1");
        TreeItem child12 = new TreeItem(parent1,"1-2");


        List<TreeItem> children1 = parent1.getChildren();
        children1.add(child11);
        children1.add(child12);

        TreeItem child111 = new TreeItem(child11,"1-1-1");
        TreeItem child112 = new TreeItem(child11,"1-1-2");


        List<TreeItem> children4 = child11.getChildren();
        children4.add(child111);
        children4.add(child112);


        TreeItem parent2 = new TreeItem(rootItem,"2");
        TreeItem child21 = new TreeItem(parent2,"2-1");
        TreeItem child22 = new TreeItem(parent2,"2-2");


        List<TreeItem> children2 = parent2.getChildren();
        children2.add(child21);
        children2.add(child22);

        TreeItem child211 = new TreeItem(child21,"2-1-1");
        TreeItem child212 = new TreeItem(child21,"2-1-2");


        List<TreeItem> children3 = child21.getChildren();
        children3.add(child211);
        children3.add(child212);

        List<TreeItem> rootChildren = rootItem.getChildren();
        rootChildren.add(parent1);
        rootChildren.add(parent2);
        return rootItem;
    }
}
