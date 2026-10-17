package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.StreetFighterCharacter;
import com.example.demo.repository.StreetFighterCharacterRepository;

@Service
public class StreetFighterCharacterService {

    private final StreetFighterCharacterRepository repository;

    public StreetFighterCharacterService(StreetFighterCharacterRepository repository) {
        this.repository = repository;
    }

    public List<StreetFighterCharacter> getAll() {
        return repository.findAll();
    }

    public StreetFighterCharacter getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Character not found with id " + id));
    }

    public StreetFighterCharacter create(StreetFighterCharacter character) {
        character.setCharacterId(null); // make sure the database generates the ID
        return repository.save(character);
    }

    public StreetFighterCharacter update(Long id, StreetFighterCharacter updated) {
    StreetFighterCharacter existing = getById(id); // throws 404 if missing
    existing.setName(updated.getName());
    existing.setDescription(updated.getDescription());
    existing.setFirstAppearance(updated.getFirstAppearance());
    existing.setTypeOfFighter(updated.getTypeOfFighter());
    existing.setDifficulty(updated.getDifficulty());
    return repository.save(existing);
}

    public void delete(Long id) {
        StreetFighterCharacter existing = getById(id); // throws 404 if missing
        repository.delete(existing);
    }

    public List<StreetFighterCharacter> getByTypeOfFighter(String typeOfFighter) {
    return repository.findByTypeOfFighterIgnoreCase(typeOfFighter);
}

    public List<StreetFighterCharacter> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

}