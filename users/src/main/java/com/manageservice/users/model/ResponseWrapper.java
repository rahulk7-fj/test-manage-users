package com.manageservice.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@NoArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class ResponseWrapper implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String code;
    private String message;
    private String trackingId;
}
