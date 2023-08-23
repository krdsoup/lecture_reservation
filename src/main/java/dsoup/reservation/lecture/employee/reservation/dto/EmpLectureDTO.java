package dsoup.reservation.lecture.employee.reservation.dto;

public class EmpLectureDTO {

	private Long lectureId;
	private Long lectureHallId;
	private String lectureHallName;
	private String lecturerName;
	private String lectureTitle;
	private String lectureContents;
	private String lectureStartTime;
	private String lectureEndTime;
	private Integer lectureSeatsMax;
	private Integer reservationCount;
	private Integer reservationYn;

	public Long getLectureId() {
		return lectureId;
	}

	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}

	public Long getLectureHallId() {
		return lectureHallId;
	}

	public void setLectureHallId(Long lectureHallId) {
		this.lectureHallId = lectureHallId;
	}

	public String getLectureHallName() {
		return lectureHallName;
	}

	public void setLectureHallName(String lectureHallName) {
		this.lectureHallName = lectureHallName;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public String getLectureTitle() {
		return lectureTitle;
	}

	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}

	public String getLectureContents() {
		return lectureContents;
	}

	public void setLectureContents(String lectureContents) {
		this.lectureContents = lectureContents;
	}

	public String getLectureStartTime() {
		return lectureStartTime;
	}

	public void setLectureStartTime(String lectureStartTime) {
		this.lectureStartTime = lectureStartTime;
	}

	public String getLectureEndTime() {
		return lectureEndTime;
	}

	public void setLectureEndTime(String lectureEndTime) {
		this.lectureEndTime = lectureEndTime;
	}

	public Integer getLectureSeatsMax() {
		return lectureSeatsMax;
	}

	public void setLectureSeatsMax(Integer lectureSeatsMax) {
		this.lectureSeatsMax = lectureSeatsMax;
	}

	public Integer getReservationCount() {
		return reservationCount;
	}

	public void setReservationCount(Integer reservationCount) {
		this.reservationCount = reservationCount;
	}

	public Integer getReservationYn() {
		return reservationYn;
	}

	public void setReservationYn(Integer reservationYn) {
		this.reservationYn = reservationYn;
	}

}
