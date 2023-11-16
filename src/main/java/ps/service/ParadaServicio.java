package ps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class ParadaServicio {

    // private final String direccion = "http://localhost:8093/parada";
    @Value("${paradaURL}")
    private String paradaURL;
    
    private final RestTemplate rest;

    @Autowired
    public ParadaServicio(RestTemplate rest) {
        this.rest = rest;
    }

    
    @GetMapping
    public ResponseEntity<String> dameParadas() {
        return rest.getForEntity(paradaURL+"/parada", String.class);
    }

}
