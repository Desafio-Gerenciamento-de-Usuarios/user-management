package ms.usermanagement.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import ms.usermanagement.application.usecase.collection.GenerateCollectionUseCase;
import ms.usermanagement.application.usecase.collection.input.HttpInput;
import ms.usermanagement.domain.model.CollectionType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final GenerateCollectionUseCase generateCollection;

    public CollectionController(GenerateCollectionUseCase generateCollection) {
        this.generateCollection = generateCollection;
    }

    @GetMapping("/insomnia")
    public ResponseEntity<String> downloadCollectionInsomnia(HttpServletRequest request) {
        HttpInput input = HttpInput.from(request, CollectionType.INSOMNIA);

        String collectionYaml = generateCollection.execute(input);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"insomnia-collection.yaml\"")
                .contentType(MediaType.parseMediaType("application/x-yaml"))
                .body(collectionYaml);
    }

    @GetMapping("/postman")
    public ResponseEntity<String> downloadCollectionPostman(HttpServletRequest request) {
        HttpInput input = HttpInput.from(request, CollectionType.POSTMAN);

        String collectionYaml = generateCollection.execute(input);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"postman-collection.json\"")
                .contentType(MediaType.parseMediaType("application/x-yaml"))
                .body(collectionYaml);
    }
}
