package com.stepstone.inbucket.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wojtut01 on 09/02/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment {
    public String filename;
    @JsonProperty("content-type")
    public String contentType;
    @JsonProperty("download-link")
    public String downloadLink;
    @JsonProperty("view-link")
    public String viewLink;
    public String md5;

}
