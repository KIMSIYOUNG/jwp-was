package http.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpResponseTest {
    private HttpResponse httpResponse;

    @BeforeEach
    public void setUp() {
        httpResponse = new ResponseHandler().create();
    }

    @Test
    public void putHeaderTest() {
        httpResponse.putHeader("Connection", "keep-alive");

        assertThat(httpResponse.toString()).contains("Connection: keep-alive");
    }

    @Test
    public void setResponseBodyTest() {
        String body = "HELLO!";
        httpResponse.setResponseBody(body.getBytes(), "test.html");

        assertThat(httpResponse.getBody()).isEqualTo(body.getBytes());
        assertThat(httpResponse.toString()).contains("Content-Type: text/html;charset=utf-8");
    }

    @Test
    public void redirectTest() {
        httpResponse.redirect("index.html");

        assertThat(httpResponse.toString()).contains("302 FOUND");
        assertThat(httpResponse.toString()).contains("Location: index.html");
    }
}