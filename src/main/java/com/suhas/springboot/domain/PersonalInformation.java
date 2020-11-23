
package com.suhas.springboot.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suhas.springboot.jsonconverter.deserializer.TimestampDeserializer;
import com.suhas.springboot.jsonconverter.serializer.TimestampSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "gender",
    "maritialstatus",
    "utctimestamp"
})
public class PersonalInformation implements Serializable
{

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("maritialstatus")
    private String maritialstatus;

    @JsonProperty("utctimestamp")
    @JsonDeserialize(using = TimestampDeserializer.class)
    @JsonSerialize(using = TimestampSerializer.class)
    private Date utctimestamp;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4145945171931340376L;

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("maritialstatus")
    public String getMaritialstatus() {
        return maritialstatus;
    }

    @JsonProperty("maritialstatus")
    public void setMaritialstatus(String maritialstatus) {
        this.maritialstatus = maritialstatus;
    }

    @JsonProperty("utctimestamp")
    public Date getUtctimestamp() {
        return utctimestamp;
    }

    @JsonProperty("utctimestamp")
    public void setUtctimestamp(Date utctimestamp) {
        this.utctimestamp = utctimestamp;
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
