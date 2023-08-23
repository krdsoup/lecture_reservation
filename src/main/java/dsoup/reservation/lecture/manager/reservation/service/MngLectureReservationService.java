package dsoup.reservation.lecture.manager.reservation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import dsoup.reservation.lecture.manager.reservation.dto.MngLectureDTO;
import dsoup.reservation.lecture.manager.reservation.dto.MngLectureReqDTO;
import dsoup.reservation.lecture.manager.reservation.mapper.MngLectureReservationMapper;

/**
 * 강연 예약 직원 Service
 */
@Service
public class MngLectureReservationService {

	@Autowired
	private MngLectureReservationMapper mapper;

	/**
	 * 강연 목록
	 */
	public Map<String, Object> getLectureList(MngLectureReqDTO req) {
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
	 * 강연 등록
	 */
	@Transactional
	public int registLecture(MngLectureDTO req) {
		return mapper.insertLecture(req);
	};

	/**
	 * 강연 상세 정보, 신청자 목록
	 */
	public Map<String, Object> getLecture(Long lectureId) {
		ObjectMapper objectMapper = new ObjectMapper();
		MngLectureDTO resultDTO = mapper.selectLecture(lectureId);
		Map<String, Object> result = objectMapper.convertValue(resultDTO, Map.class);
		if (result != null) {
			result.put("list", mapper.selectLectureReservationList(lectureId));
		}
		return result;
	};
}
