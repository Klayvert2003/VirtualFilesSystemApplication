package io.github.klayvert.virtualfilessystem.service;

import io.github.klayvert.virtualfilessystem.domain.entities.Directory;
import io.github.klayvert.virtualfilessystem.domain.repositories.DirectoryRepository;
import io.github.klayvert.virtualfilessystem.rest.dtos.DirectoryDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectoryService {
    private final DirectoryRepository repository;

    public List<DirectoryDTO> findAllTopDirectories() {
        return this.repository
                .findTopDirectories()
                .stream()
                .map(DirectoryDTO::toDTO)
                .toList();
    }

    @Transactional
    public void save(DirectoryDTO dto) {
        if (dto.getParentDirectory() != null && dto.getParentDirectory().getDirectoryId() != null) {
            // Buscar o diretório pai no banco de dados
            Directory parentDirectory = repository.findById(dto.getParentDirectory().getDirectoryId())
                    .orElseThrow(() -> new RuntimeException("Parent directory not found"));

            // Garantir que o diretório atual não está sendo associado a si mesmo como pai
            if (dto.getDirectoryId() == null || !dto.getDirectoryId().equals(parentDirectory.getDirectoryId())) {
                // Associar o diretório pai corretamente
                dto.setParentDirectory(DirectoryDTO.toOBJ(DirectoryDTO.toDTO(parentDirectory)));
            } else {
                throw new RuntimeException("A directory cannot be its own parent");
            }
        }

        this.repository.save(DirectoryDTO.toOBJ(dto));
    }

    public void update(DirectoryDTO dto) {
        Directory findDirectory = DirectoryDTO.toOBJ(this.findById(dto.getDirectoryId()));

        if (dto.getFiles().isEmpty() && !findDirectory.getFiles().isEmpty()) {
            dto.setFiles(findDirectory.getFiles());
        }

        this.repository.save(DirectoryDTO.toOBJ(dto));
    }

    public void delete(Long id) {
        this.repository.delete(DirectoryDTO.toOBJ(this.findById(id)));
    }

    public DirectoryDTO findById(Long id) {
        return DirectoryDTO.toDTO(this.repository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Directory with id %s not found".formatted(id)
                    )
                )
        );
    }
}
