package com.example.hopeconnectt.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/external-data")
public class ExternalApiRedirectController {

    private static final Map<String, String> REDIRECT_URLS = new HashMap<>();
    private static final Map<String, String> SERVICE_NAMES = new HashMap<>();

    static {
        REDIRECT_URLS.put("weather", "https://www.accuweather.com/en/ps/gaza/297930/weather-forecast/297930");
        REDIRECT_URLS.put("payment", "https://www.paypal.com/fr/home");
        REDIRECT_URLS.put("education", "https://www.unrwa.org/education");
        REDIRECT_URLS.put("health", "https://www.who.int/health-topics");
        REDIRECT_URLS.put("emergency", "https://donate.unrwa.org/emergency/~my-donation");
        REDIRECT_URLS.put("location", "https://www.google.com/maps/place/Gaza/");
        
        SERVICE_NAMES.put("weather", "Weather Service");
        SERVICE_NAMES.put("payment", "Payment Service");
        SERVICE_NAMES.put("education", "Education Service");
        SERVICE_NAMES.put("health", "Health Service");
        SERVICE_NAMES.put("emergency", "Emergency Service");
        SERVICE_NAMES.put("location", "Location Service");
    }

    @GetMapping("/{feature}")
    public ResponseEntity<Map<String, Object>> getExternalServiceInfo(@PathVariable String feature) {
        String lowerFeature = feature.toLowerCase();
        if (!REDIRECT_URLS.containsKey(lowerFeature)) {
            throw new IllegalArgumentException("Feature not supported: " + feature);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("service", SERVICE_NAMES.get(lowerFeature));
        response.put("url", REDIRECT_URLS.get(lowerFeature));
        response.put("description", "You can use this URL directly or use the redirect endpoint");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{feature}/redirect")
    public RedirectView redirectToExternalSite(@PathVariable String feature) {
        String url = REDIRECT_URLS.get(feature.toLowerCase());

        if (url != null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(url);
            return redirectView;
        } else {
            throw new IllegalArgumentException("Feature not supported: " + feature);
        }
    }

    @GetMapping("/location/details")
    public ResponseEntity<Map<String, String>> getGazaLocationDetails() {
        Map<String, String> location = new HashMap<>();
        location.put("city", "Gaza");
        location.put("coordinates", "31.5017° N, 34.4668° E");
        location.put("mapUrl", "https://www.google.com/maps/place/Gaza/");
        return ResponseEntity.ok(location);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidFeature(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid feature");
        error.put("message", ex.getMessage());
        error.put("availableFeatures", String.join(", ", REDIRECT_URLS.keySet()));
        return ResponseEntity.badRequest().body(error);
    }
}