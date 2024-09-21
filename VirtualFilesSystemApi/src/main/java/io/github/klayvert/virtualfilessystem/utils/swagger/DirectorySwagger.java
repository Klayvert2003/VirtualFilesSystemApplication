package io.github.klayvert.virtualfilessystem.utils.swagger;

import io.github.klayvert.virtualfilessystem.rest.dtos.DirectoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "CRUD de Diretórios")
public interface DirectorySwagger {
    @Operation(summary = "Busca um diretório pelo seu ID")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Diretório encontrado"),
            @ApiResponse(
                responseCode = "404",
                description = "Não encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        type = "object",
                        example = "{\"error\":\"Directory with id not found\"}"
                    )
                )
            )
        }
    )
    DirectoryDTO findById(@RequestParam Long id);

    @Operation(summary = "Busca todos os diretórios filtranddo apenas os diretórios pai")
    @ApiResponse(responseCode = "200", description = "Diretórios encontrados")
    List<DirectoryDTO> findAllTopDirectories();

    @Operation(summary = "Realiza o registro de um diretório")
    @ApiResponse(responseCode = "201", description = "Diretório criado")
    DirectoryDTO save(@RequestBody DirectoryDTO dto);

    @Operation(summary = "Atualiza o registro de um diretório")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Diretório atualizado"),
            @ApiResponse(
                responseCode = "404",
                description = "Não encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        type = "object",
                        example = "{\"error\":\"Directory with id not found\"}"
                    )
                )
            )
        }
    )
    DirectoryDTO update(@RequestBody DirectoryDTO dto);

    @Operation(summary = "Deleta o registro de um diretório")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Diretório atualizado"),
            @ApiResponse(
                responseCode = "404",
                description = "Não encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        type = "object",
                        example = "{\"error\":\"Directory with id not found\"}"
                    )
                )
            )
        }
    )
    void delete(@PathVariable Long id);
}
