package assignment.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	private String id;
	private String password;
	private String fullname;
	private Date birthday;
	private boolean gender;
	private String mobile;
	private String email;
	private boolean role;
}
