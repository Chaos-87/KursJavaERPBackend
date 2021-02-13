package com.compant.erpBackend.dto;

import com.compant.erpBackend.entity.Operator;
import lombok.Data;

@Data
public class OperatorAuthenticationResultDto {

    private Long idOperator;
    private String firstName;
    private String lastName;
    private boolean authenticated;

    public static OperatorAuthenticationResultDto createUnauthenticated() {
        OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
        dto.setAuthenticated(false);
        return dto;
    }

    public static OperatorAuthenticationResultDto of(Operator operator) {
        OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
        dto.setAuthenticated(true);
        dto.setFirstName(operator.getEmployee().getFirstName());
        dto.setLastName(operator.getEmployee().getLastName());
        dto.setIdOperator(operator.getIdOperator());

        return dto;
    }
}
