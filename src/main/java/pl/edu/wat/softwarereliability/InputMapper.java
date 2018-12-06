package pl.edu.wat.softwarereliability;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InputMapper {

    ModelInput map(Input input) {
        return ModelInput.builder()
                .times(getTimes(input.getFile()))
                .epsilon(input.getEpsilon())
                .build();
    }

    private List<Integer> getTimes(MultipartFile file) {
        List<Integer> times = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Arrays.stream(line.split("\\s+"))
                        .forEach(token -> times.add(Integer.parseInt(token)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return times;
    }


}
