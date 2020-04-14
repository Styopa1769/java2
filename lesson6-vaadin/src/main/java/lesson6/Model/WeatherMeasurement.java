package lesson6.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "weather")
public class WeatherMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty("entry id")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @Column (name = "date_time")
    @ApiModelProperty("measurement date time")
    private Date dateTime;

    @Column (name = "temperature")
    @ApiModelProperty("temperature")
    private Double temperature;

    public WeatherMeasurement(JSONObject jsonObject){
        Map<String, Object> jsonMap = jsonObject.toMap();

        this.dateTime = Date.from(Instant.ofEpochSecond(new Long((Integer)jsonMap.get("dt"))));

        Map<String, Object> main = (Map<String, Object>) jsonMap.get("main");
        this.temperature = Double.parseDouble(main.get("temp").toString());
    }

    public WeatherMeasurement() {}

    public Date getDateTime() {
        return dateTime;
    }

    public Double getTemperature() {
        return temperature;
    }
}
