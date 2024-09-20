package io.github.klayvert.virtualfilessystem.service;

import io.github.klayvert.virtualfilessystem.domain.entities.Directory;
import io.github.klayvert.virtualfilessystem.domain.entities.File;
import io.github.klayvert.virtualfilessystem.domain.repositories.DirectoryRepository;
import io.github.klayvert.virtualfilessystem.rest.dtos.DirectoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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
    public void testSaveWithNoChildDirectory() {
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

    @Test
    public void testSaveWithChildDirectory() {
        Directory rootDirectory = new Directory(
                1L,
                "directory1",
                LocalDate.now(),
                new Directory(),
                List.of(),
                List.of()
        );

        Directory parentDirectory = new Directory(
                2L,
                "directory2",
                LocalDate.now(),
                rootDirectory,
                List.of(),
                List.of()
        );

        when(this.directoryRepository.save(rootDirectory)).thenReturn(new Directory());
        this.directoryService.save(DirectoryDTO.toDTO(rootDirectory));
        when(this.directoryRepository.findById(rootDirectory.getDirectoryId())).thenReturn(Optional.of(rootDirectory));
        DirectoryDTO savedRootDirectory = this.directoryService.findById(rootDirectory.getDirectoryId());

        when(this.directoryRepository.save(parentDirectory)).thenReturn(new Directory());
        this.directoryService.save(DirectoryDTO.toDTO(parentDirectory));
        when(this.directoryRepository.findById(parentDirectory.getDirectoryId())).thenReturn(Optional.of(parentDirectory));
        DirectoryDTO savedParentDirectory = this.directoryService.findById(parentDirectory.getDirectoryId());

        assertNotNull(savedRootDirectory);
        assertNotNull(savedParentDirectory);
    }

    @Test
    public void testUpdateWithChildDirectory() {
        List<File> files = List.of(
            new File(
                1L,
                "file1",
                LocalDate.now(),
                new Directory()
            )
        );

        Directory parentDirectory = new Directory(
                2L,
                "parentDirectory",
                LocalDate.now(),
                null,
                List.of(),
                List.of()
        );

        Directory directory = new Directory(
                1L,
                "directory1",
                LocalDate.now(),
                parentDirectory,
                List.of(),
                files
        );

        when(this.directoryRepository.findById(2L)).thenReturn(Optional.of(parentDirectory));
        when(this.directoryRepository.save(directory)).thenReturn(directory);

        DirectoryDTO savedDirectory = this.directoryService.save(DirectoryDTO.toDTO(directory));

        when(this.directoryRepository.findById(savedDirectory.getDirectoryId())).thenReturn(Optional.of(directory));
        savedDirectory = this.directoryService.findById(1L);

        String updatedName = "directory1_updated";
        List<File> updatedFiles = List.of(
            new File(
                2L,
                "file2",
                LocalDate.now(),
                new Directory()
            )
        );

        Directory updatedDirectory = new Directory(
                savedDirectory.getDirectoryId(),
                updatedName,
                savedDirectory.getCreatedAt(),
                savedDirectory.getParentDirectory(),
                savedDirectory.getSubDirectories(),
                updatedFiles
        );

        when(this.directoryRepository.save(updatedDirectory)).thenReturn(updatedDirectory);

        DirectoryDTO updatedDirectoryDTO = this.directoryService.update(DirectoryDTO.toDTO(updatedDirectory));

        when(this.directoryRepository.findById(updatedDirectoryDTO.getDirectoryId())).thenReturn(Optional.of(updatedDirectory));

        DirectoryDTO findUpdatedDirectory = this.directoryService.findById(updatedDirectoryDTO.getDirectoryId());

        assertNotNull(savedDirectory);
        assertNotNull(findUpdatedDirectory);
        assertNotEquals(savedDirectory.getDirectoryName(), findUpdatedDirectory.getDirectoryName());
        assertNotEquals(savedDirectory.getFiles(), findUpdatedDirectory.getFiles());
        assertNotEquals(savedDirectory.getFiles().getFirst().getId(), findUpdatedDirectory.getFiles().getFirst().getId());
        assertNotEquals(savedDirectory.getFiles().getFirst().getFileName(), findUpdatedDirectory.getFiles().getFirst().getFileName());
        assertNotEquals(savedDirectory.getFiles(), findUpdatedDirectory.getFiles());
        assertEquals(savedDirectory.getFiles().size(), updatedDirectoryDTO.getFiles().size());
        assertEquals(findUpdatedDirectory.getDirectoryName(), updatedName);
        assertEquals(findUpdatedDirectory.getFiles(), updatedFiles);
    }


    @Test
    public void testUpdateWithNoChildDirectory() {
        List<File> files = List.of(
            new File(
                1L,
                "file1",
                LocalDate.now(),
                new Directory()
            )
        );

        Directory directory = new Directory(
                1L,
                "directory1",
                LocalDate.now(),
                new Directory(),
                List.of(),
                files
        );

        when(this.directoryRepository.save(directory)).thenReturn(directory);

        DirectoryDTO savedDirectory = this.directoryService.save(DirectoryDTO.toDTO(directory));

        when(this.directoryRepository.findById(savedDirectory.getDirectoryId())).thenReturn(Optional.of(directory));

        savedDirectory = this.directoryService.findById(1L);

        String updatedName = "directory1_updated";
        List<File> updatedFiles = List.of(
            new File(
                2L,
                "file2",
                LocalDate.now(),
                new Directory()
            )
        );

        Directory updatedDirectory = new Directory(
                savedDirectory.getDirectoryId(),
                updatedName,
                savedDirectory.getCreatedAt(),
                savedDirectory.getParentDirectory(),
                savedDirectory.getSubDirectories(),
                updatedFiles
        );

        when(this.directoryRepository.save(updatedDirectory)).thenReturn(updatedDirectory);

        DirectoryDTO updatedDirectoryDTO = this.directoryService.update(DirectoryDTO.toDTO(updatedDirectory));

        when(this.directoryRepository.findById(updatedDirectoryDTO.getDirectoryId())).thenReturn(Optional.of(updatedDirectory));

        DirectoryDTO findUpdatedDirectory = this.directoryService.findById(updatedDirectoryDTO.getDirectoryId());

        assertNotNull(savedDirectory);
        assertNotNull(findUpdatedDirectory);
        assertNotEquals(savedDirectory.getDirectoryName(), findUpdatedDirectory.getDirectoryName());
        assertNotEquals(savedDirectory.getFiles(), findUpdatedDirectory.getFiles());
        assertNotEquals(savedDirectory.getFiles().getFirst().getId(), findUpdatedDirectory.getFiles().getFirst().getId());
        assertNotEquals(savedDirectory.getFiles().getFirst().getFileName(), findUpdatedDirectory.getFiles().getFirst().getFileName());
        assertNotEquals(savedDirectory.getFiles(), findUpdatedDirectory.getFiles());
        assertEquals(savedDirectory.getFiles().size(), updatedDirectoryDTO.getFiles().size());
        assertEquals(findUpdatedDirectory.getDirectoryName(), updatedName);
        assertEquals(findUpdatedDirectory.getFiles(), updatedFiles);
    }

    @Test
    public void testDelete() {
        List<File> files = List.of(
            new File(
                1L,
                "file1",
                LocalDate.now(),
                new Directory()
            )
        );

        Directory directory = new Directory(
                1L,
                "directory1",
                LocalDate.now(),
                new Directory(),
                List.of(),
                files
        );

        when(this.directoryRepository.findById(directory.getDirectoryId())).thenReturn(Optional.of(directory));
        this.directoryService.delete(directory.getDirectoryId());

        verify(this.directoryRepository, times(1)).delete(directory);

        when(this.directoryRepository.findById(directory.getDirectoryId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> this.directoryService.findById(directory.getDirectoryId()));
    }

    @Test
    public void testFindByID() {
        List<File> files = List.of(
            new File(
                1L,
                "file1",
                LocalDate.now(),
                new Directory()
            )
        );

        Directory directory = new Directory(
            1L,
            "directory1",
            LocalDate.now(),
            new Directory(),
            List.of(),
            files
        );

        when(this.directoryRepository.findById(directory.getDirectoryId())).thenReturn(Optional.of(directory));
        Directory findDirectory = DirectoryDTO.toOBJ(this.directoryService.findById(directory.getDirectoryId()));

        assertNotNull(findDirectory);
        assertEquals(directory, findDirectory);
    }

    @Test
    public void testFindByIdNotFoundException() {
        assertThrows(ResponseStatusException.class, () -> this.directoryService.findById(null));
    }
}
