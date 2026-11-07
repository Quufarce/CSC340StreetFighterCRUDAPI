package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.StreetFighterCharacter;
import com.example.demo.service.StreetFighterCharacterService;

import jakarta.validation.Valid;

@Controller 
public class PageController {
    
    private final StreetFighterCharacterService service;

    public PageController(StreetFighterCharacterService service) {
        this.service = service;
    }

    @GetMapping({"/", "/characters"}) 
    public String gallery(@RequestParam(required = false) String name,
        @RequestParam(required = false) String typeOfFighter,
        @RequestParam(required = false) Integer difficulty,
        Model model) {
        model.addAttribute("characters", service.search(typeOfFighter, name, difficulty));
        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("selectedType", typeOfFighter == null ? "" : typeOfFighter);
        model.addAttribute("selectedDifficulty", difficulty == null ? "" : difficulty);
        return "index"; 
    }

    @GetMapping("/characters/{id}")
    public String character(@PathVariable Long id, Model model) {
        try {
            StreetFighterCharacter character = service.getById(id);
            model.addAttribute("character", character);
            return "details";
        } 
        catch (RuntimeException exception) {
            model.addAttribute("errorMessage",
            exception.getMessage() == null ? "Character not found" : exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/characters/new")
    public String newForm(Model model) {
        StreetFighterCharacter character = new StreetFighterCharacter();
        model.addAttribute("character", character);
        model.addAttribute("formTitle", "Add a Character");
        model.addAttribute("formAction", "/characters");
        return "character-form";
    }

    @PostMapping("/characters")
    public String createCharacter(@Valid @ModelAttribute StreetFighterCharacter character, @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        service.create(character);
        if (imageFile != null && !imageFile.isEmpty()) {
            service.saveThumbnail(character, imageFile);
        }
        return "redirect:/characters";
    }

    @GetMapping("/characters/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            StreetFighterCharacter character = service.getById(id);
            model.addAttribute("character", character);
            model.addAttribute("formTitle", "Edit Character");
            model.addAttribute("formAction", "/characters/" + id);
            return "character-form";
        } 
        catch (RuntimeException exception) {
            model.addAttribute("errorMessage",
            exception.getMessage() == null ? "Character not found" : exception.getMessage());
            return "error";
        }
    }

    @PostMapping("/characters/{id}")
    public String updateCharacter(@PathVariable Long id, @Valid @ModelAttribute StreetFighterCharacter character, MultipartFile imageFile) {
        StreetFighterCharacter updatedCharacter = service.update(id, character);
        if (imageFile != null && !imageFile.isEmpty()) {
            service.saveThumbnail(updatedCharacter, imageFile);
        }
        return "redirect:/characters/" + updatedCharacter.getCharacterId();
    }

    @PostMapping("/characters/{id}/delete")
    public String deleteCharacter(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
    
}
