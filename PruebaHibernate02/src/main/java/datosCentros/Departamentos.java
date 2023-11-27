package datosCentros;
// Generated 27 nov 2023 19:57:29 by Hibernate Tools 5.6.15.Final

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos implements java.io.Serializable {

	private BigInteger deptNo;
	private String dnombre;
	private String loc;
	private Set empleadoses = new HashSet(0);

	public Departamentos() {
	}

	public Departamentos(BigInteger deptNo) {
		this.deptNo = deptNo;
	}

	public Departamentos(BigInteger deptNo, String dnombre, String loc, Set empleadoses) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
		this.empleadoses = empleadoses;
	}

	public BigInteger getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(BigInteger deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set empleadoses) {
		this.empleadoses = empleadoses;
	}

}
