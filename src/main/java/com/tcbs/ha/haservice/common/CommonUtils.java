package com.tcbs.ha.haservice.common;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.EqualsAndHashCode.Exclude;

public class CommonUtils {
  private static Gson gson;

  public static Gson getGSonConverter() {
    if (gson == null) {
      ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
          return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
          return field.getAnnotation(Exclude.class) != null;
        }
      };

      gson = new GsonBuilder()
          .addSerializationExclusionStrategy(strategy)
          .create();
    }
    return gson;
  }
}
