package com.vanskarner.remotedata;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

class MovieDeserializer implements JsonDeserializer<MovieDTO> {

    private final String baseImageUrl;
    private final Gson gson;

    public MovieDeserializer(String baseImageUrl, Gson gson) {
        this.baseImageUrl = baseImageUrl;
        this.gson = gson;
    }

    @Override
    public MovieDTO deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context)
            throws JsonParseException {
        MovieDTO detailDTO = gson.fromJson(json.getAsJsonObject(), MovieDTO.class);
        detailDTO.posterPath = baseImageUrl.concat(getImagePath(detailDTO.posterPath));
        detailDTO.backdropPath = baseImageUrl.concat(getImagePath(detailDTO.backdropPath));
        return detailDTO;
    }

    private String getImagePath(String imagePath) {
        return imagePath == null ? "" : imagePath;
    }

}