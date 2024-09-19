package io.github.klayvert.virtualfilessystem.domain.repositories;

import io.github.klayvert.virtualfilessystem.domain.entities.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    @Query("SELECT d FROM Directory d WHERE d.parentDirectory IS NULL")
    List<Directory> findTopDirectories();
}