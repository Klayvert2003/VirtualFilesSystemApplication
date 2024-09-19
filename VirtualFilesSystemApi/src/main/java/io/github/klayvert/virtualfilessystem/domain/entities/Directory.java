package io.github.klayvert.virtualfilessystem.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "directory")
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directory_id")
    private Long directoryId;

    @Column(name = "directory_name")
    private String directoryName;

    @Column(name = "created_at", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate createdAt = LocalDate.now();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "parent_directory_id")
    private Directory parentDirectory;

    @JsonManagedReference
    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Directory> subDirectories;

    @JsonManagedReference
    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;
}
