package tech.ynfy.frame.module;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 *   接口返回数据格式
 * @author scott
 * @email jeecgos@163.com
 * @date  2019年1月19日
 */
@Data
@Tag(name = "接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**2
	 * 成功标志
	 */
	@Schema(description = "成功标志")
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	@Schema(description = "返回处理消息")
	private String message = "操作成功！";

	/**
	 * 返回代码
	 */
	@Schema(description = "返回代码")
	private Integer code = 0;

	/**
	 * 返回数据对象 data
	 */
	@Schema(description = "返回数据对象")
	private T result = (T) new Object();

	/**
	 * 时间戳
	 */
	@Schema(description = "时间戳")
	private long timestamp = System.currentTimeMillis();

	public Result() {

	}


	public static <T> Result<T> ok() {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(HttpStatus.OK.value());
		r.setMessage("操作成功");
		return r;
	}

	public static <T> Result<T> ok(String msg) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(HttpStatus.OK.value());
		r.setMessage(msg);
		return r;
	}

	public static <T> Result<T> ok(T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(HttpStatus.OK.value());
		r.setResult(data);
		return r;
	}

	public static <T> Result<T> ok(T data,String msg) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(HttpStatus.OK.value());
		r.setResult(data);
		r.setMessage(msg);
		return r;
	}

	public static <T> Result<T> authExpire() {
		return error(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value(), "用户授权失效! 请重新登录");
	}

	public static <T> Result<T> error(String msg) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}

	public static <T> Result<T> error(T data,String msg) {
		Result<T> r = new Result<T>();
		r.setSuccess(false);
		r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		r.setResult(data);
		r.setMessage(msg);
		return r;
	}

	public static <T> Result<T> error(Integer code, String msg) {
		Result<T> r = new Result<T>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}
}
