package lesson7.UI.chart;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.TimeLineDataset;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.options.scale.TimeScale;
import com.byteowls.vaadin.chartjs.options.scale.TimeScaleOptions;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import lesson7.Model.WeatherMeasurement;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@UIScope
@SpringView
public class BasicLine extends AbstractChartView {

    private List<WeatherMeasurement> measurements;

    public BasicLine() {
    }

    public void setMeasurements(List<WeatherMeasurement> measurements){
        this.measurements = measurements;
    }

    @Override
    public Component getChart() {
        TimeLineDataset dateSet = new TimeLineDataset();

        int[] color = {0,137,137};
        graphColor(dateSet,color);

        for(WeatherMeasurement measurement: measurements){
            dateSet.addData(LocalDateTime.ofInstant(measurement.getDateTime().toInstant(),
                    ZoneId.systemDefault()), measurement.getTemperature());
        }
        dateSet.label("Температура");


        LineChartConfig config = new LineChartConfig();
        config.data()
                .addDataset(dateSet)
                .and()
                .options()
                .responsive(true)
                .title()
                .display(true)
                .text("График температуры")
                .and()
                .tooltips()
                .mode(InteractionMode.INDEX)
                .intersect(false)
                .and()
                .hover()
                .mode(InteractionMode.NEAREST)
                .intersect(true)
                .and()
                .scales()
                .add(Axis.X, new TimeScale()
                        .display(true)
                        .scaleLabel()
                        .display(true)
                        .labelString("Время")
                        .and()
                        .time()
                        .stepSize(1)
                        .unit(TimeScaleOptions.Unit.HOUR)
                        .displayFormats()
                        .day("HH.DD.MM")
                        .and()
                        .and()
                )
                .add(Axis.Y, new LinearScale()
                        .display(true)
                        .scaleLabel()
                        .display(true)
                        .labelString("Температура, К")
                        .and()
                        .ticks()
                        .suggestedMin(250)
                        .suggestedMax(350)
                        .and()
                        .position(Position.RIGHT))
                .and()
                .done();

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);

        return chart;
    }

    private void graphColor(TimeLineDataset timeLineDataset,int[] color){
        timeLineDataset.borderColor(ColorUtils.toRgb(color));
        timeLineDataset.backgroundColor(ColorUtils.toRgb(color));
        timeLineDataset.pointBorderColor(ColorUtils.toRgb(color));
        timeLineDataset.pointBackgroundColor(ColorUtils.toRgb(color));
        timeLineDataset.pointBorderWidth(0);
        timeLineDataset.fill(false);
    }
}
