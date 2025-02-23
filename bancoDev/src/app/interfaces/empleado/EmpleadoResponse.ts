export interface EmpleadoResponse {
  id: number,
  nombre: string,
  apellido: string,
  correo: string,
  foto: string,
  rol: Roles
}

export enum Roles {
  CAJERO,
  ANALISTA
}
