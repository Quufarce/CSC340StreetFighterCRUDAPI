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
    public ResponseEntity<List<StreetFighterCharacter>> getCharacters(
            @RequestParam(required = false) String TypeOfFighter,
            @RequestParam(required = false) String name) {

        if (TypeOfFighter != null) {
            return ResponseEntity.ok(service.getByTypeOfFighter(TypeOfFighter));
        }
        if (name != null) {
            return ResponseEntity.ok(service.searchByName(name));
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreetFighterCharacter> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<StreetFighterCharacter> create(
            @Valid @RequestBody StreetFighterCharacter character) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(character));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreetFighterCharacter> update(
            @PathVariable Long id,
            @Valid @RequestBody StreetFighterCharacter character) {
        return ResponseEntity.ok(service.update(id, character));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}