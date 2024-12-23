package com.example.hacker_news.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "users") // or "users"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the ID value
    private Long id;


    private int age;
    private String firstName;
    private String lastName;






}
