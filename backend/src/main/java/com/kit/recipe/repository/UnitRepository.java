package com.kit.recipe.repository;

import com.kit.recipe.entities.units.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {
    Optional<Unit> findByName(@NonNull String name);

}

