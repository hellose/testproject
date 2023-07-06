package study.testproject.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import study.testproject.dto.UserInfoDto;
import study.testproject.entity.UserInfo;

@Component
public class UserInfoMapper {

	public UserInfoDto toDtoWithoutPassword(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}

		// password 제외
		UserInfoDto dto = new UserInfoDto();
		dto.setId(userInfo.getId());
		dto.setUserName(userInfo.getUserName());
		dto.setEmail(userInfo.getEmail());
		dto.setEnable(userInfo.getEnable());
		
		return dto;
	}

	public UserInfoDto toDto(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}

		UserInfoDto dto = new UserInfoDto();
		dto.setId(userInfo.getId());
		dto.setUserName(userInfo.getUserName());
		dto.setEmail(userInfo.getEmail());
		dto.setEnable(userInfo.getEnable());
		dto.setPassword(userInfo.getPassword());
		
		return dto;
	}

	public UserInfo toEntity(UserInfoDto dto) {
		if (dto == null) {
			return null;
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setId(dto.getId());
		userInfo.setUserName(dto.getUserName());
		userInfo.setEmail(dto.getEmail());
		userInfo.setPassword(dto.getPassword());
		userInfo.setEnable(dto.getEnable());
		
		return userInfo;
	}

	public List<UserInfoDto> toDtoListWithoutPassword(List<UserInfo> userList) {
		if (userList == null) {
			return null;
		}

		List<UserInfoDto> dtoList = new ArrayList<>(userList.size());
		for (UserInfo userInfo : userList) {
			dtoList.add(toDtoWithoutPassword(userInfo));
		}
		
		return dtoList;
	}

}
