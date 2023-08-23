package dsoup.reservation.lecture.employee.reservation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dsoup.reservation.lecture.config.ResponseCode;
import dsoup.reservation.lecture.employee.reservation.dto.EmpEmployeeDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureRegistrationReqDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureReqDTO;
import dsoup.reservation.lecture.employee.reservation.mapper.EmpLectureReservationMapper;

/**
 * 강연 예약 직원 Service
 */
@Service
public class EmpLectureReservationService {

	@Autowired
	private EmpLectureReservationMapper mapper;
	
	/**
	 * 사번 확인
	 */
	public EmpEmployeeDTO getEmployeeId(String employeeNumber) {
		return mapper.selectEmployee(employeeNumber);
	};

	/**
	 * 강연 목록
	 */
	public Map<String, Object> getLectureList(EmpLectureReqDTO req) {
		Map<String, Object> result = new HashMap<String, Object>();

		int totalCount = mapper.selectLectureListCount(req);
		result.put("totalCount", totalCount);
		if (totalCount > 0) {
			result.put("list", mapper.selectLectureList(req));
		} else {
			result.put("list", null);
		}
		return result;
	};

	/**
	 * 강연 신청
	 */
	@Transactional
	public ResponseCode registLectureReservation(EmpLectureRegistrationReqDTO req) {

		EmpLectureDTO lecture = mapper.selectLectureReservationYn(req);
		if (lecture.getReservationYn() > 0) {
			return ResponseCode.DUPLICATE_DATA;
		}
		if (lecture.getReservationCount() >= lecture.getLectureSeatsMax()) {
			return ResponseCode.OVER_LIMIT;
		}
		int result = mapper.insertLectureReservation(req);
		if (result == 0) {
			return ResponseCode.NO_DATA;
		}

		return ResponseCode.NO_ERROR;
	};

	/**
	 * 강연 신청 목록
	 */
	public Map<String, Object> getLectureReservationList(EmpLectureRegistrationReqDTO req) {
		// 인증으로 사번이 사전에 setting 됨
		Map<String, Object> result = new HashMap<String, Object>();
		int totalCount = mapper.selectLectureReservationListCount(req);
		result.put("totalCount", totalCount);
		if (totalCount > 0) {
			result.put("list", mapper.selectLectureReservationList(req));
		} else {
			result.put("list", null);
		}
		return result;
	};

	/**
	 * 강연 신청 취소
	 */
	@Transactional
	public ResponseCode removeLectureReservation(EmpLectureRegistrationReqDTO req) {
		int result = mapper.cancelLectureReservation(req);
		if (result == 0) {
			return ResponseCode.NO_DATA;
		}
		return ResponseCode.NO_ERROR;
	};

	/**
	 * 실시간 인기 강연
	 */
	public Map<String, Object> getPopularLectureList(EmpLectureReqDTO req) {
		Map<String, Object> result = new HashMap<String, Object>();
		int totalCount = mapper.selectPopularLectureListCount(req);
		result.put("totalCount", totalCount);
		if (totalCount > 0) {
			result.put("list", mapper.selectPopularLectureList(req));
		} else {
			result.put("list", null);
		}

		return result;
	};
}
