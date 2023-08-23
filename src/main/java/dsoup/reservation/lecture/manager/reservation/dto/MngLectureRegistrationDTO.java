package dsoup.reservation.lecture.manager.reservation.dto;

public class MngLectureRegistrationDTO {

	private Long lectureRegistrationId;
	private Long lectureId;
	private Long employeeId;
	private String employeeName;
	private String employeeNumber;
	private String createTime;
	private String updateTime;

	public Long getLectureRegistrationId() {
		return lectureRegistrationId;
	}

	public void setLectureRegistrationId(Long lectureRegistrationId) {
		this.lectureRegistrationId = lectureRegistrationId;
	}

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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
