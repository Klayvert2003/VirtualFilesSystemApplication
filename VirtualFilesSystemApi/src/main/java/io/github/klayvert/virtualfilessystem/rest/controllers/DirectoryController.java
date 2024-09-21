package io.github.klayvert.virtualfilessystem.rest.controllers;

import io.github.klayvert.virtualfilessystem.rest.dtos.DirectoryDTO;
import io.github.klayvert.virtualfilessystem.service.DirectoryService;
import io.github.klayvert.virtualfilessystem.utils.swagger.DirectorySwagger;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/directory")
public class DirectoryController implements DirectorySwagger {
    private DirectoryService directoryService;

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public DirectoryDTO findById(@RequestParam Long id) {
        return this.directoryService.findById(id);
    }

    @GetMapping("/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectoryDTO> findAllTopDirectories() {
        return this.directoryService.findAllTopDirectories();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public DirectoryDTO save(@RequestBody DirectoryDTO dto) {
        return this.directoryService.save(dto);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public DirectoryDTO update(@RequestBody DirectoryDTO dto) {
        return this.directoryService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.directoryService.delete(id);
    }
}
