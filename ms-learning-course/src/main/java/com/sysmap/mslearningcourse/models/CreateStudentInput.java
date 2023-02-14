package com.sysmap.mslearningcourse.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateStudentInput {
    @NotBlank(message = "O nome do curso não pode ser vazio!")
    @Size(min=4, message = "O nome do curso precisa ter no mínimo 4 caracteres!")
    private String courseName;
}
