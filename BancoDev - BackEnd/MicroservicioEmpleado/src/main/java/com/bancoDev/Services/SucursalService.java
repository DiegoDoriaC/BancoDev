package com.bancoDev.Services;

import java.util.List;

import com.bancoDev.DTOs.ApiResponse;
import com.bancoDev.Models.SucursalEntity;

public interface SucursalService {

    ApiResponse<List<SucursalEntity>> listadoSucursales();    

}
