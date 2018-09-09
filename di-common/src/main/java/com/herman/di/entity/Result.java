package com.herman.di.entity;

/**
 * 返回体封装
 *
 * @author hsh
 * @create 2018-08-29 16:37
 **/
public class Result<T> {
    public final static Integer SUCESS = 200;
    public final static Integer ERROR = 400;


    private Integer status;
    private String message;
    private T result;


    public Result(Integer status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public Result() {
        this.status = SUCESS;
        this.message = Message.SUCESS.description;
    }

    public static Result returnError(String message) {
        return new Result(ERROR, message, null);
    }

    public static Result resultSucess() {
        return new Result();
    }

    public static Result returnOperateFail() {
        return new Result(ERROR, Message.ERROR_OPERATE_FAIL.description, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static enum Message {
        SUCESS(0, "操作成功"),
        ERROR_OPERATE_FAIL(0, "操作失败");

        private Integer code;
        private String description;

        Message(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public Result.Message customDescription(String customError) {
            if (customError != null) {
                this.description = customError;
            }
            return this;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
