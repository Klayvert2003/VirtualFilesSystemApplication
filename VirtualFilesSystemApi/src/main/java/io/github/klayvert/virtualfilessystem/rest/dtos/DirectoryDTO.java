package io.github.klayvert.virtualfilessystem.rest.dtos;

import io.github.klayvert.virtualfilessystem.domain.entities.Directory;
import io.github.klayvert.virtualfilessystem.domain.entities.File;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class DirectoryDTO {
    private Long directoryId;
    private String directoryName;
    private LocalDate createdAt;
    private Directory parentDirectory;
    private List<Directory> subDirectories;
    private List<File> files;

    public static DirectoryDTO toDTO(Directory directory) {
        return new DirectoryDTO(
            directory.getDirectoryId(),
            directory.getDirectoryName(),
            LocalDate.now(),
            directory.getParentDirectory(),
            directory.getSubDirectories(),
            directory.getFiles()
        );
    }

    public static Directory toOBJ(DirectoryDTO dto) {
        return new Directory(
            dto.getDirectoryId(),
            dto.getDirectoryName(),
            LocalDate.now(),
            dto.getParentDirectory(),
            dto.getSubDirectories(),
            dto.getFiles()
        );
    }
}
