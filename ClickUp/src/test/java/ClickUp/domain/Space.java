package ClickUp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Space {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
