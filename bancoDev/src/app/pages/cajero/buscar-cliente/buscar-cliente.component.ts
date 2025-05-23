import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteResponse } from 'src/app/interfaces/cliente/ClienteResponse';
import { ClienteService } from 'src/app/services/cliente.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-buscar-cliente',
  templateUrl: './buscar-cliente.component.html',
  styleUrls: ['./buscar-cliente.component.css']
})
export class BuscarClienteComponent implements OnInit {

  public clienteEncontrado?: ClienteResponse;
  public mensaje: string = '' ;

  public formularioConsultarDni = new FormGroup({
    dni: new FormControl('', [Validators.required, Validators.pattern('^\d{8}$')])
  })

  constructor(
    private _cliente: ClienteService,
    private _router: Router,
  ) {}


  ngOnInit(): void {

  }

  onSubmit(){
    const dni = this.formularioConsultarDni.get('dni')?.value ?? '';
      this.obtenerClientePorDni(dni);
  }

  obtenerClientePorDni(dni: string){
    this._cliente.buscarPorDni(dni).subscribe(data => {
      if(data.status){
        this.clienteEncontrado = data.data;
        this._router.navigate(['/cajero/listarPrestamo', data.data.id])
      }
      else{
        Swal.fire({
          title: 'Opps!',
          text: data.message,
          icon: 'info'
        })
      }
      this.mensaje = data.message;
    })
  }





}
