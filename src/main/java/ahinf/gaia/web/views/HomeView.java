package ahinf.gaia.web.views;

import ahinf.gaia.api.db.AnimalRepository;
import ahinf.gaia.api.entities.Animal;
import ahinf.gaia.web.layouts.Standard;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@Route()
@RouteAlias("/")
@CssImport("./css/standard.css")
public class HomeView extends Standard{

    private AnimalRepository repos;
    public HomeView() {
        super("Home");
        VerticalLayout content = new VerticalLayout();
        setContent(content);
    }
}
