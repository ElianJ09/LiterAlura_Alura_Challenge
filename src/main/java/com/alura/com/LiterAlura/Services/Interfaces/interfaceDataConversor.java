package com.alura.com.LiterAlura.Services.Interfaces;

import java.util.List;

public interface interfaceDataConversor {
    <data> data obtainData(String json, Class<data> dataClass);

    <data> List<data> obtainArrayData(String json, Class<data> dataClass);
}
