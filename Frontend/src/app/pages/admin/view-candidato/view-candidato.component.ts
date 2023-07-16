import { Component, OnInit } from '@angular/core';
import { CandidatoServiceService } from 'src/app/services/candidato.service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-candidato',
  templateUrl: './view-candidato.component.html',
  styleUrls: ['./view-candidato.component.css']
})

export class ViewCandidatoComponent implements OnInit{

  candidatos:any = [];
  filtro: string = ''; //filtro da barra de pesquisa pelo nome

  constructor(private candidatoService: CandidatoServiceService) {}

  //chama o método de listar todos os candidatos ao iniciar a página
  ngOnInit(): void {
    this.listarCandidatos()
  }

  // método para listar os candidatos atráves do service do candidato
  listarCandidatos() {
    this.candidatoService.listarCandidatos().subscribe(
      (dados:any) => {
        this.candidatos = dados;
      },
      (error) => {
        console.log(error);
        Swal.fire('Problema!','Erro ao carregar os candidatos', 'error')
      }
    )
  }

  //filtro para mostrar os candidatos pelo nome
  filtrarCandidatos(): void {
    if (!this.filtro) {
      this.listarCandidatos();
      return;
    }

    this.candidatos = this.candidatos.filter((candidato: any) =>
      candidato.nome.toLowerCase().includes(this.filtro.toLowerCase())
    );
  }

  // método para remover um candidato pelo ID
  removerCandidato(candidatoID: any) {
    Swal.fire({
      title: 'Eliminar candidato ',
      text: 'Tem certeza ?', icon: 'warning', showCancelButton:true,
      confirmButtonColor: '#3096d6', cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar' ,
      cancelButtonText: 'Cancelar'

    }).then((result) => {
      if(result.isConfirmed){
        this.candidatoService.removerCandidato(candidatoID).subscribe(
          (data) => {
            this.candidatos = this.candidatos.filter((candidato:any) => candidato.cadidatoID != candidatoID);
            Swal.fire('Candidato eliminado','Desativado com sucesso!','success');
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
