package ms.usermanagement.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import ms.usermanagement.application.usecase.collection.GenerateInsomniaCollectionUseCase;
import ms.usermanagement.application.usecase.collection.input.HttpInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final GenerateInsomniaCollectionUseCase generateCollection;

    public CollectionController(GenerateInsomniaCollectionUseCase generateCollection) {
        this.generateCollection = generateCollection;
    }


    @GetMapping("/insomnia")
    public ResponseEntity<String> downloadCollection(HttpServletRequest request) {
        HttpInput input = HttpInput.from(request);

        String collectionYaml = generateCollection.execute(input);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"insomnia-collection.yaml\"")
                .contentType(MediaType.parseMediaType("application/x-yaml"))
                .body(collectionYaml);
    }
}
