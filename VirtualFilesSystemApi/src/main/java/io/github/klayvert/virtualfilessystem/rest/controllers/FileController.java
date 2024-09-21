package io.github.klayvert.virtualfilessystem.rest.controllers;

import io.github.klayvert.virtualfilessystem.rest.dtos.FileDTO;
import io.github.klayvert.virtualfilessystem.service.FileService;
import io.github.klayvert.virtualfilessystem.utils.swagger.FileSwagger;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/file")
public class FileController implements FileSwagger {
    private FileService fileService;

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public FileDTO findById(@RequestParam Long id) {
        return this.fileService.findById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody FileDTO dto) {
        this.fileService.save(dto);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody FileDTO dto) {
        this.fileService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.fileService.delete(id);
    }
}
