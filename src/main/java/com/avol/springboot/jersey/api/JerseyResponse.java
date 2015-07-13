package com.avol.springboot.jersey.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 7/10/2015.
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JerseyResponse extends ResourceSupport{

    private int statusCode;
    private String message;
    private List<PostalCode> postalCodes;

    public JerseyResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        this.message = builder.message;
        this.postalCodes = builder.postalCodes;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int statusCode;
        private String message;
        private List<PostalCode> postalCodes;

        public JerseyResponse build(){
            return new JerseyResponse(this);
        }

        public Builder withStatusCode(int input){
            this.statusCode = input;
            return this;
        }

        public Builder withMessage(String input){
            this.message = input;
            return this;
        }

        public Builder withPostalCode(PostalCode input){
            if (this.postalCodes == null) {
                this.postalCodes = new ArrayList<>();
            }
            this.postalCodes.add(input);
            return this;
        }

        public Builder withPostalCodes(List<PostalCode> input){
            if (this.postalCodes == null) {
                this.postalCodes = new ArrayList<>();
            }
            this.postalCodes.addAll(input);
            return this;
        }
    }
}
