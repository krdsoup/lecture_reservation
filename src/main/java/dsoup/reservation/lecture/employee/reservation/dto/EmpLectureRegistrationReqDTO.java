package dsoup.reservation.lecture.employee.reservation.dto;

import dsoup.reservation.lecture.dto.PagingDTO;
import jakarta.validation.constraints.Size;

public class EmpLectureRegistrationReqDTO extends PagingDTO {

	private Long lectureId;
	private Long employeeId;
	
	@Size(min = 5, max = 5)
	private String employeeNumber;

	public Long getLectureId() {
		return lectureId;
	}

	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

}
