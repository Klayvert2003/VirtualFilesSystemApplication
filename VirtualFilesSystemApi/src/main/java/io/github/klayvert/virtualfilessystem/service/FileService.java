package io.github.klayvert.virtualfilessystem.service;

import io.github.klayvert.virtualfilessystem.domain.repositories.FileRepository;
import io.github.klayvert.virtualfilessystem.rest.dtos.FileDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class FileService {
    private final FileRepository repository;

    public void save(FileDTO dto) {
        this.repository.save(FileDTO.toOBJ(dto));
    }

    public void update(FileDTO dto) {
        this.findById(dto.getId());
        this.repository.save(FileDTO.toOBJ(dto));
    }

    public void delete(Long id) {
        this.repository.delete(FileDTO.toOBJ(this.findById(id)));
    }

    public FileDTO findById(Long id) {
        return FileDTO.toDTO(this.repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "File with id %s not found".formatted(id)
                        )
                )
        );
    }
}
