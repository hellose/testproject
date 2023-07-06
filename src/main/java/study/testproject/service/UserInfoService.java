package study.testproject.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import study.testproject.dto.UserInfoDto;
import study.testproject.dto.mapper.UserInfoMapper;
import study.testproject.entity.UserInfo;
import study.testproject.repository.UserInfoRepository;

@Service
@RequiredArgsConstructor
public class UserInfoService {

	private final UserInfoRepository userInfoRepository;
	private final UserInfoMapper userInfoMapper;

	private final PasswordEncoder encoder;

	@Transactional
	public UserInfoDto insertUser(UserInfoDto dto) {
		dto.setPassword(encoder.encode(dto.getPassword()));
		UserInfo savedUserInfo = userInfoRepository.save(userInfoMapper.toEntity(dto));

		UserInfoDto savedUserInfoDto = userInfoMapper.toDtoWithoutPassword(savedUserInfo);
		return savedUserInfoDto;
	}

	@Transactional(readOnly = true)
	public List<UserInfoDto> getAllUser() {
		List<UserInfo> userInfoList = userInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		return userInfoMapper.toDtoListWithoutPassword(userInfoList);
	}

}