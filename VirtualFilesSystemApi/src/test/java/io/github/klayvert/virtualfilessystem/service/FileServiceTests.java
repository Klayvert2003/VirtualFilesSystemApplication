package io.github.klayvert.virtualfilessystem.service;

import io.github.klayvert.virtualfilessystem.domain.entities.Directory;
import io.github.klayvert.virtualfilessystem.domain.entities.File;
import io.github.klayvert.virtualfilessystem.domain.repositories.FileRepository;
import io.github.klayvert.virtualfilessystem.rest.dtos.FileDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileServiceTests {
    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileService fileService;

    @Test
    public void testFindAll() {
        File file1 = new File(
            1L,
            "file1",
            LocalDate.now(),
            new Directory()
        );

        when(this.fileRepository.findById(file1.getId())).thenReturn(Optional.of(file1));

        FileDTO fileDTO = this.fileService.findById(file1.getId());

        assertEquals(FileDTO.toOBJ(fileDTO), file1);
        assertEquals(fileDTO, FileDTO.toDTO(file1));
        assertEquals(fileDTO.getId(), file1.getId());
        assertEquals(fileDTO.getFileName(), file1.getFileName());
        assertEquals(fileDTO.getCreatedAt(), file1.getCreatedAt());
        assertEquals(fileDTO.getDirectory(), file1.getDirectory());
    }

    @Test
    public void testSave() {
        File file1 = new File(
                1L,
                "file1",
                LocalDate.now(),
                new Directory()
        );

        when(this.fileRepository.save(file1)).thenReturn(file1);
        this.fileService.save(FileDTO.toDTO(file1));
        when(this.fileRepository.findById(file1.getId())).thenReturn(Optional.of(file1));

        FileDTO savedFile = this.fileService.findById(file1.getId());

        assertNotNull(savedFile);
        assertEquals(FileDTO.toOBJ(savedFile), file1);
        assertEquals(savedFile, FileDTO.toDTO(file1));
        assertEquals(savedFile.getId(), file1.getId());
        assertEquals(savedFile.getFileName(), file1.getFileName());
        assertEquals(savedFile.getCreatedAt(), file1.getCreatedAt());
        assertEquals(savedFile.getDirectory(), file1.getDirectory());
    }

    @Test
    public void testUpdate() {
        File existFile = new File(
            1L,
            "file1",
            LocalDate.now(),
            new Directory()
        );

        when(this.fileRepository.findById(existFile.getId())).thenReturn(Optional.of(existFile));
        when(this.fileRepository.save(existFile)).thenReturn(existFile);
        this.fileService.save(FileDTO.toDTO(existFile));

        FileDTO foundSavedFile = this.fileService.findById(existFile.getId());

        File updatedFile = new File(
                2L,
                "file_updated",
                LocalDate.now(),
                new Directory()
        );

        when(this.fileRepository.findById(updatedFile.getId())).thenReturn(Optional.of(updatedFile));
        when(this.fileRepository.save(updatedFile)).thenReturn(updatedFile);
        this.fileService.update(FileDTO.toDTO(updatedFile));
        when(this.fileRepository.findById(updatedFile.getId())).thenReturn(Optional.of(updatedFile));

        FileDTO foundUpdatedFile = this.fileService.findById(updatedFile.getId());

        assertNotNull(foundSavedFile);
        assertNotNull(foundUpdatedFile);
        assertNotEquals(foundSavedFile.getId(), foundUpdatedFile.getId());
        assertNotEquals(foundSavedFile.getFileName(), foundUpdatedFile.getFileName());
        assertEquals(foundSavedFile.getCreatedAt(), foundUpdatedFile.getCreatedAt());
        assertEquals(foundSavedFile.getDirectory(), foundUpdatedFile.getDirectory());
        assertEquals(FileDTO.toDTO(existFile), foundSavedFile);
        assertEquals(existFile, FileDTO.toOBJ(foundSavedFile));
        assertEquals(FileDTO.toDTO(updatedFile), foundUpdatedFile);
        assertEquals(updatedFile, FileDTO.toOBJ(foundUpdatedFile));
    }

    @Test
    public void testDelete() {
        File file = new File(
                1L,
                "file1",
                LocalDate.now(),
                new Directory()
        );

        when(this.fileRepository.findById(file.getId())).thenReturn(Optional.of(file));
        this.fileService.delete(file.getId());

        verify(this.fileRepository, times(1)).delete(file);

        when(this.fileRepository.findById(file.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> this.fileService.findById(file.getId()));
    }

    @Test
    public void testFindByID() {
       File file =  new File(
            1L,
            "file1",
            LocalDate.now(),
            new Directory()
        );

        when(this.fileRepository.findById(file.getId())).thenReturn(Optional.of(file));
        File foundFile = FileDTO.toOBJ(this.fileService.findById(file.getId()));

        assertNotNull(foundFile);
        assertEquals(file, foundFile);
    }

    @Test
    public void testFindByIdNotFoundException() {
        assertThrows(ResponseStatusException.class, () -> this.fileService.findById(null));
    }
}
