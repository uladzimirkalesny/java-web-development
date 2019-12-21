package by.epam.training.bank.handler;

import by.epam.training.bank.dto.UserDto;
import by.epam.training.bank.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserHandler {

    public UserDto process(User user) {

        return UserDto.builder()
                      .userId(user.getId())
                      .name(user.getName() + " " + user.getSecondName())
                      .birthday(user.getBirthday())
                      .build();
    }
}
