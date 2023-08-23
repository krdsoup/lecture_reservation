package dsoup.reservation.lecture.employee.reservation.dto;

public class EmpEmployeeDTO {

	private Long employeeId;
	private String employeeNumber;
	private String name;
	private int autority;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAutority() {
		return autority;
	}

	public void setAutority(int autority) {
		this.autority = autority;
	}

}
