package lesson7.scheduled;

import lesson7.Model.WeatherMeasurement;
import lesson7.Repositories.WeatherMeasurementRepository;
import lesson7.json.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherScheduler {

    @Autowired
    WeatherMeasurementRepository repo;

    @Scheduled(cron = "0 * * * * *")
    public void getWeather() throws IOException {
        String key = "de85a2f93f3b5233586d28b2077f0319";
        String moscowId = "524901";
        WeatherMeasurement weather = new WeatherMeasurement(JsonReader.readJsonFromUrl(
                String.format("https://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s",moscowId, key)));
        System.out.println("weather recorded");
        repo.save(weather);
    }
}
