import cn.hutool.http.HttpResponse;
import org.junit.Test;

public class HttpRequest {

    @Test
    public void test(){
        //链式构建请求
        HttpResponse responseForward = cn.hutool.http.HttpRequest.post("https://spfintech.sains.com.my/xservice/UnionPayTransactionAction.KYCVerification.do")
                //银联定义 的 机构ID/钱包ID
                .header("UPI-JWS", "request UPI-JWS message")
                .header("Content-Type", "application/json;charset=UTF-8")
                //表单内容
                .body("request BODY message")
                //超时，毫秒
                .timeout(900000).execute();
        String upi_jws = responseForward.header("UPI-JWS");
        String body = responseForward.body();
        System.out.println("upi_jws:" + upi_jws);
        System.out.println("body:" + body);
    }
}

