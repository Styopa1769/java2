package lesson7.UI.chart;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public interface ChartView extends View {

    Component getChart();

    String getSource();

}
