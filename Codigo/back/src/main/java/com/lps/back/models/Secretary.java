package com.lps.back.models;

import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Entity
public class Secretary extends Usuario {

}
