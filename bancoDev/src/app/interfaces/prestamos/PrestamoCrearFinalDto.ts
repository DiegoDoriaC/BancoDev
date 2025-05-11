export interface PrestamoCrearFinalDto {
  idPrestamo: number,  
  sueldoCliente: string,
  montoSolicitado: number,
  plazoMeses: PlazoMeses,
  empleadoId: number,
  clienteId: number,
  socreCrediticio: number
}

export enum PlazoMeses {
  TRES = 3,
  SEIS = 6,
  DOCE = 12,
  TREINTA_Y_DOS = 32,
  SESENTA = 60,
}
