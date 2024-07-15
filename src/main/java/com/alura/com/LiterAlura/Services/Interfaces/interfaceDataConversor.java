package com.alura.com.LiterAlura.Services.Interfaces;

import java.util.List;

public interface interfaceDataConversor {
    <T> T obtainData(String json, Class<T> dataClass);

    <T> List<T> obtainArrayData(String json, Class<T> dataClass);
}
