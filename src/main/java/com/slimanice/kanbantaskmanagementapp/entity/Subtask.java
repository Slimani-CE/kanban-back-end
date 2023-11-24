package com.slimanice.kanbantaskmanagementapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subtask {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String title;
    private Boolean isCompleted;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Task task;
}