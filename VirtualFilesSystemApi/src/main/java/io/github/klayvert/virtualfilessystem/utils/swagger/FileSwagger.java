package io.github.klayvert.virtualfilessystem.utils.swagger;

import io.github.klayvert.virtualfilessystem.rest.dtos.FileDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "CRUD de Arquivos")
public interface FileSwagger {
    @Operation(summary = "Busca um arquivo pelo seu ID")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Arquivo encontrado"),
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
    FileDTO findById(@RequestParam Long id);

    @Operation(summary = "Realiza o registro de um arquivo")
    @ApiResponse(responseCode = "201", description = "Arquivo criado")
    void save(@RequestBody FileDTO dto);

    @Operation(summary = "Atualiza o registro de um arquivo")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Arquivo atualizado"),
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
    void update(@RequestBody FileDTO dto);

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
