package lesson6.scheduled;

import lesson6.Model.WeatherMeasurement;
import lesson6.Repositories.WeatherMeasurementRepository;
import lesson6.json.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherScheduler {

    @Autowired
    WeatherMeasurementRepository repo;

    @Scheduled(fixedDelay = 60000)
    public void getWeather() throws IOException {
        String key = "de85a2f93f3b5233586d28b2077f0319";
        String moscowId = "524901";
        WeatherMeasurement weather = new WeatherMeasurement(JsonReader.readJsonFromUrl(
                String.format("https://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s",moscowId, key)));
        System.out.println("weather recorded");
        repo.save(weather);
    }
}
