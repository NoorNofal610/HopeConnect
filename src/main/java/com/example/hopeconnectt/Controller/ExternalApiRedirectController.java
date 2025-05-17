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

   static {
    REDIRECT_URLS.put("weather", "https://www.accuweather.com/en/ps/gaza/297930/weather-forecast/297930");
    REDIRECT_URLS.put("payment", "https://www.paypal.com/donate");
    REDIRECT_URLS.put("education", "https://www.unrwa.org/education");
    REDIRECT_URLS.put("health", "https://www.who.int/health-topics");
    REDIRECT_URLS.put("emergency", "https://donate.unrwa.org/emergency/~my-donation");
    REDIRECT_URLS.put("volunteer", "https://www.unv.org/become-volunteer");
    REDIRECT_URLS.put("location", "https://www.google.com/maps/place/Gaza/");
}


    @GetMapping("/{feature}")
    public RedirectView redirectToExternalSite(@PathVariable String feature) {
        String url = REDIRECT_URLS.get(feature.toLowerCase());

        if (url != null) {
            return new RedirectView(url);
            
        } else {
            throw new IllegalArgumentException("Feature not supported: " + feature);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidFeature(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @GetMapping("/location")
public ResponseEntity<Map<String, String>> getGazaLocation() {
    Map<String, String> location = new HashMap<>();
    location.put("city", "Gaza");
    location.put("mapUrl", "https://www.google.com/maps/place/Gaza/");
    return ResponseEntity.ok(location);
}
}

