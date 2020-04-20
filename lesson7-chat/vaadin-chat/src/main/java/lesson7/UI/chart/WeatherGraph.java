package lesson7.UI.chart;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import lesson7.Repositories.WeatherMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/graph")
public class WeatherGraph extends UI {

    @Autowired
    WeatherMeasurementRepository repo;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout verticalLayout = new VerticalLayout();

        BasicLine basicLine = new BasicLine();
        basicLine.setMeasurements(repo.findAllByOrderByDateTime());

        Component chart = basicLine.getChart();
        chart.setSizeFull();

        verticalLayout.addComponent(chart);
        setContent(verticalLayout);
    }
}
