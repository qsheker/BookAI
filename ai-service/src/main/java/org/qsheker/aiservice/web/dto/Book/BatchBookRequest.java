package org.qsheker.aiservice.web.dto.Book;

import lombok.Data;

import java.util.List;

@Data
public class BatchBookRequest {
    private List<Long> bookIds;
}
