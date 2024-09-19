package io.github.klayvert.virtualfilessystem.rest.dtos;

import io.github.klayvert.virtualfilessystem.domain.entities.Directory;
import io.github.klayvert.virtualfilessystem.domain.entities.File;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FileDTO {
    private Long id;
    private String fileName;
    private LocalDate createdAt;
    private Directory directory;

    public static FileDTO toDTO(File file) {
        return new FileDTO(
                file.getId(),
                file.getFileName(),
                LocalDate.now(),
                file.getDirectory()
        );
    }

    public static File toOBJ(FileDTO dto) {
        return new File(
                dto.getId(),
                dto.getFileName(),
                LocalDate.now(),
                dto.getDirectory()
        );
    }
}
