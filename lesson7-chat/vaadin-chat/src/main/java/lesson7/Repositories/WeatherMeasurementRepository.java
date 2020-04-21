package lesson7.Repositories;

import lesson7.Model.WeatherMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherMeasurementRepository extends JpaRepository<WeatherMeasurement, Long> {
    List<WeatherMeasurement> findAllByOrderByDateTime();
}
