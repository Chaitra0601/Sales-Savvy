package com.example.demo.Entitys;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(length = 225)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // admin / customer

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at  = LocalDateTime.now();

	/**
	 * @param userid
	 * @param username
	 * @param email
	 * @param password
	 * @param role
	 * @param createdAt
	 * @param updatedAt
	 */
    public User() {
    }

	/**
	 * @param userid
	 * @param username
	 * @param email
	 * @param password
	 * @param role
	 * @param created_at
	 * @param updated_at
	 */
	public User(Integer userid, String username, String email, String password, Role role, LocalDateTime created_at,
			LocalDateTime updated_at) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param role
	 * @param created_at
	 * @param updated_at
	 */
	public User(String username, String email, String password, Role role, LocalDateTime created_at,
			LocalDateTime updated_at) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	
}
