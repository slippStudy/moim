package net.slipp.ddd.events;


import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Serializer {

    private static final String EMPTY = "";
    private static Serializer serializer;
    private Gson gson;

    public static synchronized Serializer getInstance() {
        if (Serializer.serializer == null) {
            Serializer.serializer = new Serializer();
        }
        return Serializer.serializer;
    }

    private Serializer() {
        this.build();
    }


    public String serialize(Object object) {
        if(object == null) {
            return EMPTY;
        }
        if(EMPTY.equals(object)) {
            return EMPTY;
        }

        if(object instanceof String) {
            return (String) object;
        }

        return this.gson().toJson(object);
    }

    public <T> T deserialize(String aSerialization, final Class<T> aType) {
        return this.gson().fromJson(aSerialization, aType);
    }

    protected Gson gson() {
        return this.gson;
    }

    private void build() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .serializeNulls()
                .create();
    }


    private static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }

    private static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_DATE_TIME);
        }
    }


    private static class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date source, Type typeOfSource, JsonSerializationContext context) {
            return new JsonPrimitive(Long.toString(source.getTime()));
        }
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        public Date deserialize(JsonElement json, Type typeOfTarget, JsonDeserializationContext context) throws JsonParseException {
            long time = Long.parseLong(json.getAsJsonPrimitive().getAsString());
            return new Date(time);
        }
    }
}
