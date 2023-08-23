package dsoup.reservation.lecture.employee.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dsoup.reservation.lecture.employee.reservation.dto.EmpEmployeeDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureRegistrationReqDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureReqDTO;

@Repository
@Mapper
public interface EmpLectureReservationMapper {

	EmpEmployeeDTO selectEmployee(String id);

	List<EmpLectureDTO> selectLectureList(EmpLectureReqDTO req);

	int selectLectureListCount(EmpLectureReqDTO req);

	EmpLectureDTO selectLectureReservationYn(EmpLectureRegistrationReqDTO req);

	int insertLectureReservation(EmpLectureRegistrationReqDTO req);

	List<EmpLectureDTO> selectLectureReservationList(EmpLectureRegistrationReqDTO req);

	int selectLectureReservationListCount(EmpLectureRegistrationReqDTO req);

	int cancelLectureReservation(EmpLectureRegistrationReqDTO req);

	List<EmpLectureDTO> selectPopularLectureList(EmpLectureReqDTO req);

	int selectPopularLectureListCount(EmpLectureReqDTO req);

}
