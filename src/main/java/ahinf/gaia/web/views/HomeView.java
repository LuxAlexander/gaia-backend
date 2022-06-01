package ahinf.gaia.web.views;

import ahinf.gaia.web.layouts.Standard;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.PermitAll;

@Route()
@RouteAlias("/")
@CssImport("./css/standard.css")
@PermitAll
public class HomeView extends Standard{

    public HomeView() {
        super("Home");
        VerticalLayout content = new VerticalLayout();

        //List<Score> scores = restService.getScores();

        /*for (Score score : scores) {
            content.add(score.toString() + "\n");
        }*/
        setContent(content);
    }
}
