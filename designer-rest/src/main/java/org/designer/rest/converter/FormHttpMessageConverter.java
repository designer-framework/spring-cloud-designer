package org.designer.rest.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.http.converter.*;
import org.springframework.lang.Nullable;
import org.springframework.util.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author designer [19901753334@163.com]
 * @since 2021/12/15 23:32
 */
public class FormHttpMessageConverter implements HttpMessageConverter<Object> {

    public static final Charset DEFAULT_CHARSET;
    private static final MediaType DEFAULT_FORM_DATA_MEDIA_TYPE;

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
        DEFAULT_FORM_DATA_MEDIA_TYPE = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, DEFAULT_CHARSET);
    }

    private List<MediaType> supportedMediaTypes = new ArrayList();
    private List<HttpMessageConverter<?>> partConverters = new ArrayList();
    private Charset charset;
    @Nullable
    private Charset multipartCharset;

    @Autowired
    private ObjectMapper objectMapper;

    public FormHttpMessageConverter() {
        charset = DEFAULT_CHARSET;
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
        supportedMediaTypes.add(MediaType.MULTIPART_MIXED);
        partConverters.add(new ByteArrayHttpMessageConverter());
        partConverters.add(new StringHttpMessageConverter());
        partConverters.add(new ResourceHttpMessageConverter());
        applyDefaultCharset();
    }

    public void addSupportedMediaTypes(MediaType... supportedMediaTypes) {
        Assert.notNull(supportedMediaTypes, "'supportedMediaTypes' must not be null");
        Assert.noNullElements(supportedMediaTypes, "'supportedMediaTypes' must not contain null elements");
        Collections.addAll(this.supportedMediaTypes, supportedMediaTypes);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.unmodifiableList(supportedMediaTypes);
    }

    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        Assert.notNull(supportedMediaTypes, "'supportedMediaTypes' must not be null");
        this.supportedMediaTypes = new ArrayList(supportedMediaTypes);
    }

    @Override
    public Object read(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        Map<String, Object> stringStringMap = objectMapper.convertValue(o, new TypeReference<Map<String, Object>>() {
        });
        stringStringMap.forEach(multiValueMap::add);
        writeForm(multiValueMap, mediaType, outputMessage);
    }

    public void setPartConverters(List<HttpMessageConverter<?>> partConverters) {
        Assert.notEmpty(partConverters, "'partConverters' must not be empty");
        this.partConverters = partConverters;
    }

    public void addPartConverter(HttpMessageConverter<?> partConverter) {
        Assert.notNull(partConverter, "'partConverter' must not be null");
        partConverters.add(partConverter);
    }

    public void setCharset(@Nullable Charset charset) {
        if (charset != this.charset) {
            this.charset = charset != null ? charset : DEFAULT_CHARSET;
            applyDefaultCharset();
        }

    }

    private void applyDefaultCharset() {
        Iterator var1 = partConverters.iterator();

        while (var1.hasNext()) {
            HttpMessageConverter<?> candidate = (HttpMessageConverter) var1.next();
            if (candidate instanceof AbstractHttpMessageConverter) {
                AbstractHttpMessageConverter<?> converter = (AbstractHttpMessageConverter) candidate;
                if (converter.getDefaultCharset() != null) {
                    converter.setDefaultCharset(charset);
                }
            }
        }

    }

    public void setMultipartCharset(Charset charset) {
        multipartCharset = charset;
    }

    @Override
    public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
        return true;
    }

    private boolean isMultipart(MultiValueMap<String, ?> map, @Nullable MediaType contentType) {
        if (contentType != null) {
            return contentType.getType().equalsIgnoreCase("multipart");
        } else {
            Iterator var3 = map.values().iterator();

            while (var3.hasNext()) {
                List<?> values = (List) var3.next();
                Iterator var5 = values.iterator();

                while (var5.hasNext()) {
                    Object value = var5.next();
                    if (value != null && !(value instanceof String)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private void writeForm(MultiValueMap<String, Object> formData, @Nullable MediaType contentType, HttpOutputMessage outputMessage) throws IOException {
        contentType = getFormContentType(contentType);
        outputMessage.getHeaders().setContentType(contentType);
        Charset charset = contentType.getCharset();
        Assert.notNull(charset, "No charset");
        byte[] bytes = serializeForm(formData, charset).getBytes(charset);
        outputMessage.getHeaders().setContentLength(bytes.length);
        if (outputMessage instanceof StreamingHttpOutputMessage) {
            StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage) outputMessage;
            streamingOutputMessage.setBody((outputStream) -> {
                StreamUtils.copy(bytes, outputStream);
            });
        } else {
            StreamUtils.copy(bytes, outputMessage.getBody());
        }

    }

    protected MediaType getFormContentType(@Nullable MediaType contentType) {
        if (contentType == null) {
            return DEFAULT_FORM_DATA_MEDIA_TYPE;
        } else {
            return contentType.getCharset() == null ? new MediaType(contentType, charset) : contentType;
        }
    }

    protected String serializeForm(MultiValueMap<String, Object> formData, Charset charset) {
        StringBuilder builder = new StringBuilder();
        formData.forEach((name, values) -> {
            if (name == null) {
                Assert.isTrue(CollectionUtils.isEmpty(values), "Null name in form data: " + formData);
            } else {
                values.forEach((value) -> {
                    try {
                        if (builder.length() != 0) {
                            builder.append('&');
                        }
                        builder.append(URLEncoder.encode(name, charset.name()));
                        if (value == null) {
                        } else if (value instanceof String) {
                            builder.append('=');
                            builder.append(URLEncoder.encode(String.valueOf(value), charset.name()));
                        } else if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
                            builder.append('=');
                            builder.append(URLEncoder.encode(String.valueOf(value), charset.name()));
                        } else {
                            builder.append('=');
                            builder.append(URLEncoder.encode(objectMapper.writeValueAsString(value), charset.name()));
                        }
                    } catch (UnsupportedEncodingException | JsonProcessingException var5) {
                        throw new IllegalStateException(var5);
                    }
                });
            }
        });
        return builder.toString();
    }


}
