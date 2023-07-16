import Swal from 'sweetalert2';
import { Component, OnInit } from '@angular/core';
import { VagaService } from 'src/app/services/vaga.service';
import { retry } from 'rxjs';

@Component({
  selector: 'app-view-vaga',
  templateUrl: './view-vaga.component.html',
  styleUrls: ['./view-vaga.component.css']
})

export class ViewVagaComponent implements OnInit{

  vagas: any = [];
  filtro: string  = '';  //filtro da barra de pesquisa pelo cargo

  constructor(private vagaService: VagaService) {}

  //chama o método de listar todas as vagas ao iniciar a página
  ngOnInit(): void {
    this.listarVagas()
  }

  // método para listar as vagas atráves do service de vagas
  listarVagas() {
    this.vagaService.listarVagas().subscribe(
      (dados: any) => {
        this.vagas = dados;
      },
      (error) => {
        Swal.fire('Problema!','Erro ao carregar as vagas', 'error')
        console.log(error);
      }
    )
  }

   //filtro para mostrar os vagas pelo cargo
  filtrarVagas() {
    if (!this.filtro) {
      this.listarVagas();
      return;
    }

    this.vagas = this.vagas.filter((vaga: any) =>
    vaga.cargo.toLowerCase().includes(this.filtro.toLowerCase())
    );
  }

  // remove a vaga pelo seu ID
  removerVaga(vagaID: any) {
    Swal.fire({
      title: 'Eliminar vaga ',
      text: 'Tem certeza ?', icon: 'warning', showCancelButton:true,
      confirmButtonColor: '#3096d6', cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar' ,
      cancelButtonText: 'Cancelar'

    }).then((result) => {
      if(result.isConfirmed){
        this.vagaService.removerVaga(vagaID).subscribe(  //chama o service para remover a vaga pelo ID
          (data) => {
            this.vagas = this.vagas.filter((vagas:any) => vagas.vagaID != vagaID); //filtro para pe
            Swal.fire('Vaga eliminada','Removida com sucesso!','success');
            console.log(data);

          },
          (error) => {
            Swal.fire('Error','Exception','error');
            console.log(error);

          }
        )
      }
    })
  }

}
