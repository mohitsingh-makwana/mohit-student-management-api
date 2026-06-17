package com.mohit.student_management_api.mapper;

import org.springframework.stereotype.Component;

import com.mohit.student_management_api.dto.ClassRoomResponseDto;
import com.mohit.student_management_api.entity.ClassRoom;

@Component
public class ClassroomMapper {

	public ClassRoomResponseDto toClassRoomResponseDto(ClassRoom classRoom) {
		return ClassRoomResponseDto.builder()
				.name(classRoom.getName())
				.CoordinatorName(classRoom.getCoordinator().getName())
				.build();
	}
}
