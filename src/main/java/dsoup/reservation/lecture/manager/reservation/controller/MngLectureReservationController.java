package dsoup.reservation.lecture.manager.reservation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dsoup.reservation.lecture.config.Response;
import dsoup.reservation.lecture.config.ResponseCode;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureDTO;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureReqDTO;
import dsoup.reservation.lecture.manager.reservation.service.MngLectureReservationService;

/**
 * 강연 예약 직원 API
 */
@RequestMapping("/mng")
@RestController
public class MngLectureReservationController {

	@Autowired
	private MngLectureReservationService service;

	/**
	 * 강연 목록
	 */
	@PostMapping("/lecture/list")
	public Response getLectureList(@RequestBody MngLectureReqDTO req) {

		// validate
		req.setStart((req.getPage() - 1) * req.getSize());

		// 강연 목록 조회
		Map<String, Object> data = service.getLectureList(req);

		return new Response(data);
	};

	/**
	 * 강연 등록
	 */
	@PostMapping("/lecture")
	public Response registLecture(@RequestBody MngLectureDTO req) {

		// validate
		// header, access_token 등 인증정보를 통한 ID 확인
		req.setEmployeeId(null);

		// 강연 등록
		int result = service.registLecture(req);
		if (result == 0) {
			return new Response(ResponseCode.BAD_REQUEST);
		}

		return new Response(ResponseCode.NO_ERROR);
	};

	/**
	 * 강연 신청자 목록
	 */
	@GetMapping("/lecture/reservation/list")
	public Response getLectureReservationList(@RequestParam(defaultValue="0") Long lectureId) {

		// validate

		// 강연 신청 목록 조회
		Map<String, Object> data = service.getLecture(lectureId);

		return new Response(data);
	};

}
