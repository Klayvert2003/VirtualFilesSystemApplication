package io.github.klayvert.virtualfilessystem.service;

import io.github.klayvert.virtualfilessystem.domain.entities.Directory;
import io.github.klayvert.virtualfilessystem.domain.repositories.DirectoryRepository;
import io.github.klayvert.virtualfilessystem.rest.dtos.DirectoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

// TODO: Adicionar teste de update(apenas com diretório pai e com diretório filho)
// TODO: Adicionar teste de delete
// TODO: Adicionar teste de findById
// TODO: Adicionar teste para capturar exceção de findById
@ExtendWith(MockitoExtension.class)
public class DirectoryServiceTests {
    @Mock
    private DirectoryRepository directoryRepository;

    @InjectMocks
    private DirectoryService directoryService;

    @Test
    public void testFindAll() {
        Directory directory1 = new Directory(
            1L,
            "directory1",
            LocalDate.now(),
            new Directory(),
            List.of(),
            List.of()
        );

        Directory directory2 = new Directory(
            2L,
            "directory2",
            LocalDate.now(),
            new Directory(),
            List.of(),
            List.of()
        );

        when(this.directoryRepository.findTopDirectories()).thenReturn(Arrays.asList(directory1, directory2));

        List<DirectoryDTO> directories = this.directoryService.findAllTopDirectories();

        assertEquals(directories.size(), 2);
        assertEquals(directories.get(0).getDirectoryId(), 1);
        assertEquals(directories.get(1).getDirectoryId(), 2);
        assertEquals(directories.get(0).getDirectoryName(), directory1.getDirectoryName());
        assertEquals(directories.get(1).getDirectoryName(), directory2.getDirectoryName());
        assertEquals(directories.get(0).getCreatedAt(), directory1.getCreatedAt());
        assertEquals(directories.get(1).getCreatedAt(), directory2.getCreatedAt());
        assertEquals(directories.get(0).getParentDirectory(), directory1.getParentDirectory());
        assertEquals(directories.get(1).getParentDirectory(), directory2.getParentDirectory());
        assertEquals(directories.get(0).getSubDirectories(), directory1.getSubDirectories());
        assertEquals(directories.get(1).getSubDirectories(), directory2.getSubDirectories());
        assertEquals(directories.get(0).getFiles(), directory1.getFiles());
        assertEquals(directories.get(1).getFiles(), directory2.getFiles());
    }

    @Test
    public void testSave() {
        Directory directory = new Directory(
            1L,
            "directory1",
            LocalDate.now(),
            new Directory(),
            List.of(),
            List.of()
        );

        when(this.directoryRepository.save(directory)).thenReturn(new Directory());

        this.directoryService.save(DirectoryDTO.toDTO(directory));

        when(this.directoryRepository.findById(directory.getDirectoryId())).thenReturn(Optional.of(directory));

        DirectoryDTO savedDirectory = this.directoryService.findById(directory.getDirectoryId());

        assertNotNull(savedDirectory);
    }
}
