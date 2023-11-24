package com.slimanice.kanbantaskmanagementapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String title;
    private String description;
    private String status;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Board board;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<Subtask> subtaskList = new ArrayList<>();
}