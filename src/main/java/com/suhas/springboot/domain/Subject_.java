
package com.suhas.springboot.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "subjectid",
    "subjectname"
})
public class Subject_ implements Serializable
{

    @JsonProperty("subjectid")
    private Integer subjectid;
    @JsonProperty("subjectname")
    private String subjectname;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2771613575590338802L;

    @JsonProperty("subjectid")
    public Integer getSubjectid() {
        return subjectid;
    }

    @JsonProperty("subjectid")
    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    @JsonProperty("subjectname")
    public String getSubjectname() {
        return subjectname;
    }

    @JsonProperty("subjectname")
    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
