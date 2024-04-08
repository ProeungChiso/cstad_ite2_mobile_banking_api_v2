package co.istad.ite2_mbanking_api_v2.feature.user.dto;

import java.math.BigDecimal;

public record UserDetailUpdateRequest(
        String cityOrProvince,
        String khanOrDistrict,
        String employeeType,
        String position,
        String companyName,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange
) {
}
