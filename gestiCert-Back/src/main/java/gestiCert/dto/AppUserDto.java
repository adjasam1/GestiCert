package gestiCert.dto;

import java.util.List;

import gestiCert.model.Role;

public class AppUserDto {
	
	private Integer id;
	
	private String idRHUser;
//	private String username;
	
	private List<Role> roleList;

	public AppUserDto() {
	}

	public AppUserDto(String idRHUser) {
		super();
		this.idRHUser = idRHUser;
	}

	public AppUserDto(String idRHUser, List<Role> roleList) {
		super();
		this.idRHUser = idRHUser;
		this.roleList = roleList;
	}
	
//	public AppUserDto(String username) {
//		this.username = username;
//	}
//
//	public AppUserDto(String username, List<Role> roleList) {
//		this.username = username;
//		this.roleList = roleList;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdRHUser() {
		return idRHUser;
	}

	public void setIdRHUser(String idRHUser) {
		this.idRHUser = idRHUser;
	}
	
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
