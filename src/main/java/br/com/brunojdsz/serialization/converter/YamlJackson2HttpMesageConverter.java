package br.com.brunojdsz.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.util.List;

public class YamlJackson2HttpMesageConverter implements HttpMessageConverter<Object> {

    private YAMLMapper mapper;

    public void YamlHttpMessageConverter() {
        this.mapper = (YAMLMapper) new YAMLMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return MediaType.parseMediaType("application/x-yaml").includes(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return MediaType.parseMediaType("application/x-yaml").includes(mediaType);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.parseMediaType("application/x-yaml"));
    }

    @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
        return mapper.readValue(inputMessage.getBody(), clazz);
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException {
        mapper.writeValue(outputMessage.getBody(), o);
    }
}