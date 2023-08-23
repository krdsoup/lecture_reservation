package dsoup.reservation.lecture;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import dsoup.reservation.lecture.employee.reservation.dto.EmpEmployeeDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureRegistrationReqDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureReqDTO;
import dsoup.reservation.lecture.employee.reservation.mapper.EmpLectureReservationMapper;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureDTO;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureRegistrationDTO;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureReqDTO;
import dsoup.reservation.lecture.manager.reservation.mapper.MngLectureReservationMapper;

@SpringBootTest
class LectureApplicationMapperTests {
	
	@Autowired
	private EmpLectureReservationMapper empLectureReservationMapper;
	
	@Autowired
	private MngLectureReservationMapper mngLectureReservationMapper;
	
	@Test
	public void empLectureReservationMapperTest() {
		
		// 강연 목록, 전체 카운트
		EmpLectureReqDTO empLectureReqDTO = new EmpLectureReqDTO();
		empLectureReqDTO.setPage(1);
		empLectureReqDTO.setSize(15);
		empLectureReqDTO.setStart((empLectureReqDTO.getPage()-1)*empLectureReqDTO.getSize());
		empLectureReqDTO.setSearchText("1");
		empLectureReqDTO.setSearchStartTime("2023-08-22 00:00:00");
		empLectureReqDTO.setSearchEndTime("2023-08-27 23:59:59");

		List<EmpLectureDTO> lectureList = empLectureReservationMapper.selectLectureList(empLectureReqDTO);
		Assert.notNull(lectureList, "비정상 호출");
		Assert.isTrue(lectureList.size() > 0, "데이터 없음");
		
		int lectureListCount = empLectureReservationMapper.selectLectureListCount(empLectureReqDTO);
		Assert.notNull(lectureListCount, "비정상 호출");
		Assert.isTrue(lectureListCount > 0, "데이터 없음");
		
		// 노출 시간 제한 확인
		EmpLectureReqDTO empLectureAllReqDTO = new EmpLectureReqDTO();
		empLectureAllReqDTO.setPage(0);
		empLectureAllReqDTO.setSize(0);
		
		List<EmpLectureDTO> lectureAllList = empLectureReservationMapper.selectLectureList(empLectureAllReqDTO);
		String recentTime = lectureAllList.get(0).getLectureStartTime();
		String lastTime = lectureAllList.get(lectureAllList.size()-1).getLectureStartTime();
		// TODO: 시간 비교 하여 제한 시간 확인
		
		// 사번 조회
		String employeeNumber = "00001";
		EmpEmployeeDTO employee = empLectureReservationMapper.selectEmployee(employeeNumber);
		Assert.notNull(employee, "비정상 호출");
		Assert.isTrue(employee.getEmployeeId() > 0, "데이터 없음");
		int randomLecture = (int)Math.floor((Math.random())*15);
		
		// 강연 등록 정보
		EmpLectureRegistrationReqDTO empLectureRegistrationReqDTO = new EmpLectureRegistrationReqDTO();
		empLectureRegistrationReqDTO.setLectureId(lectureList.get(randomLecture).getLectureId());
		empLectureRegistrationReqDTO.setEmployeeId(employee.getEmployeeId());

		// 강연 신청 여부 조회
		EmpLectureDTO registrationResult = empLectureReservationMapper.selectLectureReservationYn(empLectureRegistrationReqDTO);
		Assert.notNull(registrationResult, "비정상 호출");
				
		// 강연 신청
		int registrationInsertResult = empLectureReservationMapper.insertLectureReservation(empLectureRegistrationReqDTO);
		Assert.notNull(registrationInsertResult, "비정상 호출");
//		Assert.isTrue(registrationInsertResult == 1, "등록 실패");
		
		// 강연 신청 내역 조회
		empLectureRegistrationReqDTO = new EmpLectureRegistrationReqDTO();
		empLectureRegistrationReqDTO.setEmployeeNumber(employeeNumber);
		empLectureRegistrationReqDTO.setPage(1);
		empLectureRegistrationReqDTO.setSize(10);
		empLectureRegistrationReqDTO.setStart((empLectureRegistrationReqDTO.getPage()-1)*empLectureRegistrationReqDTO.getSize());
		
		List<EmpLectureDTO> employeeLectureList = empLectureReservationMapper.selectLectureReservationList(empLectureRegistrationReqDTO);
		Assert.notNull(employeeLectureList, "비정상 호출");
		Assert.isTrue(employeeLectureList.size() > 0, "데이터 없음");
		
		int employeeLectureCount = empLectureReservationMapper.selectLectureReservationListCount(empLectureRegistrationReqDTO);
		Assert.notNull(employeeLectureCount, "비정상 호출");
		Assert.isTrue(employeeLectureCount > 0, "데이터 없음");
		
		// 강연 취소
		empLectureRegistrationReqDTO = new EmpLectureRegistrationReqDTO();
		empLectureRegistrationReqDTO.setLectureId(lectureList.get(randomLecture).getLectureId());
		empLectureRegistrationReqDTO.setEmployeeId(employee.getEmployeeId());
		
		int cancelResult = empLectureReservationMapper.cancelLectureReservation(empLectureRegistrationReqDTO);
		Assert.notNull(cancelResult, "비정상 호출");
		Assert.isTrue(cancelResult == 1, "수정 실패");
		
		// 인기 강연 목록
		empLectureReqDTO = new EmpLectureReqDTO();
		empLectureReqDTO.setPage(1);
		empLectureReqDTO.setSize(10);
		empLectureReqDTO.setStart((empLectureReqDTO.getPage() - 1) * empLectureReqDTO.getSize());
		
		List<EmpLectureDTO> popularLectureList = empLectureReservationMapper.selectPopularLectureList(empLectureReqDTO);
		Assert.notNull(popularLectureList, "비정상 호출");
		Assert.isTrue(popularLectureList.size() > 0, "데이터 없음");
		
		int popularLectureListCount = empLectureReservationMapper.selectPopularLectureListCount(empLectureReqDTO);
		Assert.notNull(popularLectureListCount, "비정상 호출");
		Assert.isTrue(popularLectureListCount > 0, "데이터 없음");
		
		empLectureReqDTO.setEmployeeId(1L);
		
		popularLectureList = empLectureReservationMapper.selectPopularLectureList(empLectureReqDTO);
		Assert.notNull(popularLectureList, "비정상 호출");
		Assert.isTrue(popularLectureList.size() > 0, "데이터 없음");
		
		popularLectureListCount = empLectureReservationMapper.selectPopularLectureListCount(empLectureReqDTO);
		Assert.notNull(popularLectureListCount, "비정상 호출");
		Assert.isTrue(popularLectureListCount > 0, "데이터 없음");
	}
	
