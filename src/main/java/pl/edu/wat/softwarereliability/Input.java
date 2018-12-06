package pl.edu.wat.softwarereliability;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
class Input {
    private MultipartFile file;
    private Double epsilon;
}
