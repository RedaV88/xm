package org.com.tasks.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Person {
    String name;
    String birth_year;
    List<String> films;
    List<String> starships;
}
