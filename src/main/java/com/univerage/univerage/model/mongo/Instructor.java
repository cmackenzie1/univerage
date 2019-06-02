package com.univerage.univerage.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Document
public class Instructor {
    @Id
    @NotNull
    private String id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
