package by.epam.training.bank.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class UserDto {
    private final int userId;
    private final String name;
    private final LocalDate birthday;

    private List<CreditDto> credits = new ArrayList<CreditDto>();

}
