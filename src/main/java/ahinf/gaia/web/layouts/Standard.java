package ahinf.gaia.web.layouts;

import ahinf.gaia.web.views.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Standard extends VerticalLayout {

    private final VerticalLayout content = new VerticalLayout();
    public Standard(String title) {
        setAlignItems(Alignment.CENTER);
        setPadding(false);
        setId("template");

        content.setId("content");

        HorizontalLayout navBar = createNavbar(title);
        Footer footer = createFooter();

        add(navBar, content, footer);
    }

    private HorizontalLayout createNavbar(String title) {
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.setMinWidth(100, Unit.PERCENTAGE);

        H1 titleLabel = new H1(title);
        titleLabel.setClassName("title");
        navBar.add(titleLabel);
        navBar.setId("navbar");
        navBar.setJustifyContentMode(JustifyContentMode.BETWEEN);

        return navBar;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.setId("footer");
        footer.setText("2022");
        footer.setMinWidth(100, Unit.PERCENTAGE);
        return footer;
    }

    protected void setContent(Component component) {
        this.content.removeAll();
        this.content.add(component);
    }
}