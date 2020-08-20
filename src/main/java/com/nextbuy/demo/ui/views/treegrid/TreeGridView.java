package com.nextbuy.demo.ui.views.treegrid;

import com.nextbuy.demo.model.Company;
import com.nextbuy.demo.ui.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.hierarchy.AbstractBackEndHierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalQuery;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.stream.Stream;

@PageTitle("TreeGrid | Vaadin CRM")
@Route(value = "treegrid", layout = MainLayout.class)
public class TreeGridView extends HorizontalLayout {

    public TreeGridView() {
        TreeGrid<TreeItem> grid = new TreeGrid<>();

        TreeItem treeItem = new TreeItem("1");
        TreeItem treeItem2 = new TreeItem("1-1");
        TreeItem treeItem3 = new TreeItem("1-2");

        TreeItem treeItem4 = new TreeItem("2");
        TreeItem treeItem5 = new TreeItem("2-1");
        TreeItem treeItem6 = new TreeItem("2-2");

        TreeItem treeItem8 = new TreeItem("2-1-1");
        TreeItem treeItem9 = new TreeItem("2-1-2");




        List<TreeItem> children = treeItem.getChildren();
        children.add(treeItem2);
        children.add(treeItem3);

        List<TreeItem> children2 = treeItem4.getChildren();
        children2.add(treeItem5);
        children2.add(treeItem6);

        List<TreeItem> children3 = treeItem5.getChildren();
        children3.add(treeItem8);
        children3.add(treeItem9);

        grid.addHierarchyColumn(TreeItem::getName).setHeader("TreeItem");


        HierarchicalDataProvider dataProvider =
                new AbstractBackEndHierarchicalDataProvider<TreeItem, Void>() {

                    @Override
                    public int getChildCount(HierarchicalQuery<TreeItem, Void> query) {
                        if(query.getParent() == null)
                            return 0;
                        return query.getParent().getChildren().size();
                    }

                    @Override
                    public boolean hasChildren(TreeItem item) {
                        return !item.getChildren().isEmpty();
                    }

                    @Override
                    protected Stream<TreeItem> fetchChildrenFromBackEnd(
                            HierarchicalQuery<TreeItem, Void> query) {
                        return query.getParent().getChildren().stream();
                    }
                };

        grid.setItems(List.of(treeItem, treeItem4).stream(), (ValueProvider<TreeItem, Stream<TreeItem>>) treeItem1 -> treeItem1.getChildren().stream());


//        grid.setDataProvider(dataProvider);
        add(grid);

    }


}
