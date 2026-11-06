package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StreetFighterCharacter;
import com.example.demo.service.StreetFighterCharacterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/characters")
public class StreetFighterCharacterController {

    private final StreetFighterCharacterService service;

    public StreetFighterCharacterController(StreetFighterCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<StreetFighterCharacter> getCharacters(
            @RequestParam(required = false) String typeOfFighter,
            @RequestParam(required = false) String name) {

        return service.search(typeOfFighter, name);
    }

    @GetMapping("/{id}")
    public StreetFighterCharacter getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StreetFighterCharacter create(@Valid @RequestBody StreetFighterCharacter character) {
        return service.create(character);
    }

    @PutMapping("/{id}")
    public StreetFighterCharacter update(@PathVariable Long id, @Valid @RequestBody StreetFighterCharacter character) {
        return service.update(id, character);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/pulse")
    public ResponseEntity<String> checkPulse() {
        return ResponseEntity.ok("Pulse check successful");
    }
}