package com.sreeny.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TODO")
@Component
@DynamicInsert
@DynamicUpdate
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "USER_ID", nullable = false)
	private String userId;
	
	@NotNull 
	@NotEmpty(message = "Please enter task Name.")
	@Column(name = "TASK_NAME", nullable = false)
	private String taskName;
	
	@Column(name = "DESCIPRIOTN", nullable = false)
	private String description;
	
	
	@NotNull
	@Column(name = "DUE_DATE", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dueDate;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", userId=" + userId + ", taskName=" + taskName + ", description=" + description
				+ ", dueDate=" + dueDate + ", status=" + status + "]";
	}
	
	
}
