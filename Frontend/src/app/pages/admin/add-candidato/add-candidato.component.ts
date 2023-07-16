import { Component, OnInit, NgModule } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CandidatoServiceService } from 'src/app/services/candidato.service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-candidato',
  templateUrl: './add-candidato.component.html',
  styleUrls: ['./add-candidato.component.css']
})

export class AddCandidatoComponent implements OnInit{

  candidato = {
    nome: '',
    idade: '',
    cpf: '',
    email: '',
    telefone: ''
  }

  constructor(private snack: MatSnackBar,
      private router: Router,
      private candidatoService: CandidatoServiceService,
    ) {}

  ngOnInit(): void { }

  formSubmit(form: any) {
    if (form.invalid) {
      this.snack.open('Preencha corretamente todos os campos!', 'Fechar', {
        duration: 4000
      });
      return;
    }

    this.candidatoService.salvarCandidato(this.candidato).subscribe(
      (data: any) => {
        this.candidato = {
          nome: '',
          idade: '',
          cpf: '',
          email: '',
          telefone: ''
        };

        console.log(data);

        Swal.fire('Candidato salvo!', 'Com sucesso no sistema!', 'success');
        this.router.navigate(['/admin/candidatos']);
      },
      (error) => {
        console.log(error);
        Swal.fire('Erro!', 'Erro ao salvar o candidato', 'error');
      }
    );
  }
}
