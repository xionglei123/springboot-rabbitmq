package org.yh.result;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String message;
	private Object data;


}