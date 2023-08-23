package dsoup.reservation.lecture;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import dsoup.reservation.lecture.config.ResponseCode;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureRegistrationReqDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureReqDTO;
import dsoup.reservation.lecture.employee.reservation.service.EmpLectureReservationService;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureDTO;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureReqDTO;
import dsoup.reservation.lecture.manager.reservation.service.MngLectureReservationService;

@SpringBootTest
class LectureApplicationServiceTests {

	@Autowired
	private MngLectureReservationService mngLectureReservationService;
	
	@Autowired
	private EmpLectureReservationService empLectureReservationService;
	
	@Test
	public void EmpLectureReservationServiceTest() {
		
		// 강연 목록
		EmpLectureReqDTO empLectureReqDTO = new EmpLectureReqDTO();
		empLectureReqDTO.setPage(1);
		empLectureReqDTO.setSize(15);
		empLectureReqDTO.setStart((empLectureReqDTO.getPage()-1)*empLectureReqDTO.getSize());
		empLectureReqDTO.setSearchText("1");
		empLectureReqDTO.setSearchStartTime("2023-08-25 00:00:00");
		empLectureReqDTO.setSearchEndTime("2023-08-27 23:59:59");

		Map<String, Object> resultList = empLectureReservationService.getLectureList(empLectureReqDTO);
		Assert.notNull(resultList, "비정상 호출");
		Assert.isTrue(((List<Map<String, Object>>) resultList.get("list")).size() > 0, "데이터 없음");
		Assert.isTrue((int) resultList.get("totalCount") > 0, "데이터 없음");

		// 강연 신청
		EmpLectureRegistrationReqDTO empLectureRegistrationReqDTO = new EmpLectureRegistrationReqDTO();
		empLectureRegistrationReqDTO.setEmployeeId(2L);
		empLectureRegistrationReqDTO.setLectureId(10L);

		ResponseCode reservationInsertResult = empLectureReservationService.registLectureReservation(empLectureRegistrationReqDTO);

		Assert.notNull(reservationInsertResult, "비정상 호출");
//		Assert.isTrue(reservationInsertResult == ResponseCode.NO_ERROR, "등록 실패");

		// 강연 신청 목록
		empLectureRegistrationReqDTO = new EmpLectureRegistrationReqDTO();
		empLectureRegistrationReqDTO.setEmployeeNumber("00001");
		
		Map<String, Object> reservationList = empLectureReservationService.getLectureReservationList(empLectureRegistrationReqDTO);
		Assert.notNull(reservationList, "비정상 호출");
		Assert.isTrue(((List<Map<String, Object>>) reservationList.get("list")).size() > 0, "데이터 없음");
		Assert.isTrue((int) reservationList.get("totalCount") > 0, "데이터 없음");
		
		// 강연 신청 취소
		empLectureRegistrationReqDTO = new EmpLectureRegistrationReqDTO();
		empLectureRegistrationReqDTO.setEmployeeId(2L);
		empLectureRegistrationReqDTO.setLectureId(10L);

		ResponseCode reservationRemoveResult = empLectureReservationService.removeLectureReservation(empLectureRegistrationReqDTO);
		Assert.notNull(reservationRemoveResult, "비정상 호출");
		Assert.isTrue(reservationRemoveResult == ResponseCode.NO_ERROR, "취소 실패");
		
		// 실시간 인기 강연 목록
		empLectureReqDTO = new EmpLectureReqDTO();
		empLectureReqDTO.setEmployeeId(2L);
		
		Map<String, Object> popularList = empLectureReservationService.getPopularLectureList(empLectureReqDTO);
		Assert.notNull(popularList, "비정상 호출");
		Assert.isTrue(((List<Map<String, Object>>) popularList.get("list")).size() > 0, "데이터 없음");
		Assert.isTrue((int) popularList.get("totalCount") > 0, "데이터 없음");
	}

	@Test
	public void mngLectureReservationServiceTest() {

		// 강연 등록
		MngLectureDTO mngLectureDTO = new MngLectureDTO();
		mngLectureDTO.setLectureHallId(1L);
		mngLectureDTO.setLecturerName("홍길동");
		mngLectureDTO.setLectureTitle("ChatGPT와 학습의 사용성");
		mngLectureDTO.setLectureContents("1.ChatGPT 사용\n2.ChatGPT가 학습하는 방식\n3.ChatGPT와 학습의 미래");
		mngLectureDTO.setLectureStartTime("2023-08-27 15:00:00");
		mngLectureDTO.setLectureEndTime("2023-08-28 10:00:00");
		mngLectureDTO.setLectureSeatsMax(150);

		int lectureInsertResult = mngLectureReservationService.registLecture(mngLectureDTO);

		Assert.notNull(lectureInsertResult, "비정상 호출");
		Assert.isTrue(lectureInsertResult == 1, "등록 실패");

		// 강연 목록
		MngLectureReqDTO mngLectureReqDTO = new MngLectureReqDTO();
		mngLectureReqDTO.setPage(1);
		mngLectureReqDTO.setSize(15);
		mngLectureReqDTO.setStart((mngLectureReqDTO.getPage()-1)*mngLectureReqDTO.getSize());
		mngLectureReqDTO.setSearchText("1");
		mngLectureReqDTO.setSearchStartTime("2023-08-25 00:00:00");
		mngLectureReqDTO.setSearchEndTime("2023-08-27 23:59:59");

		Map<String, Object> resultList = mngLectureReservationService.getLectureList(mngLectureReqDTO);
		Assert.notNull(resultList, "비정상 호출");
		Assert.isTrue(((List<Map<String, Object>>) resultList.get("list")).size() > 0, "데이터 없음");
		Assert.isTrue((int) resultList.get("totalCount") > 0, "데이터 없음");

		// 강연 신청 목록 조회
		Long lectureId = 1L;

		Map<String, Object> resultDetail = mngLectureReservationService.getLecture(lectureId);
		Assert.notNull(resultDetail, "비정상 호출");
		Assert.isTrue((Long) resultDetail.get("lectureId") > 0, "데이터 없음");
		Assert.notNull(resultDetail.get("list"), "신청자 데이터 없음");
		Assert.notNull(((List<Map<String, Object>>) resultDetail.get("list")).size() > 0, "신청자 데이터 없음");
	}

}
