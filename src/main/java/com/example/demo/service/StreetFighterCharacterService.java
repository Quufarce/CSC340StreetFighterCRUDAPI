package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.StreetFighterCharacter;
import com.example.demo.repository.StreetFighterCharacterRepository;

@Service
public class StreetFighterCharacterService {

    private final StreetFighterCharacterRepository repository;

    private static final String UPLOAD_DIR = "src/main/resources/static/thumbnails/";

    public StreetFighterCharacterService(StreetFighterCharacterRepository repository) {
        this.repository = repository;
    }

    public List<StreetFighterCharacter> getAll() {
        return repository.findAll();
    }

    public StreetFighterCharacter getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Character id not found: " + id));
    }

    public List<StreetFighterCharacter> search(String typeOfFighter, String name) {
        if (typeOfFighter != null && !typeOfFighter.isEmpty()) {
            return repository.findByTypeOfFighterIgnoreCase(typeOfFighter);
        } else if (name != null && !name.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(name);
        } else {
            return repository.findAll();
        }
    }

    public StreetFighterCharacter create(StreetFighterCharacter character) {
        character.setCharacterId(null); 
        return repository.save(character);
    }

    public StreetFighterCharacter update(Long id, StreetFighterCharacter updated) {
    StreetFighterCharacter existing = getById(id); 
    existing.setName(updated.getName());
    existing.setDescription(updated.getDescription());
    existing.setFirstAppearance(updated.getFirstAppearance());
    existing.setTypeOfFighter(updated.getTypeOfFighter());
    existing.setDifficulty(updated.getDifficulty());
    return repository.save(existing);
    }

    public void delete(Long id) {
        StreetFighterCharacter existing = getById(id);
        repository.delete(existing);
    }

    public void saveThumbnail(StreetFighterCharacter character, MultipartFile imageFile) {
        String originalFileName = imageFile.getOriginalFilename();
        try {
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String newFileName = "character_" + character.getCharacterId() + "." + fileExtension;
            Path path = Paths.get(UPLOAD_DIR + newFileName);
            InputStream inputStream = imageFile.getInputStream();
            Files.createDirectories(Paths.get(UPLOAD_DIR)); // Ensure the directory exists
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            character.setThumbnailUrl(newFileName);
            repository.save(character);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save image", e);
        }
    }

}