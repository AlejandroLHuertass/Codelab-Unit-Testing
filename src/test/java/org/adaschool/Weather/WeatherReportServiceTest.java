package org.adaschool.Weather;


import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherReportServiceTest {

    private static final String API_KEY = "b521df53cc3de83b65f478a4238cc7ee";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";


    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    WeatherReportService weatherReportService;


    @Test
    public void testGetWeatherReport() {

        double latitude = 40.7128;
        double longitude = -74.0060;

    
        WeatherApiResponse respuesta = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(0);
        main.setHumidity(91);
        respuesta.setMain(main);

        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=b521df53cc3de83b65f478a4238cc7ee";

        when(restTemplate.getForObject(url, WeatherApiResponse.class)).thenReturn(respuesta);


        WeatherReport report = weatherReportService.getWeatherReport(latitude, longitude);

        assertEquals(0, report.getTemperature());
        assertEquals(91, report.getHumidity());
    }
}
