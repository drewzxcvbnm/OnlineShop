package com.online.shop.application.mappers;

import com.online.shop.application.dto.UserDto;
import com.online.shop.application.entities.User;
import com.online.shop.application.entities.UserInfo;
import com.online.shop.application.mappers.service.UserImageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    @Mock
    private UserImageMapper userImageMapper;
    @InjectMocks
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void toUser() {
        UserDto userDto = getUserDto();
        User actual = userMapper.toUser(userDto);
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(getExpectedUser());
    }

    @Test
    public void toUserInfo() {
        UserDto userDto = getUserDto();
        UserInfo info = userMapper.toUserInfo(getUserDto());
        assertThat(info).isEqualToComparingFieldByField(getExpectedUser().getUserInfo());
    }

    private User getExpectedUser() {
        User user = new User();
        user.setUsername("un");
        user.setPassword("pass");
        UserInfo userInfo = new UserInfo();
        user.setUserInfo(userInfo);
        userInfo.setName("n");
        userInfo.setSurname("s");
        userInfo.setEmail("em");
        userInfo.setAddress("ad");
        userInfo.setPhoneNumber("222");
        userInfo.setBankAccount("bc");
        return user;
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("un");
        userDto.setPassword("pass");
        userDto.setName("n");
        userDto.setSurname("s");
        userDto.setEmail("em");
        userDto.setAddress("ad");
        userDto.setPhoneNumber("222");
        userDto.setBankAccount("bc");
        return userDto;
    }
}