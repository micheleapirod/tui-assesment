package com.tui.proof.search;

import com.tui.proof.domain.QueryOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchQ {

    private String key;
    private QueryOperation queryOperation;
    private String value;

}
