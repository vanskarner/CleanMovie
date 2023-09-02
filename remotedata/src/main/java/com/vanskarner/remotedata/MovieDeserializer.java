package com.vanskarner.remotedata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

class MovieDeserializer implements JsonDeserializer<MovieDTO> {
    private final String baseImageUrl;

    public MovieDeserializer(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    @Override
    public MovieDTO deserialize(JsonElement json,
                                Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return new MovieDTO(
                jsonObject.get("id").getAsInt(),
                getStringValue(jsonObject, "overview"),
                baseImageUrl.concat(getStringValue(jsonObject, "poster_path")),
                baseImageUrl.concat(getStringValue(jsonObject, "backdrop_path")),
                getStringValue(jsonObject, "release_date"),
                getStringValue(jsonObject, "title"),
                jsonObject.get("vote_count").getAsInt(),
                jsonObject.get("vote_average").getAsFloat());
    }

    private String getStringValue(JsonObject jsonObject, String key) {
        JsonElement element = jsonObject.get(key);
        return element.isJsonNull() ? "" : element.getAsString();
    }

}