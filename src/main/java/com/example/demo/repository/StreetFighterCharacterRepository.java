package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StreetFighterCharacter;

import java.util.List;
public interface StreetFighterCharacterRepository extends JpaRepository<StreetFighterCharacter, Long> {

    List<StreetFighterCharacter> findByTypeOfFighterIgnoreCase(String TypeOfFighter);

    List<StreetFighterCharacter> findByNameContainingIgnoreCase(String name);
}
