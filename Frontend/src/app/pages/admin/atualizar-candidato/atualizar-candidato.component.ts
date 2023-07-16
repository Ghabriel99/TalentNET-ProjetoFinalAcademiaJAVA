import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidatoServiceService } from 'src/app/services/candidato.service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-atualizar-candidato',
  templateUrl: './atualizar-candidato.component.html',
  styleUrls: ['./atualizar-candidato.component.css']
})

export class AtualizarCandidatoComponent implements OnInit{

  constructor (private candidatoService: CandidatoServiceService,
    private route: ActivatedRoute,
    private router: Router
    ) {}

  candidatoID = 1;
  candidato: any;

  ngOnInit(): void {
    this.candidatoID = this.route.snapshot.params['candidatoID']; //pegando o candidatoID

    this.candidatoService.obterCandidatoID(this.candidatoID).subscribe( //pegando o candidatoID pelo método do service
      (data:any) => {
        this.candidato = data; //dados do candidato
      },
      (error) => {
        console.log(error);
      }
    )
  }

  public atualizarDados() {
    if (this.candidato && this.candidato.candidatoID) {
      this.candidatoService.atualizarCandidato(this.candidato).subscribe(
        (data:any) => {
          console.log(data);
          Swal.fire('Dados do candidato', 'Atualizados com sucesso', 'success').then(
            (c) => {
              console.log(c);

              this.router.navigate(['/admin/candidatos']);
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
