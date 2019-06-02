package com.univerage.univerage.model.mongo;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@CompoundIndexes(value = {
        @CompoundIndex(unique = true, def = "{'subject': 1, 'number': 1}")
})
public class Course {
    @Id
    private String id;

    @NotNull
    private String subject;
    @NotNull
    private int number;
    @TextIndexed
    private String description;

    // Metadata
    @LastModifiedDate
    private Long lastModification;
    @CreatedDate
    private Long creationDate;
    @Version
    private long version;
}
