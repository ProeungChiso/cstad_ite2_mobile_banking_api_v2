package co.istad.ite2_mbanking_api_v2.feature.user.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record UserDetailsResponse(
        String uuid,
        String name,
        String profileImage,
        String gender,
        LocalDate dob,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String village,
        String street,
        String employeeType,
        String position,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange,
        String studentIdCard,
        List<RoleNameResponse> roles

) {
}
