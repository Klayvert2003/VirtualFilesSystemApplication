package io.github.klayvert.virtualfilessystem.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate createdAt = LocalDate.now();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "directory_id", nullable = false)
    private Directory directory;
}
