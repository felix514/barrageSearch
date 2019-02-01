package com.group4.barrageSearch.response;

import com.group4.barrageSearch.entity.BarrageInfoImpl;
import lombok.Data;

import java.util.List;

@Data
public class PopularBarrageResponse extends JsonpResponse {
    private List<BarrageInfoImpl> popularBarrages;
}
