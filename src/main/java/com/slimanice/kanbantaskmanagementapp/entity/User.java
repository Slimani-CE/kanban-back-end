package com.slimanice.kanbantaskmanagementapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();
}
