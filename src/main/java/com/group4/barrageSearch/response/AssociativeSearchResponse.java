package com.group4.barrageSearch.response;

import lombok.Data;

import java.util.List;

@Data
public class AssociativeSearchResponse extends JsonpResponse{
    private List<String> results;
}
