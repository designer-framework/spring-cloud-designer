package org.designer.tools.web;

import org.apache.commons.io.IOUtils;
import org.designer.tools.exception.ToolsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @description:
 * @author: Designer
 * @date : 2022/1/4 22:35
 */
public class WebUtils {

    private static final String UNKNOWN_IP = "unknown";

    private WebUtils() {
    }

    /**
     * 导出文件
     *
     * @param fileName
     * @param fileExportFunction
     * @param <R>
     * @return
     * @throws IOException
     */
    public static <R> R export(String fileName, Function<OutputStream, R> fileExportFunction) {
        Assert.notNull(fileName, "fileName");
        HttpServletResponse response = getResponse();
        try {
            applyDownloadHeader(response, fileName);
            R result = fileExportFunction.apply(response.getOutputStream());
            return result;
        } catch (IOException e) {
            throw new ToolsException("文件[" + fileName + "]导出失败", e);
        }
    }

    public static void export(String fileName, Consumer<OutputStream> fileConsumer) {
        export(fileName, outputStream -> {
            fileConsumer.accept(outputStream);
            return null;
        });
    }

    public static void export(String fileName, InputStream inputStream) {
        export(fileName, outputStream -> {
            try {
                IOUtils.copy(inputStream, outputStream);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private static void applyDownloadHeader(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
    }


    public static String getClientIp() {
        HttpServletRequest request = getRequest();
        String ip = null;
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("X-Real-IP");
        }
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new IllegalStateException("request上下文未初始化");
        }
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes);
        } else {
            throw new IllegalStateException("不支持的上下文类型: " + requestAttributes.getClass().getName());
        }
    }

}
