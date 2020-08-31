package work.aijiu.common.utils;

import lombok.Data;

/**
 * @ClassName BlogJSONResult
 * @Description 自定义响应数据格（接口）
 *              200：表示成功
 *              500：表示错误
 *              501：bean验证错误
 *              502：拦截器拦截用户taken出错
 *              503：不具备角色功能
 *              505：异常抛出信息
 * @Author liuhongjian
 * @Date 2020/3/11 16:03
 * @Version 1.0
 **/
public class CarJSONResult {

    /**
     * 响应码
     */
    private Integer status;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应的数据
     */
    private Object data;

    /**
     *
     */
    private String ok;

    public static CarJSONResult build(Integer status,String msg,Object data){
        return new CarJSONResult(status,msg,data);
    }
    public static CarJSONResult ok(Object data){
        return new CarJSONResult(data);
    }
    public static CarJSONResult ok(){
        return new CarJSONResult(null);
    }
    public static CarJSONResult errorMsg(String msg){
        return new CarJSONResult(500,msg,null);
    }
    public static CarJSONResult errorMap(Object data){
        return new CarJSONResult(501,"error",data);
    }
    public static CarJSONResult errorTokenMsg(String msg){
        return new CarJSONResult(502,msg,null);
    }
    public static CarJSONResult errorRolesMsg(String msg){
        return new CarJSONResult(503,msg,null);
    }
    public static CarJSONResult errorException(String msg){
        return new CarJSONResult(555,msg,null);
    }

    public CarJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CarJSONResult() {
    }

    public CarJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOk(){
        return this.status ==200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

}
