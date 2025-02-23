export interface PagoResponse {
  id: number,
  fechaPagoEstimada: string,
  fechaPagoRealizada: string,
  montoPagado: number,
  estado: string,
  nombreEmpleado: string,
  prestamoId: number,
}
