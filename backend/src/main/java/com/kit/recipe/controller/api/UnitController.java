package com.kit.recipe.controller.api;


import com.kit.recipe.dto.UnitDTO;
import com.kit.recipe.entities.units.Unit;
import com.kit.recipe.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/api")
public class UnitController {
    private final UnitRepository unitRepository;

    public UnitController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }



    @PostMapping("/units")
    public ResponseEntity<Unit> addUnit(@RequestBody UnitDTO unit) {
        if (unit.getName() == null || unit.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Unit newUnit = new Unit();
        newUnit.setName(unit.getName());
        Optional<Unit> found =  unitRepository.findByName(
                unit.getName()
            );
        if (found.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(unitRepository.save(newUnit));
    }

    @GetMapping("/units")
    public ResponseEntity<List<Unit>> getUnits() {
        return ResponseEntity.ok(unitRepository.findAll());
    }
}
