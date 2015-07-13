package com.avol.springboot.jersey.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Durga on 7/10/2015.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostalCode {

    public PostalCode(Long id){
        this.id = id;
    }

    private Long id;
    @NotEmpty(message = "Postal Code Mandatory.")
    private String code;
    @NotEmpty(message = "Area Name Mandatory")
    private String areaName;
}
