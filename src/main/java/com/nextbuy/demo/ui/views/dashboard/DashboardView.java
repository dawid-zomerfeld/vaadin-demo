package com.nextbuy.demo.ui.views.dashboard;


import com.nextbuy.demo.ui.MainLayout;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropEvent;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;


@CssImport("./styles/main.css")
@PageTitle("Dashboard | Vaadin CRM")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends HorizontalLayout {

    private Button button;
    private VerticalLayout columnA;
    private VerticalLayout columnB;

    public DashboardView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        VerticalLayout columnAWrapper = new VerticalLayout();
        columnAWrapper.addClassName("wrapper");
        columnA = new VerticalLayout();
        columnA.addClassNames("column", "column-a");
        columnAWrapper.add(new Label("Column A"), columnA);

        VerticalLayout columnBWrapper = new VerticalLayout();
        columnBWrapper.addClassName("wrapper");
        columnB = new VerticalLayout();
        columnB.addClassNames("column", "column-b");
        columnBWrapper.add(new Label("Column B"), columnB);

        button = new Button("Would you drag me to column B");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setClassName("my-button");

        DragSource<Button> dragSource = DragSource.create(button);
        dragSource.addDragStartListener(event -> {
            if (button.getParent().get() == columnA) {
                columnB.setClassName("drop-here", true);
                event.setDragData("From A");
            } else {
                columnA.setClassName("drop-here", true);
                event.setDragData("From B");
            }
        });

        dragSource.addDragEndListener(event -> {
            columnA.setClassName("drop-here", false);
            columnB.setClassName("drop-here", false);
        });

        DropTarget.create(columnA).addDropListener(this::onDrop);
        DropTarget.create(columnB).addDropListener(this::onDrop);

        columnA.add(button);
        add(columnAWrapper, columnBWrapper);
    }

    private void onDrop(DropEvent<VerticalLayout> event) {
        if (event.getDragSourceComponent().isPresent() && event.getDragSourceComponent().get() == button) {
            String dragData = (String) event.getDragData().orElse("");
            if ("From A".equals(dragData) && event.getComponent() == columnB) {
                columnA.remove(button);
                columnB.add(button);
                button.setText("Good! I'd like to go back to column A");
            } else if ("From B".equals(dragData) && event.getComponent() == columnA) {
                columnB.remove(button);
                columnA.add(button);
                button.setText("Nice! I'd like to go back to column B");
            }
        }
    }
}