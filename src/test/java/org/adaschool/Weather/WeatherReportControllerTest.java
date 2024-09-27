package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(WeatherReportController.class)
public class WeatherReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherReportService weatherReportService;

    @Test
    public void testGetWeatherReport() throws Exception {
        WeatherReport mockReport = new WeatherReport();
        mockReport.setTemperature(20.0);
        mockReport.setHumidity(65);

        when(weatherReportService.getWeatherReport(anyDouble(), anyDouble())).thenReturn(mockReport);

        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", "37.8267")
                        .param("longitude", "-122.4233")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(20.0))
                .andExpect(jsonPath("$.humidity").value(65));
    }
}
