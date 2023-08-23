package dsoup.reservation.lecture.manager.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dsoup.reservation.lecture.manager.reservation.dto.MngLectureDTO;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureRegistrationDTO;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureReqDTO;

@Repository
@Mapper
public interface MngLectureReservationMapper {

	List<MngLectureDTO> selectLectureList(MngLectureReqDTO req);

	int selectLectureListCount(MngLectureReqDTO req);
	
	int insertLecture(MngLectureDTO req);
	
	MngLectureDTO selectLecture(Long id);

	List<MngLectureRegistrationDTO> selectLectureReservationList(Long id);

}
