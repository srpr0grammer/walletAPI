package com.wallet.service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String statusMessage;
    private final Integer statusCode;

    public BusinessExceptionBody getOnlyBody() {
        return BusinessExceptionBody.builder()
                .statusMessage(this.statusMessage)
                .statusCode(this.statusCode)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BusinessExceptionBody {
        private String statusMessage;
        private Integer statusCode;
    }
}
