import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VagaService } from 'src/app/services/vaga.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-atualizar-vaga',
  templateUrl: './atualizar-vaga.component.html',
  styleUrls: ['./atualizar-vaga.component.css']
})
export class AtualizarVagaComponent implements OnInit{

  constructor(private vagaService: VagaService,
    private route: ActivatedRoute,
    private router: Router) {}

    vagaID = 1;
    vaga: any;

  ngOnInit(): void {
    this.vagaID = this.route.snapshot.params['vagaID']

    this.vagaService.obterVagaID(this.vagaID).subscribe(
      (dados:any) => {
        this.vaga = dados;
        console.log(dados);

      }, (error) => {
        console.log(error);

      }
    )
  }

  public atualizarVaga() {
    if (this.vaga && this.vaga.vagaID) {
      this.vagaService.atualizarVaga(this.vaga).subscribe(
        (data:any) => {
          Swal.fire('Dados da vaga', 'Atualizados com sucesso', 'success').then(
            (c) => {
              this.router.navigate(['/admin/vagas']);
              console.log(c);
              console.log(data);
            }
          );
        },
        (error) => {
          Swal.fire('Erro', 'Não foi possível atualizar', 'error');
          console.log(error);
        }
      );
    } else {
      Swal.fire('Erro', 'Não foi possível encontrar o candidato!', 'error');
    }
  }

}
