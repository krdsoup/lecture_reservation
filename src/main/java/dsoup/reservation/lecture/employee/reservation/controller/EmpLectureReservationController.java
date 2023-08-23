package dsoup.reservation.lecture.employee.reservation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dsoup.reservation.lecture.config.Response;
import dsoup.reservation.lecture.config.ResponseCode;
import dsoup.reservation.lecture.employee.reservation.dto.EmpEmployeeDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureRegistrationReqDTO;
import dsoup.reservation.lecture.employee.reservation.dto.EmpLectureReqDTO;
import dsoup.reservation.lecture.employee.reservation.service.EmpLectureReservationService;

/**
 * 강연 예약 직원 API
 */
@RequestMapping("/emp/lecture")
@RestController
public class EmpLectureReservationController {

	@Autowired
	private EmpLectureReservationService service;

	/**
	 * 강연 목록
	 */
	@PostMapping("/list")
	public Response getLectureList(@RequestBody EmpLectureReqDTO req) {

		// validate

		// 강연 목록 조회
		Map<String, Object> data = service.getLectureList(req);

		return new Response(data);
	};

	/**
	 * 강연 신청
	 */
	@PostMapping("/reservation")
	public Response registLectureReservation(@RequestBody EmpLectureRegistrationReqDTO req) {

		// validate
		// header, access_token 등 인증정보를 통한 ID 확인
		EmpEmployeeDTO employee = service.getEmployeeId(req.getEmployeeNumber());
		if(employee == null || employee.getEmployeeId() == null) {
			return new Response(ResponseCode.AUTHORIZED_REQUIRED);
		}
		req.setEmployeeId(employee.getEmployeeId());

		// 강연 신청
		ResponseCode result = service.registLectureReservation(req);

		return new Response(result);
	};

	/**
	 * 강연 신청 목록
	 */
	@PostMapping("/reservation/list")
	public Response getLectureReservationList(@RequestBody EmpLectureRegistrationReqDTO req) {

		// validate
		// header, access_token 등 인증정보를 통한 ID 확인
		EmpEmployeeDTO employee = service.getEmployeeId(req.getEmployeeNumber());
		if(employee == null || employee.getEmployeeId() == null) {
			return new Response(ResponseCode.AUTHORIZED_REQUIRED);
		}
		req.setEmployeeId(employee.getEmployeeId());

		// 강연 신청 목록 조회
		Map<String, Object> data = service.getLectureReservationList(req);

		return new Response(data);
	};

	/**
	 * 강연 신청 취소
	 */
	@DeleteMapping("/reservation")
	public Response removeLectureReservation(@RequestBody EmpLectureRegistrationReqDTO req) {
		// validate
		// header, access_token 등 인증정보를 통한 ID 확인
		EmpEmployeeDTO employee = service.getEmployeeId(req.getEmployeeNumber());
		if(employee == null || employee.getEmployeeId() == null) {
			return new Response(ResponseCode.AUTHORIZED_REQUIRED);
		}
		req.setEmployeeId(employee.getEmployeeId());

		// 강연 신청 취소
		ResponseCode result = service.removeLectureReservation(req);

		return new Response(result);
	};

	/**
	 * 실시간 인기 강연
	 */
	@PostMapping("/popular/list")
	public Response getPopularLectureList(@RequestBody EmpLectureReqDTO req) {
		// validate

		// 강연 신청 목록 조회
		Map<String, Object> data = service.getPopularLectureList(req);

		return new Response(data);
	};
}