	@Test
	public void mngLectureReservationMapperTest() {

		//강연 등록
		MngLectureDTO mngLectureDTO = new MngLectureDTO();
		mngLectureDTO.setLectureHallId(1L);
		mngLectureDTO.setLecturerName("김용국");
		mngLectureDTO.setLectureTitle("Java 설명회");
		mngLectureDTO.setLectureContents("1.Java의 정의\n2.Java의 역사\n3.Java의 구성");
		mngLectureDTO.setLectureStartTime("2023-08-26 11:00:00");
		mngLectureDTO.setLectureEndTime("2023-08-26 12:00:00");
		mngLectureDTO.setLectureSeatsMax(50);
		
		int lectureInsertResult = mngLectureReservationMapper.insertLecture(mngLectureDTO);
		
		Assert.notNull(lectureInsertResult, "비정상 호출");
		Assert.isTrue(lectureInsertResult == 1, "등록 실패");
		
		// 강연 목록, 전체 카운트
		MngLectureReqDTO mngLectureReqDTO = new MngLectureReqDTO();
		mngLectureReqDTO.setPage(1);
		mngLectureReqDTO.setSize(15);
		mngLectureReqDTO.setStart((mngLectureReqDTO.getPage()-1)*mngLectureReqDTO.getSize());
		mngLectureReqDTO.setSearchText("1");
		mngLectureReqDTO.setSearchStartTime("2023-08-25 00:00:00");
		mngLectureReqDTO.setSearchEndTime("2023-08-27 23:59:59");

		List<MngLectureDTO> lectureList = mngLectureReservationMapper.selectLectureList(mngLectureReqDTO);
		Assert.notNull(lectureList, "비정상 호출");
		Assert.isTrue(lectureList.size() > 0, "데이터 없음");
		
		int lectureListCount = mngLectureReservationMapper.selectLectureListCount(mngLectureReqDTO);
		Assert.notNull(lectureListCount, "비정상 호출");
		Assert.isTrue(lectureListCount > 0, "데이터 없음");
		
		// 강연 목록 검색어 없음
		mngLectureReqDTO.setSearchText(null);

		lectureList = mngLectureReservationMapper.selectLectureList(mngLectureReqDTO);
		Assert.notNull(lectureList, "비정상 호출");
		Assert.isTrue(lectureList.size() > 0, "데이터 없음");
		
		lectureListCount = mngLectureReservationMapper.selectLectureListCount(mngLectureReqDTO);
		Assert.notNull(lectureListCount, "비정상 호출");
		Assert.isTrue(lectureListCount > 0, "데이터 없음");
		
		// 강연 상세 조회
		Long lectureId = 1L;
		
		MngLectureDTO lecture = mngLectureReservationMapper.selectLecture(lectureId);
		Assert.notNull(lecture, "비정상 호출");
		Assert.isTrue(lecture.getLectureId() > 0, "데이터 없음");
		
		// 강연 신청자 목록 조회
		lectureId = 10L;
		
		List<MngLectureRegistrationDTO> lectureRegistrationList = mngLectureReservationMapper.selectLectureReservationList(lectureId);
		Assert.notNull(lectureRegistrationList, "비정상 호출");
		Assert.isTrue(lectureRegistrationList.size() > 0, "데이터 없음");
	}

}
