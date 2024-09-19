package io.github.klayvert.virtualfilessystem.domain.repositories;

import io.github.klayvert.virtualfilessystem.domain.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {}