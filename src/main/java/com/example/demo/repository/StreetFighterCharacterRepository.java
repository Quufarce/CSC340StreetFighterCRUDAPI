package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StreetFighterCharacter;
public interface StreetFighterCharacterRepository extends JpaRepository<StreetFighterCharacter, Long> {

    List<StreetFighterCharacter> findByTypeOfFighterIgnoreCase(String TypeOfFighter);

    List<StreetFighterCharacter> findByNameContainingIgnoreCase(String name);

    List<StreetFighterCharacter> findByDifficulty(Integer difficulty);
    
}
